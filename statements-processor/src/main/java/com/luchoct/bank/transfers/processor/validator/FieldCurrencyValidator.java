package com.luchoct.bank.transfers.processor.validator;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FieldCurrencyValidator implements FieldValidator<String> {
    @Override
    public Optional<String> getValidatedValue(final String value, final List<String> errors) {
        return Optional.of(value);
    }
}
