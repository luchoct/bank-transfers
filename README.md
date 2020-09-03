# bank-transfers
This project is the implementation of [the coding challenge state specified here](specification.md).

Bank Transfers is a software that performs the following tasks:
* It loads random bank statements, and it stores them into CSV files, in a FTP server.
* It loads CSV files from FTP Server, it processes them, and it stores calculated data into a MongoDB.
* It exposes an API that retrieve data from a MongoDB server.  

Implementation has been performed in Python and Java.

## Modules
See below the modules that compose bank-transfers are the following ones:

### statements-loader
Module that generates random statements and stores them into an FTP server.

Read more details at [README file of the module](statements-loader/README.md).

### statements-processor
ETL module that reads statements from files hosted in FTP server, processes them and loads them into a MongoDB database.

Read more details at [README file of the module](statements-processor/README.md).

### statements-api
Module that offers an API to retrieve analytical data from bank statements.

Read more details, **including details about exposed API**,  at [README file of the module](statements-api/README.md).

# Design considerations
## Scalability is a key point in current design
   * Scalability of ETL depends on volume of data to process.
   * Scalability of API depends on number of API requests to process.
## Data model focused on performance of API 
Instead of having a generic data model storing every bank transaction, statements-processor stores data that actually supports API, which avoids degradation on API performance, when volume of data increases.
However, storing bank statements is so easy as is adding a new message handler for channel statement-read in statements-processor, which would store it. 

See below pseudo-representation of data model.
```
currencies: {
    symbol: string
} 
dailyTransfers: {
    day: integer
    month: integer
    year: integer
    transfers: integer
} 
monthlyTransfers: {
    month: integer
    year: integer
    transfers: integer
} 
yearlyTransfers: {
    month: integer
    year: integer
    transfers: integer
} 
lastIBANTransfers: {
    iban: string
    dni: string
    currency: string
    amount: string
    date: date (ISO)
} 
```

# TODOs
* [See TODOs section in statements-loader](statements-loader/README.md).
* [See TODOs section in statements-processor](statements-processor/README.md).
* [See TODOs section in statements-api](statements-api/README.md).

In addition, see below global TODOs:
* On launching containers with docker compose, make sure that MongoDB is available before launching statements-processor and statements-api.
