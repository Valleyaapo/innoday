SELECT voter_id, voted, count(voted) FROM voter WHERE voted='Y';
# shows the amount of given votes
SELECT voter_id, voted, count(voted) FROM voter WHERE voted='N';
# shows the amount of votes not yet given amongst the voters

select voter_id from voter where team_id=*****;
# shows the voters in a certain team

select team_id from team where team_name='name_of_inno';
# shows the id of a team which name you do know
select team_name from team where team_id=***;
# shows the name of a team when you know id

select inno_id, inno_name, count(inno_name) FROM inno;
# should work and should show the amount of innovations in the database