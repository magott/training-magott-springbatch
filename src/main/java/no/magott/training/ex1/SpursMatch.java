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

/**
 * Represents a match being played between Spurs, the proudest team in the world and some other team
 * @author Morten Andersen-Gott
 *
 */
public class SpursMatch {
	private int matchId;
	private String day;
	private String competition;
	private String opposition;
	private String venue;
	private String halfTimeScore;
	private int spursGoals;
	private int oppositionGoals;
	private Date date;

	public SpursMatch(int matchId, String day,  Date date, String competition, String opposition, String venue,
			String halfTimeScore, int spursGoals, int oppositionGoals) {
		this.matchId = matchId;
		this.day = day;
		this.date = date;
		this.competition = competition;
		this.opposition = opposition;
		this.venue = venue;
		this.halfTimeScore = halfTimeScore;
		this.spursGoals = spursGoals;
		this.oppositionGoals = oppositionGoals;
	}

	public boolean isSpursWin(){
		return spursGoals > oppositionGoals;
	}
	
	/**
	 * @return the matchId, unique to a match in one season. Starts at 1
	 */
	public int getMatchId() {
		return matchId;
	}

	public String getDay() {
		return day;
	}

	public Date getDate() {
		return date;
	}
	
	public String getCompetition() {
		return competition;
	}

	public String getOpposition() {
		return opposition;
	}

	public String getVenue() {
		return venue;
	}

	public String getHalfTimeScore() {
		return halfTimeScore;
	}

	public int getSpursGoals() {
		return spursGoals;
	}

	public int getOppositionGoals() {
		return oppositionGoals;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SpursMatch [matchId=").append(matchId).append(", day=").append(day).append(", competition=")
				.append(competition).append(", opposition=").append(opposition).append(", venue=").append(venue)
				.append(", halfTimeScore=").append(halfTimeScore).append(", spursGoals=").append(spursGoals)
				.append(", oppositionGoals=").append(oppositionGoals).append(", date=").append(date).append("]");
		return builder.toString();
	}
	
	
	
}
