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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Morten Andersen-Gott
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SpursImportJobIntegrationTest extends JobLauncherTestUtils{

	@Autowired
	FlatFileItemReader<SpursMatch> spursMatchReader;
	
	@Test
	public void jobCompletesSuccessfully() throws Exception{
		JobExecution jobExecution = launchJob();
		assertThat(jobExecution.getExitStatus().getExitCode(), equalTo(ExitStatus.COMPLETED.getExitCode()));
	}
	
	@Test
	public void spursTrashedAr5ena1() throws Exception{
		int lineNumber = 1;//Reader configured to skip first (header) row
		
		spursMatchReader.open(new ExecutionContext());
		//Will skipp through first 141 rows in file
		while(lineNumber++ < 141 ){
			spursMatchReader.read();
		}
		SpursMatch spursMatch = spursMatchReader.read();
		assertThat(spursMatch.isSpursWin(), is(true));
		assertThat(spursMatch.getOpposition(), equalTo("Arsenal"));
		assertThat(spursMatch.getSpursGoals(), equalTo(5));
		assertThat(spursMatch.getOppositionGoals(), equalTo(1));
	}
	
}
