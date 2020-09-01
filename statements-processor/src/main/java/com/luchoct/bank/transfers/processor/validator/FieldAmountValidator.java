package com.luchoct.bank.transfers.processor.validator;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
public class FieldAmountValidator implements FieldValidator<BigDecimal> {
    @Override
    public Optional<BigDecimal> getValidatedValue(final String value, final List<String> errors) {
        try {
            return Optional.of(new BigDecimal(value));
        } catch (NumberFormatException nfe) {
            errors.add("Amount: Wrong value. " + value);
            return Optional.empty();
        }
    }
}
