# statements-loader
Module that generates random statements and stores them into an FTP server.

## Functional details
This module emulates 3rd parties storing files into an FTP server.

* File names of generated files follow the yyyymmddThhmmssZ in UTC time.
* A new file is generated every 60 seconds, approximately, with maximum 100 statements per file.

## Technical details
This module is implemented in Python.

### Environment variables
This module loads FTP configuration from environment variables:
* `FTP_SERVER`: the host of the FTP server
* `FTP_USER`: the user of FTP server
* `FTP_PASSWORD`: the user of FTP server
* `FTP_PASSIVE`: `True` for connecting in passive mode, `False` for connecting on active mode.

## Running only this module (standalone)
Launching this module starts the module and ftp server.

1. In the current dir, launch docker-compose with the configuration file for standalone mode.
    ```
    start docker-compose -f docker-compose-standalone.yml up
    ```
   System keeps generating files and uploading it to FTP server.
2. Click Ctrl+C over window with docker compose (step 1).
3. Stop services and remove containers with following command:
    ```
    docker-compose -f docker-compose-standalone.yml down
    ```
As per configuration of statements_loader service in docker compose file, the client connects in active mode.

## Integration tests.
Before running the tests the FTP server must be launched.
In the current dir, run following commands:
1. Launch docker-compose with the configuration file for integration tests.
    ```
    start docker-compose -f ./tests/resources/docker-compose-int-test.yml up
    ```
2. Run integration tests (using a Windows PATH).
    ```
    .\venv\Scripts\python.exe -m unittest discover -s tests/integration
    ```
3. Click Ctrl+C over window with docker compose (step 1).
4. Stop services and remove containers with following command:
    ```
    docker-compose -f ./tests/resources/docker-compose-int-test.yml down
    ```
  
As per configuration of FTP server in docker-compose, the integration tests 
regarding FTP server set the following env variables:
* `FTP_SERVER`: `localhost`
* `FTP_USER`: `ftpUser`
* `FTP_PASSWORD`: `ftpPass`
* `FTP_PASSIVE`: `True`

As per docker-compose-file, FTP server folder is mapped as a Docker volume to `./out/runTests/ftpServerContent`. 
However, as integration tests do remove uploaded FTP files, this folder will be empty after every execution.  

## Unit tests.
In order to run unit tests, run following commands in the current dir:
```
.\venv\Scripts\python.exe -m unittest discover -s tests/unit
```
As per code, a file is generated in `/out/runTests/unit`.   
# TODOs
* FTP connection uses default timeouts of Python ftplib module.
* FTP connection is not secured with TLS.
