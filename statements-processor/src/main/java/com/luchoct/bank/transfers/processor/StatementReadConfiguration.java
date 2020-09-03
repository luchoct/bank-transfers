package com.luchoct.bank.transfers.processor;

import com.luchoct.bank.transfers.processor.converter.StatementLineConverter;
import com.luchoct.bank.transfers.processor.validator.FieldValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.MessageChannel;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * All configuration related to statementsRead channel.
 * This channel receives a message once a correct {@link BankStatement} is read.
 */
@Configuration
public class StatementReadConfiguration {

    @Bean
    @ServiceActivator(inputChannel = "statementLineRead", outputChannel = "statementRead", requiresReply = "false")
    public StatementLineConverter statementLineProcessor(
            final FieldValidator<String> fieldIBANValidator,
            final FieldValidator<String> fieldDNIValidator,
            final FieldValidator<String> fieldCurrencyValidator,
            final FieldValidator<BigDecimal> fieldAmountValidator,
            final FieldValidator<ZonedDateTime> dateValidator) {
        return new StatementLineConverter(
                fieldIBANValidator,
                fieldDNIValidator,
                fieldCurrencyValidator,
                fieldAmountValidator,
                dateValidator);
    }

    @Bean
    public MessageChannel statementRead() {
        return new PublishSubscribeChannel();
    }
}
