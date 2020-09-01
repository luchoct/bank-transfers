package com.luchoct.bank.transfers.processor.validator;

import java.util.List;
import java.util.Optional;

public interface FieldValidator<T extends Object> {
    Optional<T> getValidatedValue(final String value, final List<String> errors);
}
