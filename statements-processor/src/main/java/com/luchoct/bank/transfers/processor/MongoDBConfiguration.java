package com.luchoct.bank.transfers.processor;

import com.luchoct.bank.transfers.processor.converter.ZonedDateTimeReadConverter;
import com.luchoct.bank.transfers.processor.converter.ZonedDateTimeWriteConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MongoDBConfiguration {
    @Bean
    public MongoCustomConversions customConversions(){
        final List<Converter<?,?>> converters = new ArrayList<Converter<?,?>>();
        converters.add(new ZonedDateTimeReadConverter());
        converters.add(new ZonedDateTimeWriteConverter());
        return new MongoCustomConversions(converters);
    }
}
