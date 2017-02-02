##Problem 2 - Mars Rovers
It's a command line application, run it from the jar, robotsonmars.0.0.1.jar, created in the build/libs.This is implemented with spring boot, however, unit testing is based on junit not spring boot testing.An assumption has been made that the input file with rover instructions, won't have corrupt data, however, if it does happen to have either invalid instructions or cardinal point, that robot/rover will be skipped. The robot will only move within the rectangular bounded by (0,0,E) to (max x-coordinate(plataeuX),0,E) horizontally and (0,0,N) to (max 0,y-coordinate(plataeuY),N).

###Pre-requisites
This problem was developed on Ubuntu linux 16.04:

1. JDK 1.8 
2. Gradle 3.2

###Cloning code
```
git clone git@github.com:ngochibamu/robotsonmars.git
```
```
cd robotsonmars
```

###Running unit test 
To see running unit test. Make sure you in robotsonmars/ application folder
```
gradle test 
```
OR
```
./gradlew clean test
```

To run unit test again 
```
gradle test --rerun-tasks
```

###Building and running app
```
cp build/libs/robotsonmars.0.0.1.jar .
```

```
java -jar robotsonmars-0.0.1.jar [instruction file]
```

