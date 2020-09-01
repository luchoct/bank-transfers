package com.luchoct.bank.transfers.processor.validator;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableWithSize.iterableWithSize;

class FieldDNIValidatorTest {

    private final FieldDNIValidator validator = new FieldDNIValidator();

    @Test
    void getValidatedValue_withRightValue() {
        System.out.println(validator.toString());
        final List<String> errors = new ArrayList<>(1);
        final Optional<String> value = validator.getValidatedValue("25250753L", errors);
        assertThat(value.isEmpty(), equalTo(false));
        assertThat(errors, iterableWithSize(0));
    }

    @Test
    void getValidatedValue_withWrongValue() {
        final List<String> errors = new ArrayList<>(1);
        final Optional<String> value = validator.getValidatedValue("54921852R", errors);
        assertThat(value.isEmpty(), equalTo(true));
        assertThat(errors, iterableWithSize(1));
    }
}