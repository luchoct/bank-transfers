# Specification
Our banking partner uses text files over FTP to let us know when a customer
deposits money that has to be assigned to her account. From our side we
check every 5 minutes if there is a new file or set of files ready to be
processed.

The file specs are:
* The name is the timestamp when it was uploaded to the FTP server, with
the format 20200727T201020Z.csv .
* Every line in the file has the following format IBAN\tDNI\tCURRENCY\tAMOUNT

The file is processed and inserted in MongoDB for later analysis. Expect the
file to be huge and with errors in the fields (malformed IBANs, malformed
DNIs, and missing data).

We not only persist this information but also expose it to a dashboard that
displays:
* Number of transfers received per day, per month and per year
* Month with the most transfers in the last year
* Month with the most aggregated amount of transfers ever
* Last transfers coming from a given IBAN
* How many different currencies have been received

To complete this challenge, send us the code to run this system, adding files
with randomized data to an FTP or SFTP that gets propagated to a Mongo
instance and exposed through an API.

Other considerations:
1. If possible please complete this test in Node.js, Python or any JVM
language (our stack is mainly Kotlin+Spring Boot and Typescript+NestJS).
If you want to use a different language please check with us first.
2. Provide production-ready code as much as possible. If you have time
constraints that's OK - just add a list of TODOs to the README.
3. Mock the behavior of the third party (create random files and upload to a
FTP) for testing
4. We should be able to test the entire solution without any external
dependency (we love Docker)
5. Commit messages are important
6. If you feel creative feel free to add more queries
7. Take any other assumptions that you need