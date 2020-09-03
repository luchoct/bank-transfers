package com.luchoct.bank.transfers.api.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document("lastIBANTransfers")
public class LastIBANTransfer {
    private final String iban;
    private final String dni;
    private final String currency;
    private final BigDecimal amount;
    private final String date;
}
