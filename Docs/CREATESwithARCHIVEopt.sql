-- DROP TABLE innoarchive;
-- DROP TABLE event;
DROP TABLE vote;
DROP TABLE voter;
DROP TABLE inno;
DROP TABLE team;

CREATE TABLE team (
	team_id BIGINT AUTO_INCREMENT PRIMARY KEY,
	team_name VARCHAR(30) NOT NULL
	)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE inno (
	inno_id BIGINT AUTO_INCREMENT PRIMARY KEY,
	team_id BIGINT NOT NULL,
	inno_name VARCHAR(100) NOT NULL,
	inno_desc VARCHAR(225),
	inno_owner VARCHAR(100) NOT NULL,
	FOREIGN KEY (team_id) REFERENCES team(team_id)
	)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE voter (
	voter_id BIGINT AUTO_INCREMENT PRIMARY KEY,
	fname VARCHAR(20) NOT NULL,
	sname VARCHAR(30) NOT NULL,
	type VARCHAR(10) NOT NULL,
	voted CHAR(1) default 'N',
	team_id BIGINT default '1',
	FOREIGN KEY (team_id) REFERENCES team(team_id)
	)ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE vote (
	vote_id BIGINT AUTO_INCREMENT PRIMARY KEY,
	voter_id BIGINT NOT NULL,
	inno_id BIGINT NOT NULL,
	legit CHAR(1) default 'N',
	FOREIGN KEY (voter_id) REFERENCES voter(voter_id),
	FOREIGN KEY (inno_id) REFERENCES inno(inno_id)
	)ENGINE=InnoDB DEFAULT CHARSET=latin1;
	
INSERT INTO team (team_id, team_name) VALUES ('1', 'not_in_team');

CREATE TABLE innoevent (
	event_id BIGINT AUTO_INCREMENT PRIMARY KEY,
	eventName VARCHAR(30) NOT NULL,
	eventYear INT NOT NULL,
	eventSemester CHAR(6)
	)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE innoarchive (
	inno_id BIGINT AUTO_INCREMENT PRIMARY KEY,
	event_id BIGINT NOT NULL,
	inno_name VARCHAR(100) NOT NULL,
	inno_desc VARCHAR(225),
	team_name VARCHAR(30) NOT NULL,
	votes INT default '0',
	FOREIGN KEY (event_id) REFERENCES event(event_id)
	)ENGINE=InnoDB DEFAULT CHARSET=latin1;
	
	