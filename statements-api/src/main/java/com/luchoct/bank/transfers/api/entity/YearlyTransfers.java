package com.luchoct.bank.transfers.api.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("yearlyTransfers")
public class YearlyTransfers {
    private final Integer year;
    private final Integer transfers;
}
