package com.luchoct.bank.transfers.processor.converter;

import org.junit.jupiter.api.Test;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class ZonedDateTimeReadConverterTest {

    @Test
    void convert_returnsRightValue() {
        //given
        final Date date = new Date();

        //when
        final ZonedDateTime zonedDateTime = new ZonedDateTimeReadConverter().convert(date);
        //then
        assertThat(zonedDateTime, equalTo(date.toInstant().atZone(ZoneOffset.UTC)));
    }
}