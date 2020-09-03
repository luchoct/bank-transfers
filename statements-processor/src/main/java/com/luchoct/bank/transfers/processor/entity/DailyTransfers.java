package com.luchoct.bank.transfers.processor.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("dailyTransfers")
public class DailyTransfers {
    private final Integer day;
    private final Integer month;
    private final Integer year;
    private final Integer transfers;
}
