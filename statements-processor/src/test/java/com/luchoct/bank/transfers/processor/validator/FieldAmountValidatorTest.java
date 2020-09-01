package com.luchoct.bank.transfers.processor.validator;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableWithSize.iterableWithSize;

class FieldAmountValidatorTest {

    private final FieldAmountValidator validator = new FieldAmountValidator();

    @Test
    void getValidatedValue_withRightValue() {
        System.out.println(validator.toString());
        final List<String> errors = new ArrayList<>(1);
        final Optional<BigDecimal> value = validator.getValidatedValue("57.23", errors);
        assertThat(value.isEmpty(), equalTo(false));
        assertThat(errors, iterableWithSize(0));
    }

    @Test
    void getValidatedValue_withWrongValue() {
        final List<String> errors = new ArrayList<>(1);
        final Optional<BigDecimal> value = validator.getValidatedValue("54a2", errors);
        assertThat(value.isEmpty(), equalTo(true));
        assertThat(errors, iterableWithSize(1));
    }
}