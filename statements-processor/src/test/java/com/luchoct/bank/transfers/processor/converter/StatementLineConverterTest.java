package com.luchoct.bank.transfers.processor.converter;

import com.luchoct.bank.transfers.processor.BankStatement;
import com.luchoct.bank.transfers.processor.validator.FieldValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.Message;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatementLineConverterTest {

    private static final Optional<ZonedDateTime> DATE = Optional.of(ZonedDateTime.now());
    private static final Optional<String> IBAN = Optional.of("any IBAN");
    private static final Optional<String> DNI = Optional.of("any DNI");
    private static final Optional<String> CURRENCY = Optional.of("any Currency");
    private static final Optional<BigDecimal> AMOUNT = Optional.of(new BigDecimal("55.55"));
    private static final String ERROR_MESSAGE = "Error";


    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private Message<String> message;

    @Mock(lenient = true)
    private FieldValidator<String> fieldIBANValidator;
    @Mock(lenient = true)
    private FieldValidator<String> fieldDNIValidator;
    @Mock(lenient = true)
    private FieldValidator<String> fieldCurrencyValidator;
    @Mock(lenient = true)
    private FieldValidator<BigDecimal> fieldAmountValidator;
    @Mock(lenient = true)
    private FieldValidator<ZonedDateTime> dateValidator;

    private StatementLineConverter converter;

    @BeforeEach
    void init() {
        //Required as per bug in Mockito: see https://github.com/mockito/mockito/issues/1066
        converter = new StatementLineConverter(
                fieldIBANValidator,
                fieldDNIValidator,
                fieldCurrencyValidator,
                fieldAmountValidator,
                dateValidator);
    }

    private void stubMessage() {
        final String payload = "GL8964710001000206\t79323490D\t€\t34.30";
        when(message.getPayload()).thenReturn(payload);
        final String filename = "20200830T155507Z.csv";
        when(message.getHeaders().get(eq(FileHeaders.FILENAME))).thenReturn(filename);
    }

    private void stubAllValidators() {
        when(dateValidator.getValidatedValue(anyString(), anyList())).thenReturn(DATE);
        when(fieldIBANValidator.getValidatedValue(anyString(), anyList())).thenReturn(IBAN);
        when(fieldDNIValidator.getValidatedValue(anyString(), anyList())).thenReturn(DNI);
        when(fieldCurrencyValidator.getValidatedValue(anyString(), anyList())).thenReturn(CURRENCY);
        when(fieldAmountValidator.getValidatedValue(anyString(), anyList())).thenReturn(AMOUNT);
    }

    private void verifyMessageInvocations() {
        verify(message, times(1)).getPayload();
        verify(message.getHeaders(), times(1)).get(eq(FileHeaders.FILENAME));
    }

    private void verifyValidatorInvocations() {
        verify(dateValidator, times(1)).getValidatedValue(eq("20200830T155507Z.csv"), anyList());
        verify(fieldIBANValidator, times(1)).getValidatedValue(eq("GL8964710001000206"), anyList());
        verify(fieldDNIValidator, times(1)).getValidatedValue(eq("79323490D"), anyList());
        verify(fieldCurrencyValidator, times(1)).getValidatedValue(eq("€"), anyList());
        verify(fieldAmountValidator, times(1)).getValidatedValue(eq("34.30"), anyList());
    }

    private <T> void stubValidatorForReturningError(final FieldValidator<T> validator, final String errorMessage) {
        doAnswer((Answer<Optional<T>>) invocation -> {
            final Object[] args = invocation.getArguments();
            ((List<String>)args[1]).add(errorMessage);
            return Optional.empty();
        }).when(validator).getValidatedValue(anyString(), anyList());
    }

    @Test
    void processStatementLine_withNoErrors_returnBankStatement() {
        //given
        stubMessage();
        stubAllValidators();

        //when
        final BankStatement statement = converter.processStatementLine(message);

        //then
        assertThat(statement, equalTo(new BankStatement(IBAN.get(), DNI.get(), CURRENCY.get(), AMOUNT.get(), DATE.get())));
        verifyMessageInvocations();
        verifyValidatorInvocations();
    }

    @Test
    void processStatementLine_withErrorFromIBANValidator_returnsBankStatement() {
        //given
        stubMessage();
        stubAllValidators();
        stubValidatorForReturningError(fieldIBANValidator, ERROR_MESSAGE);

        //when
        final BankStatement statement = converter.processStatementLine(message);

        //then
        assertThat(statement, nullValue());
        verifyMessageInvocations();
        verifyValidatorInvocations();
    }

    @Test
    void processStatementLine_withErrorFromDNIValidator_returnsNull() {
        //given
        stubMessage();
        stubAllValidators();
        stubValidatorForReturningError(fieldDNIValidator, ERROR_MESSAGE);

        //when
        final BankStatement statement = converter.processStatementLine(message);

        //then
        assertThat(statement, nullValue());
        verifyMessageInvocations();
        verifyValidatorInvocations();
    }

    @Test
    void processStatementLine_withErrorFromCurrencyValidator_returnsNull() {
        //given
        stubMessage();
        stubAllValidators();
        stubValidatorForReturningError(fieldCurrencyValidator, ERROR_MESSAGE);

        //when
        final BankStatement statement = converter.processStatementLine(message);

        //then
        assertThat(statement, nullValue());
        verifyMessageInvocations();
        verifyValidatorInvocations();
    }

    @Test
    void processStatementLine_withErrorFromAmountValidator_returnsNull() {
        //given
        stubMessage();
        stubAllValidators();
        stubValidatorForReturningError(fieldAmountValidator, ERROR_MESSAGE);

        //when
        final BankStatement statement = converter.processStatementLine(message);

        //then
        assertThat(statement, nullValue());
        verifyMessageInvocations();
        verifyValidatorInvocations();
    }

    @Test
    void processStatementLine_withErrorFromDateValidator_returnsNull() {
        //given
        stubMessage();
        stubAllValidators();
        stubValidatorForReturningError(dateValidator, ERROR_MESSAGE);

        //when
        final BankStatement statement = converter.processStatementLine(message);

        //then
        assertThat(statement, nullValue());
        verifyMessageInvocations();
        verifyValidatorInvocations();
    }

}