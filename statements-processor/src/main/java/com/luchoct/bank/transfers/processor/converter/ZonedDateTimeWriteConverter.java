package com.luchoct.bank.transfers.processor.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

public class ZonedDateTimeWriteConverter implements Converter<ZonedDateTime, Date> {
    @Override
    public Date convert(ZonedDateTime source) {
        return Date.from(source.withZoneSameInstant(ZoneOffset.UTC).toInstant());

    }
}
