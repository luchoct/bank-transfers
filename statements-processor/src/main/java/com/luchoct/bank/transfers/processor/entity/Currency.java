package com.luchoct.bank.transfers.processor.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("currencies")
public class Currency {
    private final String symbol;
}
