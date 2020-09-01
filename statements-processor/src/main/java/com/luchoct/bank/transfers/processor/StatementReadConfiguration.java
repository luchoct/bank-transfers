package com.luchoct.bank.transfers.processor;

import com.luchoct.bank.transfers.processor.validator.FieldValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

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
            @Autowired final FieldValidator<String> fieldIBANValidator,
            @Autowired final FieldValidator<String> fieldDNIValidator,
            @Autowired final FieldValidator<String> fieldCurrencyValidator,
            @Autowired final FieldValidator<BigDecimal> fieldAmountValidator,
            @Autowired final FieldValidator<ZonedDateTime> dateValidator) {
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

    @Bean
    @ServiceActivator(inputChannel = "statementRead")
    public MessageHandler handler() {
        return new MessageHandler() {

            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                System.out.println(message.getHeaders());
                System.out.println(message.getPayload().getClass());
                System.out.println(message.getPayload());
            }
        };
    }


}
