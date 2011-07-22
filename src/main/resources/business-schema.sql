CREATE TABLE SPURS_MATCH  (
	MATCH_ID BIGINT IDENTITY PRIMARY KEY ,    
	MATCH_DATE DATE NOT NULL, 
	COMPETITION VARCHAR(10) NOT NULL, 
	OPPOSITION VARCHAR(100) NOT NULL,
	VENUE VARCHAR(100) NOT NULL,
	HALF_TIME_SCORE VARCHAR(100) NOT NULL,
	GOALS_FOR INTEGER NOT NULL,
	GOALS_AGAINST INTEGER NOT NULL,
) ;

CREATE TABLE EXCHANGE_RATE(
	ID BIGINT IDENTITY PRIMARY KEY,
	EXCHANGE_DATE DATE NOT NULL,
	FROM_CURRENCY VARCHAR(5),
	TO_CURRENCY VARCHAR(5),
	EXCHANGE_RATE DOUBLE
);
CREATE TABLE EXCHANGE_RATE_REVERSE(
	ID BIGINT IDENTITY PRIMARY KEY,
	EXCHANGE_DATE DATE NOT NULL,
	FROM_CURRENCY VARCHAR(5),
	TO_CURRENCY VARCHAR(5),
	EXCHANGE_RATE DOUBLE
);
