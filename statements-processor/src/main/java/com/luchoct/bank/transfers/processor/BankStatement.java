package com.luchoct.bank.transfers.processor;

import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
public class BankStatement {

    private final String iban;
    private final String dni;
    private final String currency;
    private final BigDecimal amount;
    private final ZonedDateTime date;
}
