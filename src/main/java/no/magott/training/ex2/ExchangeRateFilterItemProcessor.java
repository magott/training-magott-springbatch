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

import org.springframework.batch.item.ItemProcessor;

/**
 * @author Morten Andersen-Gott
 *
 */
public class ExchangeRateFilterItemProcessor implements ItemProcessor<ExchangeRate, ExchangeRate> {

	/* (non-Javadoc)
	 * @see org.springframework.batch.item.ItemProcessor#process(java.lang.Object)
	 */
	@Override
	public ExchangeRate process(ExchangeRate item) throws Exception {
		//Filters out items for which there is no exchangerate given
		if(!item.hasExchangeRate()){
			return null;
		}
		return item;
	}

	

}
