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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Morten Andersen-Gott
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="CurrencyImportIntegrationTest-context.xml")
public class CurrencyImportComponentTests {
	@Autowired
	@Qualifier("nokReader")
	private FlatFileItemReader<ExchangeRate> reader;
	
	@Autowired
	private JdbcPagingItemReader<ExchangeRate> dbReader;
	
	@Autowired
	private ExchangeRateProducerItemProcessor exrProducer;
	
	@Test
	public void canReadNOKEURFromFile() throws Exception{
		reader.open(new ExecutionContext());
		ExchangeRate exchangeRate = reader.read();
		assertThat(exchangeRate, notNullValue());
		assertThat(exchangeRate.getDate(), notNullValue());
		assertThat(exchangeRate.getExchangeRate(), not(equalTo(0.0)));
		assertThat(exchangeRate.getFrom(), notNullValue());
		assertThat(exchangeRate.getTo(), notNullValue());
		
		reader.close();
	}
	
	@Test 
	public void canReadWithJdbcReader() throws Exception{
		dbReader.open(new ExecutionContext());
		dbReader.read();
	}
	
	@Test
	public void exchangeRateIsProduced() throws Exception{
		ExchangeRate exchangeRate = new ExchangeRate();
		exchangeRate.setFrom("foo");
		exchangeRate.setTo("bar");
		exchangeRate.setDate(new Date());
		exchangeRate.setExchangeRate(1.0);
		ExchangeRate exchangeRate2 = exrProducer.process(exchangeRate);
		assertThat(exchangeRate2, notNullValue());
	}
}
