package com.luchoct.bank.transfers.processor.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {
    private ValidationUtils() {}

    private static Pattern DNI_PATTERN = Pattern.compile("[0-9]{8,8}[A-Z]");
    private static final String CONTROL_LETTERS = "TRWAGMYFPDXBNJZSQVHLCKE";

    public static boolean isDNI(final String value) {
        final Matcher matcher = DNI_PATTERN.matcher(value);
        if (matcher.matches()) {
            final String receivedControlLetter = value.substring(8,9);
            final int index = Integer.parseInt(value.substring(0,8))%23;
            final String expectedControlLetter = CONTROL_LETTERS.substring(index, index + 1);
            return expectedControlLetter.equalsIgnoreCase(receivedControlLetter);
        } else {
            return false;
        }
    }
}
