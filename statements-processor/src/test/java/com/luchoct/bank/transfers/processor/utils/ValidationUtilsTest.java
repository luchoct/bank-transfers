package com.luchoct.bank.transfers.processor.utils;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class ValidationUtilsTest {

    @Test
    void isDNI_withRightValue_returnsTrue() {
        assertThat(ValidationUtils.isDNI("25250753L"), equalTo(true));
    }

    @Test
    void isDNI_withWrongValue_returnsFalse() {
        assertThat(ValidationUtils.isDNI("54921852R"), equalTo(false));
    }
}