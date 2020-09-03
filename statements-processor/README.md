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

MongoDB is also launched, so module streams out metrics and data to mongodDB.
## Tests.
Test suit contains unit tests and integration tests.

Integration tests are launched with gradle, which uses `com.avast.gradle.docker-compose` plugin to launch docker compose file before running the tests.

Before running the tests, docker compose loads file `src/test/resources/docker-compose-int-test.yml`, so it launches FTP server and MongoDB server. 

**Note that I could have used embedded servers for such purpose**, but since we are using Docker for running production environment, and other modules, I have been decided to have some homogeneity.

In the current dir, run tests with gradle:
```
gradle test
```

As per docker-compose-file, FTP server folder is mapped as a Docker volume to `src/test/resources/ftpServerContent`, which contains some Bank Statements Files.

In addition, MongoDB is loaded with testing data.
# TODOs
* FTP connection is not secured with TLS.
* On launching containers with docker compose, make sure that MongoDB is available before launching statements-processor.
* Assign a statement ID to every statement which would make easier ERROR handling.
* Scalability: capability to launch several instances and distribute the load of processing statements between instances, at file level or at statement line level.
  * Traceability so system tracks when a statement file and line has been already processed.
  * Error Handling: avoiding to process a statement file if it has already been processed.
  * Error Handling: avoiding to process a statement line if it has already been processed.
* Faulty tolerance (in case of failure of external dependency, like unavailability of MongoDB):
  * Circuit Breaking: close circuit with external dependnecy and stop processing, until circuit is open. 
  * Dead Letter Queue: unprocessed lines to be reprocessed later, once system is down.
