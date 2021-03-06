package com.luchoct.bank.transfers.processor.validator;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@Component
public class FilenameDateValidator implements FieldValidator<ZonedDateTime> {
    private static final String FILENAME_FORMAT = "yyyyMMdd'T'HHmmss'Z.csv'";

    private static final ThreadLocal<DateTimeFormatter> FILENAME_DATETIME_DTF_HOLDER =
            ThreadLocal.withInitial(() -> DateTimeFormatter.ofPattern(FILENAME_FORMAT));

    @Override
    public Optional<ZonedDateTime> getValidatedValue(final String value, final List<String> errors) {
        try {
            final ZonedDateTime parse = ZonedDateTime.of(
                    LocalDateTime.parse(value, FILENAME_DATETIME_DTF_HOLDER.get()),
                    ZoneOffset.UTC);

            return Optional.of(parse);
        } catch (DateTimeParseException dtpe) {
            errors.add("Date: Wrong value. " + value);
            return Optional.empty();
        }
    }

    public String toString() {
        return FILENAME_DATETIME_DTF_HOLDER.get().format(ZonedDateTime.now());
    }
}
