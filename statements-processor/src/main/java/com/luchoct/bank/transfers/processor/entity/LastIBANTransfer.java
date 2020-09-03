package com.luchoct.bank.transfers.processor.entity;

import com.luchoct.bank.transfers.processor.BankStatement;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Document("lastIBANTransfers")
public class LastIBANTransfer extends BankStatement {

    public LastIBANTransfer(
            final String iban,
            final String dni,
            final String currency,
            final BigDecimal amount,
            final ZonedDateTime date) {
        super(iban, dni, currency, amount, date);
    }

    public LastIBANTransfer(final BankStatement statement) {
        this(statement.getIban(), statement.getDni(), statement.getCurrency(), statement.getAmount(), statement.getDate());
    }
}
