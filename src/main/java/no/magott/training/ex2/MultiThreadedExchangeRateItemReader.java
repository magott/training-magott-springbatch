package no.magott.training.ex2;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ReaderNotOpenException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.util.Assert;

/**
 * A simplified version of org.springframework.batch.sample.common.StagingItemReader
 */
public class MultiThreadedExchangeRateItemReader implements ItemReader<ExchangeRate>, StepExecutionListener,
		InitializingBean, DisposableBean {

	private static Log logger = LogFactory.getLog(MultiThreadedExchangeRateItemReader.class);

	private final Object lock = new Object();

	private volatile boolean initialized = false;

	private volatile Iterator<Long> keys;

	private SimpleJdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}

	public void destroy() throws Exception {
		initialized = false;
		keys = null;
	}

	public final void afterPropertiesSet() throws Exception {
		Assert.notNull(jdbcTemplate, "You must provide a DataSource.");
	}

	private List<Long> retrieveKeys() {

		synchronized (lock) {

			return jdbcTemplate.query(

			"SELECT ID FROM EXCHANGE_RATE ORDER BY ID",

			new RowMapper<Long>() {
				public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getLong(1);
				}
			});

		}

	}

	public ExchangeRate read() throws DataAccessException {

		if (!initialized) {
			throw new ReaderNotOpenException("Reader must be open before it can be used.");
		}

		Long id = null;
		synchronized (lock) {
			if (keys.hasNext()) {
				id = keys.next();
			}
		}
		logger.debug("Retrieved key from list: " + id);

		if (id == null) {
			return null;
		}
		return jdbcTemplate.queryForObject("SELECT ID, EXCHANGE_DATE, FROM_CURRENCY, TO_CURRENCY, EXCHANGE_RATE FROM EXCHANGE_RATE WHERE ID=?", new ExchangeRateRowMapper(), id);

	}

	public ExitStatus afterStep(StepExecution stepExecution) {
		return null;
	}

	public void beforeStep(StepExecution stepExecution) {
		synchronized (lock) {
			if (keys == null) {
				keys = retrieveKeys().iterator();
				logger.info("Keys obtained for staging.");
				initialized = true;
			}
		}
	}

}