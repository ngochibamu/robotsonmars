##Problem 2 - Mars Rovers
It's a command line application, run it from the jar robotsonmars.0.0.1.jar thats created in the build/libs.This is build with spring boot, however, unit testing is based on junit not spring boot testing.An assumption has been made that the input file with rover instructions, won't have mistakes, however, if it does happen to have either invalid instructions or cardinal point, that robot/rover will be skipped. The robot will only move within the rectangular bounded by (0,0,E) to (max x-coordinate(plataeuX),0,E) horizontally and (0,0,N) to (max 0,y-coordinate(plataeuY),N)

###Pre-requisites
This problem was solved with:
*JDK 1.8 
*Gradle 3.2

###Cloning code

###Running unit test 
To see running unit test
`gradle test`

To run unit test again 
`gradle test --rerun-tasks`

###Building 