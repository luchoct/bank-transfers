// DAILY TRANSFERS

/* 1 */
db.dailyTransfers.insert({
    "_id" : ObjectId("5f5164963cc0802b969b2b01"),
    "day" : 2,
    "month" : 9,
    "year" : 2019,
    "transfers" : 25,
    "_class" : "com.luchoct.bank.transfers.processor.entity.DailyTransfers"
})

/* 2 */
db.dailyTransfers.insert({
    "_id" : ObjectId("5f51649c3cc0802b969b2b7e"),
    "day" : 30,
    "month" : 8,
    "year" : 2020,
    "transfers" : 99,
    "_class" : "com.luchoct.bank.transfers.processor.entity.DailyTransfers"
})

/* 3 */
db.dailyTransfers.insert({
    "_id" : ObjectId("5f5164b13cc0802b969b2d6d"),
    "day" : 2,
    "month" : 9,
    "year" : 2020,
    "transfers" : 75,
    "_class" : "com.luchoct.bank.transfers.processor.entity.DailyTransfers"
})

// MONTHLY TRANSFERS

/* 1 */
db.monthlyTransfers.insert({
    "_id" : ObjectId("5f5164963cc0802b969b2b02"),
    "month" : 9,
    "year" : 2019,
    "transfers" : 25,
    "_class" : "com.luchoct.bank.transfers.processor.entity.MonthlyTransfers"
})

/* 2 */
db.monthlyTransfers.insert({
    "_id" : ObjectId("5f51649c3cc0802b969b2b7f"),
    "month" : 8,
    "year" : 2020,
    "transfers" : 99,
    "_class" : "com.luchoct.bank.transfers.processor.entity.MonthlyTransfers"
})

/* 3 */
db.monthlyTransfers.insert({
    "_id" : ObjectId("5f5164b13cc0802b969b2d6e"),
    "month" : 9,
    "year" : 2020,
    "transfers" : 75,
    "_class" : "com.luchoct.bank.transfers.processor.entity.MonthlyTransfers"
})

// YEARLY TRANSFERS

/* 1 */
db.yearlyTransfers.insert({
    "_id" : ObjectId("5f5164963cc0802b969b2b03"),
    "year" : 2019,
    "transfers" : 25,
    "_class" : "com.luchoct.bank.transfers.processor.entity.YearlyTransfers"
})

/* 2 */
db.yearlyTransfers.insert({
    "_id" : ObjectId("5f51649c3cc0802b969b2b80"),
    "year" : 2020,
    "transfers" : 174,
    "_class" : "com.luchoct.bank.transfers.processor.entity.YearlyTransfers"
})

// CURRENCIES

/* 1 */
db.currencies.insert({
    "_id" : ObjectId("5f5164963cc0802b969b2b05"),
    "symbol" : "€",
    "_class" : "com.luchoct.bank.transfers.processor.entity.Currency"
})

/* 2 */
db.currencies.insert({
    "_id" : ObjectId("5f5164963cc0802b969b2b0a"),
    "symbol" : "£",
    "_class" : "com.luchoct.bank.transfers.processor.entity.Currency"
})

/* 3 */
db.currencies.insert({
    "_id" : ObjectId("5f5164973cc0802b969b2b19"),
    "symbol" : "$",
    "_class" : "com.luchoct.bank.transfers.processor.entity.Currency"
})

// LAST IBAN TRANSFERS

/* 1 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164963cc0802b969b2b04"),
    "iban" : "BR1800360305000010009795493C1",
    "dni" : "78197959Y",
    "currency" : "€",
    "amount" : "96.63",
    "date" : ISODate("2020-09-02T11:54:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 2 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164963cc0802b969b2b09"),
    "iban" : "BA391290079401028494",
    "dni" : "53041404T",
    "currency" : "£",
    "amount" : "91.72",
    "date" : ISODate("2020-09-02T11:54:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 3 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164963cc0802b969b2b0e"),
    "iban" : "DK5000400440116243",
    "dni" : "14520137F",
    "currency" : "£",
    "amount" : "79.44",
    "date" : ISODate("2020-09-02T11:54:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 4 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164973cc0802b969b2b13"),
    "iban" : "KW81CBKU0000000000001234560101",
    "dni" : "64845593Z",
    "currency" : "€",
    "amount" : "39.56",
    "date" : ISODate("2020-09-02T11:53:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 5 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164973cc0802b969b2b18"),
    "iban" : "IT60X0542811101000000123456",
    "dni" : "35042182A",
    "currency" : "$",
    "amount" : "87.04",
    "date" : ISODate("2020-09-02T11:54:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 6 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164973cc0802b969b2b1d"),
    "iban" : "NO9386011117947",
    "dni" : "47795066P",
    "currency" : "£",
    "amount" : "9.25",
    "date" : ISODate("2020-09-02T11:53:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 7 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164973cc0802b969b2b22"),
    "iban" : "BY13NBRB3600900000002Z00AB00",
    "dni" : "00604067H",
    "currency" : "$",
    "amount" : "29.93",
    "date" : ISODate("2020-09-02T11:53:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 8 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164973cc0802b969b2b27"),
    "iban" : "NL91ABNA0417164300",
    "dni" : "12032051S",
    "currency" : "$",
    "amount" : "1.93",
    "date" : ISODate("2020-09-02T11:53:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 9 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164973cc0802b969b2b2c"),
    "iban" : "SC18SSCB11010000000000001497USD",
    "dni" : "47607244G",
    "currency" : "€",
    "amount" : "14.57",
    "date" : ISODate("2020-09-02T11:54:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 10 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164973cc0802b969b2b36"),
    "iban" : "MD24AG000225100013104168",
    "dni" : "97764560R",
    "currency" : "$",
    "amount" : "20.90",
    "date" : ISODate("2020-09-02T11:54:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 11 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164973cc0802b969b2b3b"),
    "iban" : "MC5811222000010123456789030",
    "dni" : "98323948Y",
    "currency" : "€",
    "amount" : "24.36",
    "date" : ISODate("2020-09-02T11:53:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 12 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164973cc0802b969b2b40"),
    "iban" : "GB29NWBK60161331926819",
    "dni" : "60264262F",
    "currency" : "€",
    "amount" : "56.67",
    "date" : ISODate("2020-09-02T11:54:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 13 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164973cc0802b969b2b45"),
    "iban" : "PS92PALS000000000400123456702",
    "dni" : "78041934J",
    "currency" : "€",
    "amount" : "25.45",
    "date" : ISODate("2020-09-02T11:53:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 14 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164973cc0802b969b2b4a"),
    "iban" : "TN5910006035183598478831",
    "dni" : "83694400E",
    "currency" : "€",
    "amount" : "72.64",
    "date" : ISODate("2020-09-02T11:54:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 15 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164973cc0802b969b2b54"),
    "iban" : "CH9300762011623852957",
    "dni" : "47530724M",
    "currency" : "£",
    "amount" : "65.50",
    "date" : ISODate("2020-09-02T11:54:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 16 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164973cc0802b969b2b59"),
    "iban" : "LV80BANK0000435195001",
    "dni" : "49610933W",
    "currency" : "£",
    "amount" : "2.67",
    "date" : ISODate("2020-09-02T11:53:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 17 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164973cc0802b969b2b5e"),
    "iban" : "LC55HEMM000100010012001200023015",
    "dni" : "65992999L",
    "currency" : "€",
    "amount" : "61.24",
    "date" : ISODate("2020-09-02T11:54:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 18 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164973cc0802b969b2b63"),
    "iban" : "TR330006100519786457841326",
    "dni" : "44133866V",
    "currency" : "$",
    "amount" : "67.99",
    "date" : ISODate("2020-09-02T11:53:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 19 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164973cc0802b969b2b68"),
    "iban" : "SA0380000000608010167519",
    "dni" : "70500969N",
    "currency" : "£",
    "amount" : "66.40",
    "date" : ISODate("2020-09-02T11:54:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 20 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164973cc0802b969b2b6d"),
    "iban" : "AL47212110090000000235698741",
    "dni" : "77260122V",
    "currency" : "£",
    "amount" : "72.68",
    "date" : ISODate("2020-09-02T11:53:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 21 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164973cc0802b969b2b72"),
    "iban" : "XK051212012345678906",
    "dni" : "08674012E",
    "currency" : "$",
    "amount" : "46.38",
    "date" : ISODate("2020-09-02T11:54:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 22 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164973cc0802b969b2b77"),
    "iban" : "PL61109010140000071219812874",
    "dni" : "96044731C",
    "currency" : "$",
    "amount" : "78.43",
    "date" : ISODate("2020-09-02T11:53:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 23 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164973cc0802b969b2b7c"),
    "iban" : "RS35260005601001611379",
    "dni" : "03995637P",
    "currency" : "$",
    "amount" : "94.35",
    "date" : ISODate("2020-09-02T11:53:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 24 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f51649c3cc0802b969b2b81"),
    "iban" : "GL8964710001000206",
    "dni" : "79323490D",
    "currency" : "€",
    "amount" : "34.30",
    "date" : ISODate("2020-09-02T21:15:07.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 25 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f51649c3cc0802b969b2b8b"),
    "iban" : "HU42117730161111101800000000",
    "dni" : "03437693K",
    "currency" : "£",
    "amount" : "27.74",
    "date" : ISODate("2020-09-02T11:54:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 26 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164a13cc0802b969b2bb3"),
    "iban" : "UA213996220000026007233566001",
    "dni" : "05175445P",
    "currency" : "£",
    "amount" : "93.28",
    "date" : ISODate("2020-08-30T16:18:11.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 27 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164a13cc0802b969b2bb8"),
    "iban" : "LT121000011101001000",
    "dni" : "95723665B",
    "currency" : "€",
    "amount" : "92.88",
    "date" : ISODate("2020-08-30T16:19:11.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 28 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164a13cc0802b969b2bc2"),
    "iban" : "AE070331234567890123456",
    "dni" : "60932451T",
    "currency" : "$",
    "amount" : "18.93",
    "date" : ISODate("2020-09-02T11:54:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 29 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164a13cc0802b969b2bcc"),
    "iban" : "ES9121000418450200051332",
    "dni" : "13922031Q",
    "currency" : "£",
    "amount" : "33.12",
    "date" : ISODate("2020-09-02T11:54:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 30 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164a13cc0802b969b2bdb"),
    "iban" : "IQ98NBIQ850123456789012",
    "dni" : "09936320K",
    "currency" : "£",
    "amount" : "9.87",
    "date" : ISODate("2020-09-02T11:54:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 31 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164a13cc0802b969b2be0"),
    "iban" : "LU280019400644750000",
    "dni" : "46514307G",
    "currency" : "$",
    "amount" : "7.39",
    "date" : ISODate("2020-08-30T16:18:11.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 32 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164a13cc0802b969b2bea"),
    "iban" : "DO28BAGR00000001212453611324",
    "dni" : "94741525V",
    "currency" : "€",
    "amount" : "12.12",
    "date" : ISODate("2020-08-30T16:18:11.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 33 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164a13cc0802b969b2bef"),
    "iban" : "CZ6508000000192000145399",
    "dni" : "28830330Z",
    "currency" : "€",
    "amount" : "84.38",
    "date" : ISODate("2020-08-30T16:19:11.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 34 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164a13cc0802b969b2bf9"),
    "iban" : "CR05015202001026284066",
    "dni" : "23095386M",
    "currency" : "€",
    "amount" : "20.34",
    "date" : ISODate("2020-08-30T16:19:11.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 35 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164a13cc0802b969b2c03"),
    "iban" : "FR1420041010050500013M02606",
    "dni" : "21949994J",
    "currency" : "£",
    "amount" : "34.69",
    "date" : ISODate("2020-09-02T11:54:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 36 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164a13cc0802b969b2c08"),
    "iban" : "PK36SCBL0000001123456702",
    "dni" : "88900404E",
    "currency" : "€",
    "amount" : "54.54",
    "date" : ISODate("2020-08-30T16:19:11.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 37 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164a13cc0802b969b2c0d"),
    "iban" : "SK3112000000198742637541",
    "dni" : "55421987Z",
    "currency" : "€",
    "amount" : "22.17",
    "date" : ISODate("2020-09-02T11:54:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 38 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164a13cc0802b969b2c12"),
    "iban" : "ME25505000012345678951",
    "dni" : "20901081S",
    "currency" : "£",
    "amount" : "12.10",
    "date" : ISODate("2020-08-30T16:18:11.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 39 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164a13cc0802b969b2c17"),
    "iban" : "MR1300020001010000123456753",
    "dni" : "59242707C",
    "currency" : "$",
    "amount" : "2.07",
    "date" : ISODate("2020-09-02T11:54:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 40 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164a13cc0802b969b2c1c"),
    "iban" : "DE89370400440532013000",
    "dni" : "12906167Q",
    "currency" : "$",
    "amount" : "42.73",
    "date" : ISODate("2020-08-30T16:19:11.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 41 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164a13cc0802b969b2c21"),
    "iban" : "MU17BOMM0101101030300200000MUR",
    "dni" : "82392826H",
    "currency" : "€",
    "amount" : "92.84",
    "date" : ISODate("2020-08-30T16:19:11.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 42 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164a13cc0802b969b2c26"),
    "iban" : "FI2112345600000785",
    "dni" : "38910556F",
    "currency" : "€",
    "amount" : "30.85",
    "date" : ISODate("2020-09-02T11:54:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 43 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164a13cc0802b969b2c4e"),
    "iban" : "SI56263300012039086",
    "dni" : "61487798N",
    "currency" : "£",
    "amount" : "17.00",
    "date" : ISODate("2020-09-02T11:54:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 44 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164a13cc0802b969b2c53"),
    "iban" : "GT82TRAJ01020000001210029690",
    "dni" : "82737665H",
    "currency" : "€",
    "amount" : "53.93",
    "date" : ISODate("2020-09-02T11:54:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 45 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164a13cc0802b969b2c58"),
    "iban" : "GE29NB0000000101904917",
    "dni" : "97695850S",
    "currency" : "$",
    "amount" : "30.68",
    "date" : ISODate("2020-08-30T16:18:11.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 46 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164a13cc0802b969b2c62"),
    "iban" : "PT50000201231234567890154",
    "dni" : "87245086Z",
    "currency" : "$",
    "amount" : "99.33",
    "date" : ISODate("2020-09-02T11:54:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 47 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164a13cc0802b969b2c67"),
    "iban" : "BE68539007547034",
    "dni" : "23629019S",
    "currency" : "€",
    "amount" : "51.55",
    "date" : ISODate("2020-09-02T11:54:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 48 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164a13cc0802b969b2c6c"),
    "iban" : "LB62099900000001001901229114",
    "dni" : "55835276Q",
    "currency" : "£",
    "amount" : "41.10",
    "date" : ISODate("2020-09-02T11:54:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 49 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164a23cc0802b969b2ca3"),
    "iban" : "IS140159260076545510730339",
    "dni" : "42903896H",
    "currency" : "€",
    "amount" : "39.70",
    "date" : ISODate("2020-08-30T16:19:11.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 50 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164a73cc0802b969b2cbc"),
    "iban" : "JO94CBJO0010000000000131000302",
    "dni" : "40393979E",
    "currency" : "£",
    "amount" : "49.72",
    "date" : ISODate("2020-09-02T11:54:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 51 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164a73cc0802b969b2cc1"),
    "iban" : "BH67BMAG00001299123456",
    "dni" : "32351563Q",
    "currency" : "£",
    "amount" : "91.42",
    "date" : ISODate("2020-09-02T11:54:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 52 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164a73cc0802b969b2cc6"),
    "iban" : "CY17002001280000001200527600",
    "dni" : "22499939M",
    "currency" : "$",
    "amount" : "97.55",
    "date" : ISODate("2020-08-30T16:19:11.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 53 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164a73cc0802b969b2cda"),
    "iban" : "HR1210010051863000160",
    "dni" : "72927186M",
    "currency" : "$",
    "amount" : "54.96",
    "date" : ISODate("2020-08-30T16:19:11.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 54 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164a73cc0802b969b2cee"),
    "iban" : "MT84MALT011000012345MTLCAST001S",
    "dni" : "48264930M",
    "currency" : "£",
    "amount" : "58.02",
    "date" : ISODate("2020-09-02T11:54:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 55 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164a73cc0802b969b2cfd"),
    "iban" : "MK07250120000058984",
    "dni" : "48776119L",
    "currency" : "€",
    "amount" : "91.83",
    "date" : ISODate("2020-09-02T11:54:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 56 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164a73cc0802b969b2d11"),
    "iban" : "AT611904300234573201",
    "dni" : "08591790W",
    "currency" : "€",
    "amount" : "7.05",
    "date" : ISODate("2020-09-02T11:54:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 57 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164a73cc0802b969b2d2a"),
    "iban" : "VG96VPVG0000012345678901",
    "dni" : "84743650N",
    "currency" : "€",
    "amount" : "58.75",
    "date" : ISODate("2020-09-02T11:54:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 58 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164a73cc0802b969b2d4d"),
    "iban" : "GR1601101250000000012300695",
    "dni" : "06122283M",
    "currency" : "$",
    "amount" : "3.17",
    "date" : ISODate("2020-08-30T16:19:11.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 59 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164a73cc0802b969b2d57"),
    "iban" : "SE4550000000058398257466",
    "dni" : "37533078E",
    "currency" : "$",
    "amount" : "81.11",
    "date" : ISODate("2020-09-02T11:54:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 60 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164a73cc0802b969b2d61"),
    "iban" : "BG80BNBG96611020345678",
    "dni" : "53634933Z",
    "currency" : "$",
    "amount" : "34.43",
    "date" : ISODate("2020-08-30T16:19:11.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 61 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164b63cc0802b969b2df7"),
    "iban" : "LI21088100002324013AA",
    "dni" : "97295680E",
    "currency" : "$",
    "amount" : "5.64",
    "date" : ISODate("2020-09-02T11:54:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 62 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164b63cc0802b969b2e10"),
    "iban" : "IL620108000000099999999",
    "dni" : "74208841P",
    "currency" : "$",
    "amount" : "33.03",
    "date" : ISODate("2020-09-02T11:54:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 63 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164b63cc0802b969b2e1a"),
    "iban" : "SM86U0322509800000000270100",
    "dni" : "15731332E",
    "currency" : "$",
    "amount" : "98.96",
    "date" : ISODate("2020-09-02T11:54:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 64 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164b63cc0802b969b2e2e"),
    "iban" : "AZ21NABZ00000000137010001944",
    "dni" : "00077389V",
    "currency" : "$",
    "amount" : "5.24",
    "date" : ISODate("2020-09-02T11:54:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 65 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164b73cc0802b969b2e56"),
    "iban" : "AD1200012030200359100100",
    "dni" : "23115880Y",
    "currency" : "€",
    "amount" : "7.74",
    "date" : ISODate("2020-09-02T11:54:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 66 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164b73cc0802b969b2eb5"),
    "iban" : "RO49AAAA1B31007593840000",
    "dni" : "71571559K",
    "currency" : "€",
    "amount" : "55.80",
    "date" : ISODate("2020-09-02T11:54:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})

/* 67 */
db.lastIBANTransfers.insert({
    "_id" : ObjectId("5f5164b73cc0802b969b2ec9"),
    "iban" : "EE382200221020145685",
    "dni" : "06042065B",
    "currency" : "$",
    "amount" : "37.18",
    "date" : ISODate("2020-09-02T11:54:48.000Z"),
    "_class" : "com.luchoct.bank.transfers.processor.entity.LastIBANTransfer"
})
