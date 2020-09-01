# statements-processor
ETL module that reads statements from files hosted in FTP server, processes them and loads them into a MongoDB database.

## Functional details
This ETL loads files from FTP server, processes them and stores following data in a MongoDB database.

## Technical details
This module is implemented in Java, with Spring Integration and Gradle.

## Running only this module (standalone)
Launching this module starts the module, ftp server and mongodb.

In the current dir, launch docker-compose with the configuration file for standalone mode.
```
start docker-compose -f docker-compose-standalone.yml up
```
Stop services and remove containers with following command:
```
docker-compose -f docker-compose-standalone.yml down
```
Note that the ftp server is started and mapped to folder './out/run/ftpServerContent', which contains some bank statements files.

## Tests.
Test suit contains unit tests and integration tests.

Integration tests are launched with gradle, which uses `com.avast.gradle.docker-compose` plugin to launch docker compose file before running the tests.

Before running the tests, docker compose loads file `src/test/resources/docker-compose-int-test.yml`, so it launches FTP server. 

**Note that I could have used embedded servers for such purpose**, but since we are using Docker for running production environment, and other modules, I have been decided to have some homogeneity.

In the current dir, run tests with gradle:
```
gradle test
```

As per docker-compose-file, FTP server folder is mapped as a Docker volume to `src/test/resources/ftpServerContent`, which contains some Bank Statements Files.
# TODOs
* FTP connection is not secured with TLS.
