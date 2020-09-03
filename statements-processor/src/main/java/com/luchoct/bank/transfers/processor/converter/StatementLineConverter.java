package com.luchoct.bank.transfers.processor.converter;

import com.luchoct.bank.transfers.processor.BankStatement;
import com.luchoct.bank.transfers.processor.validator.FieldValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StatementLineConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatementLineConverter.class);

    private static final short INDEX_IBAN = 0;
    private static final short INDEX_DNI = 1;
    private static final short INDEX_CURRENCY = 2;
    private static final short INDEX_AMOUNT = 3;

    private final FieldValidator<String> fieldIBANValidator;
    private final FieldValidator<String> fieldDNIValidator;
    private final FieldValidator<String> fieldCurrencyValidator;
    private final FieldValidator<BigDecimal> fieldAmountValidator;
    private final FieldValidator<ZonedDateTime> dateValidator;

    public StatementLineConverter(
            final FieldValidator<String> fieldIBANValidator,
            final FieldValidator<String> fieldDNIValidator,
            final FieldValidator<String> fieldCurrencyValidator,
            final FieldValidator<BigDecimal> fieldAmountValidator,
            final FieldValidator<ZonedDateTime> dateValidator) {
        this.fieldIBANValidator = fieldIBANValidator;
        this.fieldDNIValidator = fieldDNIValidator;
        this.fieldCurrencyValidator = fieldCurrencyValidator;
        this.fieldAmountValidator = fieldAmountValidator;
        this.dateValidator = dateValidator;
    }

    public BankStatement processStatementLine(final Message<?> message) throws MessagingException {
        final String[] fields = message.getPayload().toString().split("\t");
        final List<String> errors = new ArrayList<>(5);
        final Optional<ZonedDateTime> date = dateValidator.getValidatedValue(message.getHeaders().get(FileHeaders.FILENAME).toString(), errors);
        final Optional<String> iban = fieldIBANValidator.getValidatedValue(fields[INDEX_IBAN], errors);
        final Optional<String> dni = fieldDNIValidator.getValidatedValue(fields[INDEX_DNI], errors);
        final Optional<String> currency = fieldCurrencyValidator.getValidatedValue(fields[INDEX_CURRENCY], errors);
        final Optional<BigDecimal> amount = fieldAmountValidator.getValidatedValue(fields[INDEX_AMOUNT], errors);
        if (errors.isEmpty()) {
            final BankStatement bankStatement = new BankStatement(iban.get(), dni.get(), currency.get(), amount.get(), date.get());
            LOGGER.info("Converted bank statement: {}", bankStatement);
            return bankStatement;
        } else {
            LOGGER.error("Processing line: found errors {}", errors);
            return null;
        }
    }
}
