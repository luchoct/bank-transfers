package com.luchoct.bank.transfers.processor.converter;

import org.junit.jupiter.api.Test;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class ZonedDateTimeWriteConverterTest {

    @Test
    void convert_returnsRightValue() {
        //given
        final ZonedDateTime zonedDateTime = ZonedDateTime.now();

        //when
        final Date date = new ZonedDateTimeWriteConverter().convert(zonedDateTime);
        //then
        assertThat(ZonedDateTime.ofInstant(date.toInstant(), ZoneOffset.UTC).toInstant().toEpochMilli(),
                equalTo(zonedDateTime.withZoneSameInstant(ZoneOffset.UTC).toInstant().toEpochMilli()));
    }
}