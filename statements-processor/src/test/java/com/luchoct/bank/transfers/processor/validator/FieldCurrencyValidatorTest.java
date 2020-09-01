package com.luchoct.bank.transfers.processor.validator;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableWithSize.iterableWithSize;

class FieldCurrencyValidatorTest {

    private final FieldCurrencyValidator validator = new FieldCurrencyValidator();

    @Test
    void getValidatedValue() {
        System.out.println(validator.toString());
        final List<String> errors = new ArrayList<>(1);
        final Optional<String> value = validator.getValidatedValue("€", errors);
        assertThat(value.isEmpty(), equalTo(false));
        assertThat(errors, iterableWithSize(0));
    }

}