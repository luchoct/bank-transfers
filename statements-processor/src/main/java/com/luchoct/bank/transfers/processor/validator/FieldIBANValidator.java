package com.luchoct.bank.transfers.processor.validator;

import org.apache.commons.validator.routines.IBANValidator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FieldIBANValidator implements FieldValidator<String> {
    private static final IBANValidator IBAN_VALIDATOR = IBANValidator.getInstance();

    @Override
    public Optional<String> getValidatedValue(final String value, final List<String> errors) {
        if (IBAN_VALIDATOR.isValid(value)) {
            return Optional.of(value);
        } else {
            errors.add("IBAN: Wrong value. " + value);
            return Optional.empty();
        }
    }
}
