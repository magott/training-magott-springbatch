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

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.batch.core.listener.SkipListenerSupport;

/**
 * @author Morten Andersen-Gott
 *
 */
public class LoggingSkipListener extends SkipListenerSupport<Object, Object> {

    Logger log = LogManager.getLogger(LoggingSkipListener.class);
    
    @Override
    public void onSkipInRead(Throwable t) {
        log.info("Error in read caused skip",t);
    }
    

    @Override
    public void onSkipInProcess(Object item, Throwable t) {
    	log.info("Error in process caused the following element to be skipped "+item,t);
    }

    @Override
    public void onSkipInWrite(Object item, Throwable t) {
    	log.info("Error in process caused the following element to be skipped "+item,t);
    }
    
}
