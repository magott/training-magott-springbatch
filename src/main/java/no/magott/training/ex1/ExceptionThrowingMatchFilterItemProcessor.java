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
package no.magott.training.ex1;

import java.util.Random;

/**
 * @author Morten Andersen-Gott
 * 
 */
public class ExceptionThrowingMatchFilterItemProcessor extends MatchFilterItemProcessor{

	private boolean exceptionThrown = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.batch.item.ItemProcessor#process(java.lang.Object)
	 */
	@Override
	public SpursMatch process(SpursMatch item) throws Exception {
		throwOnRandom();
		return super.process(item);
	}

	/**
	 * 
	 */
	private void throwOnRandom() {
		if (exceptionThrown) {
			return;
		}
		if (1 == new Random().nextInt(100)) {
			exceptionThrown = true;
			throw new RuntimeException("Planned random exception");
		}

	}

}
