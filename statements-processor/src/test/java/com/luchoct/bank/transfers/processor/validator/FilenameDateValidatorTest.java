package com.luchoct.bank.transfers.processor.validator;

import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableWithSize.iterableWithSize;

class FilenameDateValidatorTest {

    private final FilenameDateValidator validator = new FilenameDateValidator();

    @Test
    void getValidatedValue_withRightValue() {
        System.out.println(validator.toString());
        final List<String> errors = new ArrayList<>(1);
        final Optional<ZonedDateTime> value = validator.getValidatedValue("20200830T155507Z.csv", errors);
        assertThat(value.isEmpty(), equalTo(false));
        assertThat(errors, iterableWithSize(0));
    }

    @Test
    void getValidatedValue_withWrongValue() {
        final List<String> errors = new ArrayList<>(1);
        final Optional<ZonedDateTime> value = validator.getValidatedValue("202008A0T155507Z.csv", errors);
        assertThat(value.isEmpty(), equalTo(true));
        assertThat(errors, iterableWithSize(1));
    }
}