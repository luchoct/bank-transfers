# bank-transfers
This project is the implementation of [the coding challenge state specified here](specification.md).

Bank Transfers is a software that performs the following tasks:
* It loads random bank statements, and it stores them into CSV files, in a FTP server.
* It loads CSV files from FTP Server, it processes them, and it stores calculated data into a MongoDB.
* It exposes an API that retrieve data from a MongoDB server.  
 
See below the modules that compose bank-transfers are the following ones:

## statements-loader
Module that generates random statements and stores them into an FTP server.

Read more details at [README file of the module](statements-loader/README.md).

## TODOs
* [See TODOs section in statements-loader](statements-loader/README.md).
