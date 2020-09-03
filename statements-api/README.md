# statements-api
Module that offers an API to retrieve analytical data from bank statements.

## Functional details
The API offered by this module contains the following endpoints:
* Last transfer of given IBAN: `/iban/{iban}/last-transfer`
* Number of transfers per day: `/transfers/{year}/{month}/{day}`
* Number of transfers per month: `/transfers/{year}/{month}`
* Number of transfers per year: `/transfers/{year}`
* Month with maximum transfers within year: `/months/{year}/max-transfers`
* Month with maximum transfers ever: `/months/max-transfers`
* All different currencies: `/currencies`

## Technical details
This module is implemented in Java, with Spring Integration and Gradle.
This module loads the statements data from a MongoDB database.

## Running only this module (standalone)
Launching this module starts the module, the statements-processor, ftp server and mongodb.

In the current dir, launch docker-compose with the configuration file for standalone mode.
```
start docker-compose -f docker-compose-standalone.yml up
```
Stop services and remove containers with following command:
```
docker-compose -f docker-compose-standalone.yml down
```
Note that the ftp server is started and mapped to folder '../statements-processor/out/run/ftpServerContent', which contains some bank statements files.

MongoDB is also launched, so module streams out metrics and data to mongodDB.

Once data is retrieved from FTP server and statistics are populated to database, API can be invoked. 
As per statements loaded, following getExamples of API invocations return data:
* Number of transfers per day: 
    * Date with transfers: `http://localhost:8080/transfers/2020/8/30`
    * Date with transfers: `http://localhost:8080/transfers/2020/9/2`
    * Date without transfers: `http://localhost:8080/transfers/2020/9/1`
* Number of transfers per month: 
    * Month with transfers: `http://localhost:8080/transfers/2020/8`
    * Month with transfers: `http://localhost:8080/transfers/2020/9`
    * Month without transfers: `http://localhost:8080/transfers/2020/6`
* Number of transfers per year: 
    * Year with transfers: `http://localhost:8080/transfers/2020`
    * Year with transfers: `http://localhost:8080/transfers/2019`
    * Year without transfers: `http://localhost:8080/transfers/2018` 
* Month with maximum transfers within year:
    * Year with transfers: `http://localhost:8080/months/2020/max-transfers`
    * Year without transfers: `http://localhost:8080/months/2010/max-transfers`
* Month with maximum transfers ever:
    * `http://localhost:8080/months/max-transfers`    
* Last transfer with given IBAN: 
    * IBAN used for several transfers: `http://localhost:8080/iban/GL8964710001000206/last-transfer`
    * IBAN not used for any transfer: `http://localhost:8080/iban/LB96395893619336483475759827/last-transfer`
* All different currencies: `http://localhost:8080/currencies`

## Tests.
Test suit contains unit tests and integration tests.

Integration tests are launched with gradle, which uses `com.avast.gradle.docker-compose` plugin to launch docker compose file before running the tests.

Before running the tests, docker compose loads file `src/test/resources/docker-compose-int-test.yml`, so it launches MongoDB server. 

**Note that I could have used embedded servers for such purpose**, but since we are using Docker for running production environment, and other modules, I have been decided to have some homogeneity.

In the current dir, run tests with gradle:
```
gradle test
```
As per docker-compose-file, MongoDB is started.
# TODOs
* On launching containers with docker compose, make sure that MongoDB is available before launching statements-api.
