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

import java.util.Date;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

/**
 * @author Morten Andersen-Gott
 *
 */
public class SpursMatchFieldSetMapper implements FieldSetMapper<SpursMatch> {

	/* (non-Javadoc)
	 * @see org.springframework.batch.item.file.mapping.FieldSetMapper#mapFieldSet(org.springframework.batch.item.file.transform.FieldSet)
	 */
	@Override
	public SpursMatch mapFieldSet(FieldSet fieldSet) throws BindException {
		int matchId = fieldSet.readInt("MatchId", 0);
		String day = fieldSet.readString("Day");
		String competition = fieldSet.readString("Competition");
		String opposition = fieldSet.readString("Opposition");
		String venue = fieldSet.readString("Venue");
		String halfTimeScore = fieldSet.readString("HalfTimeScore");
		int spursGoals = fieldSet.readInt("SpursGoals",0);
		int oppositionGoals = fieldSet.readInt("OppositionGoals", 0);
		Date date = fieldSet.readDate("Date", "MMM d, YYYY",null);
		
		return new SpursMatch(matchId, day, date, competition, opposition, venue, halfTimeScore, spursGoals, oppositionGoals);
	}

	
	
}
