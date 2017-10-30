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
Move the data from tables to archive-tables, delete unnecessary data:
team, innovation and votes for innovations are relevant : how to? think it
Create the archive tables according to the previous
Empty the databases for the new event
Add to the Archive the: name the new event below which comes the innovations 
(which inno belongs to which event ->)

# Further development ideas for the software
Three votes for a voter, names are not probably necessary info in the database for usage
but how to ensure a voter does not vote more than three times if there's no name check?
<<<<<<< HEAD
The % of the given votes among the voters next to the amount of the votes
-> three votes can be rounded by giving each student 3 profiles to vote on with, requires meeting with the teams beforehand possibly?

Maybe adding 
lombok and RowMappers to the software? (for knowledge: lombok requires an installation to 
a computer on which it is proggrammed)

# Further usage of the software
Admin should check the status of the software in a good time before the need of the usage
Maybe a week or two before the event, in which the engine is required, collect the data from
the innovators at their class to make sure that every innovation is added?
