DROP TABLE event;

CREATE TABLE event (
	event_id BIGINT AUTO_INCREMENT PRIMARY KEY,
	event VARCHAR(30) NOT NULL
	)ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE votearchive;
DROP TABLE voterarchive;
DROP TABLE innoarchive;
DROP TABLE teamarchive;

CREATE TABLE teamarchive (
	team_id BIGINT AUTO_INCREMENT PRIMARY KEY,
	team_name VARCHAR(30) NOT NULL
	)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE innoarchive (
	inno_id BIGINT AUTO_INCREMENT PRIMARY KEY,
	team_id BIGINT NOT NULL,
	inno_name VARCHAR(100) NOT NULL,
	inno_desc VARCHAR(225),
	inno_owner VARCHAR(100) NOT NULL,
	FOREIGN KEY (team_id) REFERENCES team(team_id)
	)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE voterarchive (
	voter_id BIGINT AUTO_INCREMENT PRIMARY KEY,
	fname VARCHAR(20) NOT NULL,
	sname VARCHAR(30) NOT NULL,
	type VARCHAR(10) NOT NULL,
	voted CHAR(1) default 'N',
	team_id BIGINT default '1',
	FOREIGN KEY (team_id) REFERENCES team(team_id)
	)ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE votearchive (
	vote_id BIGINT AUTO_INCREMENT PRIMARY KEY,
	voter_id BIGINT NOT NULL,
	inno_id BIGINT NOT NULL,
	legit CHAR(1) default 'N',
	FOREIGN KEY (voter_id) REFERENCES voter(voter_id),
	FOREIGN KEY (inno_id) REFERENCES inno(inno_id)
	)ENGINE=InnoDB DEFAULT CHARSET=latin1;
	
CREATE TABLE eventarchive (
	event_id BIGINT AUTO_INCREMENT PRIMARY KEY,
	event VARCHAR(10) NOT NULL
	)ENGINE=InnoDB DEFAULT CHARSET=latin1;

	