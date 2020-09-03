package com.luchoct.bank.transfers.api.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("monthlyTransfers")
public class MonthlyTransfers {
    private final Integer month;
    private final Integer year;
    private final Integer transfers;
}
