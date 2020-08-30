# statements-loader
Module that generates random statements and stores them into an FTP server.

## Functional details
This module emulates 3rd parties storing files into an FTP server.

## Technical details
This module is implemented in Python, and generates files every certain time.

Number of statements included in every file is random.

## Running the module
In case of running just this module, you can launch the server
## Environment variables
* `FTP_SERVER`: the host of the FTP server
* `FTP_USER`: the user of FTP server
* `FTP_PASSWORD`: the user of FTP server

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
As per configuration of FTP server in docker-compose, the integration tests 
regarding FTP server set the following env variables:
* `FTP_SERVER`: `localhost`
* `FTP_USER`: `ftpUser`
* `FTP_PASSWORD`: `ftpPass`

As per docker-compose-file, FTP server folder is mapped as a Docker volume to `./out/tests/ftpServerContent`. 
However, as integration tests do remove uploaded FTP files, this folder will be empty after every execution.  

## Unit tests.
In order to run unit tests, run following commands in the current dir:
```
.\venv\Scripts\python.exe -m unittest discover -s tests/unit
```

   
# TODOs
* FTP connection uses default timeouts of Python ftplib module.
* FTP connection is not secured with TLS.
