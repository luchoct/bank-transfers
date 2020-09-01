package com.luchoct.bank.transfers.processor.validator;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableWithSize.iterableWithSize;

class FieldIBANValidatorTest {

    private final FieldIBANValidator validator = new FieldIBANValidator();

    @Test
    void getValidatedValue_withRightValue() {
        System.out.println(validator.toString());
        final List<String> errors = new ArrayList<>(1);
        final Optional<String> value = validator.getValidatedValue("NL77ABNA7852130259", errors);
        assertThat(value.isEmpty(), equalTo(false));
        assertThat(errors, iterableWithSize(0));
    }

    @Test
    void getValidatedValue_withWrongValue() {
        final List<String> errors = new ArrayList<>(1);
        final Optional<String> value = validator.getValidatedValue("ES9520804539586661511963", errors);
        assertThat(value.isEmpty(), equalTo(true));
        assertThat(errors, iterableWithSize(1));
    }
}