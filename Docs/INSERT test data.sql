DELETE FROM vote;
DELETE FROM voter;
DELETE FROM inno;
DELETE FROM team;

INSERT INTO team
	(team_id, team_name)
values
	('1', 'not_in_team'),
	('2', 'innovaattorit'),
	('3', 'ohjelmoijat'),
	('4', 'järjestelmäasiantuntijat'),
	('5', 'it-tuki');
	
INSERT INTO inno
	(inno_id, team_id, inno_name, inno_desc, inno_owner)
values
	('2', '2', 'idea', 'innovaattori idea', 'jaajo'),
	('3', '3', 'sovellus', 'ohjelmointikehitys', 'haaga-helia'),
	('4', '4', 'windows vs mac', 'käyttöjärjestelmät', 'linux'),
	('5', '5', 'konehajos', 'it-tuki auttaa', 'tukiloppu');
	
INSERT INTO voter
	(voter_id, fname, sname, type, team_id)
values
	('1', 'Maija', 'Mallikas', 'INNOMEM', '2'),
	('2', 'Antti', 'Apula', 'INNOMEM', '2'),
	('3', 'Kaisu', 'Kaartinen', 'TEACHER', '1'),
	('4', 'Kimmo', 'Kovalainen', 'TEACHER', '1'),
	('5', 'Jani', 'Jalkala', 'STUDENT','1'),
	('6', 'Jutta', 'Junkkari', 'STUDENT', '1'),
	('7', 'Oona', 'Ottawa', 'VISITOR','1'),
	('8', 'Olli', 'Osaaja', 'VISITOR', '1');
	
INSERT INTO vote
	(voter_id, inno_id)
values
	('1', '2'),
	('2', '2'),
	('3', '3'),
	('4', '3'),
	('5', '4'),
	('6', '5'),
	('7', '4'),
	('8', '5');