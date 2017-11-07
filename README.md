# Softala1-VotingEngine

1. navigate to project folder - terminal
2. git clone <project> - terminal
3. ./gradlew eclipse - in terminal
4. open eclipse, download and install EGradle plugin (might not be 100% necessary)
4. import excisting project as Gradle project (if you have the plugin)

Probably necessary terminal commands in the project folder
# ./gradlew build - gradle build - ./gradlew bootrun - ./gradlew eclipse

# hide dependencies
Customize view, enable external libraries
(Presentation Hierarchical)
(Customize - Content - Nested)

# BIG TODO
Archive event:

CREATESwithARCHIVEopt includes the CREATES used in the app and archive tables
drop event / innoarchive should never be used unless a big fail in databases
as it loses all the stored archive data if applied into use
idea : 

event that is archived should be named as archived - id auto increment
insert relative data into innoarchive from inno, team, vote maybe as exampled below:

INSERT INTO table2 (column1, column2, column3, ...)
SELECT column1, column2, column3, ...
FROM table1
WHERE condition;

and maybe something like:
public interface ArchiveDAO{
	public abstract Event addEvent(Event event);
	public abstract Event find(long event_id);
	public abstract List<Event> findAll();
	public abstract void insertArchive(Innovation inno, Vote vote, Team team, Event event);
	public abstract void deleteUnused(Innovation inno, Voter voter, Team team, Vote vote);
}

# Further development ideas for the software
Three votes for a voter, names are not probably necessary info in the database for usage
but how to ensure a voter does not vote more than three times if there's no name check?

The % of the given votes among the voters next to the amount of the votes
-> three votes can be rounded by giving each student 3 profiles to vote on with, requires meeting with the teams beforehand possibly?

Maybe adding 
lombok and RowMappers to the software? (for knowledge: lombok requires an installation to 
a computer on which it is proggrammed)

# Further usage of the software
Admin should check the status of the software in a good time before the need of the usage
Maybe a week or two before the event, in which the engine is required, collect the data from
the innovators at their class to make sure that every innovation is added?
