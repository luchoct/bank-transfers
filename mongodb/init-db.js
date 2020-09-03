db.createUser({
    user: "bankTransfersUser",
    pwd: "bankTransfersPass",
    roles: [{
        role: "readWrite",
        db: "bank-transfers"
    }]
})

//Number of transfers received per day
db.createCollection("dailyTransfers")
db.dailyTransfers.createIndex( { "day": 1, "month": 1, "year": 1 }, { unique: true } )

//Number of transfers received per month
//Also Month with the most transfers in the last year
//Also Month with the most aggregated amount of transfers ever
db.createCollection("monthlyTransfers") 
db.monthlyTransfers.createIndex( { "month": 1, "year": 1 }, { unique: true } )

//Number of transfers received per per year
db.createCollection("yearlyTransfers")
db.yearlyTransfers.createIndex( { "year": 1 }, { unique: true } )

//Last transfers coming from a given IBAN
db.createCollection("lastIBANTransfers")
db.lastIBANTransfers.createIndex( { "iban": 1 }, { unique: true } )

//How many different currencies have been received
db.createCollection("currencies")
db.currencies.createIndex( { "symbol": 1 }, { unique: true } )

