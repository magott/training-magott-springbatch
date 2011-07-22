/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package no.magott.training.ex2;

import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author Morten Andersen-Gott
 *
 */
public class ExchangeRateProducerItemProcessor implements ItemProcessor<ExchangeRate, ExchangeRate>{

	private static final String FIND_EXCHANGE_RATE = "SELECT ID FROM EXCHANGE_RATE_REVERSE WHERE FROM_CURRENCY=? AND TO_CURRENCY=? AND EXCHANGE_DATE=?";
	
	private final static Log logger = LogFactory.getLog(ExchangeRate.class);
	
	@Autowired
	private DataSource dataSource;
	
	/* (non-Javadoc)
	 * @see org.springframework.batch.item.ItemProcessor#process(java.lang.Object)
	 */
	@Override
	public ExchangeRate process(ExchangeRate item) throws Exception {
		String from = item.getFrom();
		String to = item.getTo();
		
		List<Long> list = new JdbcTemplate(dataSource).queryForList(FIND_EXCHANGE_RATE, Long.class, to, from, item.getDate());
		if(!CollectionUtils.isEmpty(list)){
			logger.trace("There already was a reversed entry for "+from+":"+"to");
			return null;
		}
		ExchangeRate exchangeRate = new ExchangeRate();
		exchangeRate.setTo(from);
		exchangeRate.setFrom(to);
		exchangeRate.setDate(item.getDate());
		exchangeRate.setExchangeRate(1/item.getExchangeRate());
		return exchangeRate;
	}

}
