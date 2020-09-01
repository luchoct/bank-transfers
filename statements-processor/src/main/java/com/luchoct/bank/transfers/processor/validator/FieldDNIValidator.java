package com.luchoct.bank.transfers.processor.validator;

import com.luchoct.bank.transfers.processor.utils.ValidationUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FieldDNIValidator implements FieldValidator<String> {
    @Override
    public Optional<String> getValidatedValue(final String value, final List<String> errors) {
        if (ValidationUtils.isDNI(value)) {
            return Optional.of(value.toUpperCase());
        } else {
            errors.add("DNI: Wrong value. " + value);
            return Optional.empty();
        }
    }
}
