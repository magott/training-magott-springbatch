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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.SkipListener;

/**
 * @author Morten Andersen-Gott
 *
 */
public class SpursSkipListener implements SkipListener<SpursMatch, SpursMatch> {

	private static final Log logger = LogFactory.getLog(SpursSkipListener.class);
	
	@Override
	public void onSkipInRead(Throwable t) {
		logger.warn("Skip in read, may require manual intervention", t);
	}

	@Override
	public void onSkipInWrite(SpursMatch item, Throwable t) {
		logger.warn("Skip in write for item " + item, t);
		
	}

	@Override
	public void onSkipInProcess(SpursMatch item, Throwable t) {
		logger.warn("Skip in process for item " + item, t);
		
	}

}
