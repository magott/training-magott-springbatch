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
package no.magott.training;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

/**
 * @author Morten Andersen-Gott
 * 
 */
public class BusinessSchemaDbIntegrationTest {

	@Test
	public void createAndInsertIntoSpursMatch() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder.addScript("classpath:business-schema.sql").build();
		String sql = "INSERT INTO SPURS_MATCH(MATCH_DATE, COMPETITION, OPPOSITION, VENUE, HALF_TIME_SCORE, GOALS_FOR, GOALS_AGAINST) VALUES(:date, :competition, :opposition, :venue, :halfTimeScore, :spursGoals, :oppositionGoals)";
		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(db);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("date", new Date());
		params.put("opposition", "Ar5ena1");
		params.put("halfTimeScore", "(2-0)");
		params.put("venue", "WHL");
		params.put("spursGoals", 5);
		params.put("oppositionGoals", 1);
		params.put("competition", "FACUP");
		jdbcTemplate.update(sql, params);
		db.shutdown();
	}

}
