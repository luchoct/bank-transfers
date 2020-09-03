package com.luchoct.bank.transfers.processor;

import com.luchoct.bank.transfers.processor.entity.Currency;
import com.luchoct.bank.transfers.processor.entity.DailyTransfers;
import com.luchoct.bank.transfers.processor.entity.LastIBANTransfer;
import com.luchoct.bank.transfers.processor.entity.MonthlyTransfers;
import com.luchoct.bank.transfers.processor.entity.YearlyTransfers;
import com.luchoct.bank.transfers.processor.repository.CurrencyRepository;
import com.luchoct.bank.transfers.processor.repository.DailyTransfersRepository;
import com.luchoct.bank.transfers.processor.repository.LastIBANTransferRepository;
import com.luchoct.bank.transfers.processor.repository.MonthlyTransfersRepository;
import com.luchoct.bank.transfers.processor.repository.YearlyTransfersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatementReadMessageHandlersConfigurationTest {

    private static final ZonedDateTime DATE = ZonedDateTime.now();
    private static final String IBAN = "any IBAN";
    private static final String DNI = "any DNI";
    private static final String CURRENCY = "any Currency";
    private static final BigDecimal AMOUNT = new BigDecimal("55.55");

    @Mock
    private DailyTransfersRepository dailyTransfersRepository;
    @Mock
    private MonthlyTransfersRepository monthlyTransfersRepository;
    @Mock
    private YearlyTransfersRepository yearlyTransfersRepository;
    @Mock
    private LastIBANTransferRepository lastIBANTransferRepository;
    @Mock
    private CurrencyRepository currencyRepository;
    @Mock
    private MongoOperations mongoOperations;

    @InjectMocks
    private StatementReadMessageHandlersConfiguration configuration;

    @Mock(lenient = true)
    private BankStatement bankStatement;

    @Mock
    private Message<BankStatement> message;

    private void stubMessage() {
        when(message.getPayload()).thenReturn(bankStatement);
        when(bankStatement.getIban()).thenReturn(IBAN);
        when(bankStatement.getDni()).thenReturn(DNI);
        when(bankStatement.getCurrency()).thenReturn(CURRENCY);
        when(bankStatement.getAmount()).thenReturn(AMOUNT);
        when(bankStatement.getDate()).thenReturn(DATE);
    }

    @Test
    void incrementDailyTransfers_withNewDailyData_inserts1AsNumberOfTransfers() {
        //given
        stubMessage();
        when(dailyTransfersRepository.save(any(DailyTransfers.class))).thenReturn(null);
        //when
        final MessageHandler messageHandler = configuration.incrementDailyTransfers();
        messageHandler.handleMessage(message);
        //then
        verify(dailyTransfersRepository, times(1)).save(eq(new DailyTransfers(DATE.getDayOfMonth(), DATE.getMonthValue(), DATE.getYear(), 1)));
        verifyNoMoreInteractions(mongoOperations);
        verify(message, times(1)).getPayload();
        verify(bankStatement, atLeast(1)).getDate();
    }

    @Test
    void incrementDailyTransfers_withExistingDailyData_incrementsNumberOfTransfers() {
        //given
        stubMessage();
        when(dailyTransfersRepository.save(any(DailyTransfers.class))).thenThrow(new DuplicateKeyException("any Message"));
        when(mongoOperations.updateFirst(any(Query.class), any(Update.class), any(Class.class))).thenReturn(null);;
        //when
        final MessageHandler messageHandler = configuration.incrementDailyTransfers();
        messageHandler.handleMessage(message);
        //then
        verify(dailyTransfersRepository, times(1)).save(eq(new DailyTransfers(DATE.getDayOfMonth(), DATE.getMonthValue(), DATE.getYear(), 1)));
        verify(mongoOperations, times(1)).updateFirst(
                eq(new Query(Criteria
                        .where("day").is(DATE.getDayOfMonth())
                        .and("month").is(DATE.getMonthValue())
                        .and("year").is(DATE.getYear()))), 
                eq(new Update().inc("transfers", 1)),
                eq(DailyTransfers.class));
        verify(message, times(1)).getPayload();
        verify(bankStatement, atLeast(1)).getDate();
    }

    @Test
    void incrementDailyTransfers_withAnotherException_doThrowException() {
        //given
        stubMessage();
        final String exceptionMessage = "any Message";
        when(dailyTransfersRepository.save(any(DailyTransfers.class))).thenThrow(new IllegalStateException(exceptionMessage));
        //when
        final MessageHandler messageHandler = configuration.incrementDailyTransfers();
        final Throwable exception = assertThrows(IllegalStateException.class, () -> messageHandler.handleMessage(message));
        //then
        assertThat(exceptionMessage, equalTo(exception.getMessage()));
        verify(dailyTransfersRepository, times(1)).save(eq(new DailyTransfers(DATE.getDayOfMonth(), DATE.getMonthValue(), DATE.getYear(), 1)));
        verifyNoMoreInteractions(mongoOperations);
        verify(message, times(1)).getPayload();
        verify(bankStatement, atLeast(1)).getDate();
    }

    @Test
    void incrementMonthlyTransfers_withNewMonthlyData_inserts1AsNumberOfTransfers() {
        //given
        stubMessage();
        when(monthlyTransfersRepository.save(any(MonthlyTransfers.class))).thenReturn(null);
        //when
        final MessageHandler messageHandler = configuration.incrementMonthlyTransfers();
        messageHandler.handleMessage(message);
        //then
        verify(monthlyTransfersRepository, times(1)).save(eq(new MonthlyTransfers(DATE.getMonthValue(), DATE.getYear(), 1)));
        verifyNoMoreInteractions(mongoOperations);
        verify(message, times(1)).getPayload();
        verify(bankStatement, atLeast(1)).getDate();
    }

    @Test
    void incrementMonthlyTransfers_withExistingMonthlyData_incrementsNumberOfTransfers() {
        //given
        stubMessage();
        when(monthlyTransfersRepository.save(any(MonthlyTransfers.class))).thenThrow(new DuplicateKeyException("any Message"));
        when(mongoOperations.updateFirst(any(Query.class), any(Update.class), any(Class.class))).thenReturn(null);;
        //when
        final MessageHandler messageHandler = configuration.incrementMonthlyTransfers();
        messageHandler.handleMessage(message);
        //then
        verify(monthlyTransfersRepository, times(1)).save(eq(new MonthlyTransfers(DATE.getMonthValue(), DATE.getYear(), 1)));
        verify(mongoOperations, times(1)).updateFirst(
                eq(new Query(Criteria
                        .where("month").is(DATE.getMonthValue())
                        .and("year").is(DATE.getYear()))),
                eq(new Update().inc("transfers", 1)),
                eq(MonthlyTransfers.class));
        verify(message, times(1)).getPayload();
        verify(bankStatement, atLeast(1)).getDate();
    }

    @Test
    void incrementMonthlyTransfers_withAnotherException_doThrowException() {
        //given
        stubMessage();
        final String exceptionMessage = "any Message";
        when(monthlyTransfersRepository.save(any(MonthlyTransfers.class))).thenThrow(new IllegalStateException(exceptionMessage));
        //when
        final MessageHandler messageHandler = configuration.incrementMonthlyTransfers();
        final Throwable exception = assertThrows(IllegalStateException.class, () -> messageHandler.handleMessage(message));
        //then
        assertThat(exceptionMessage, equalTo(exception.getMessage()));
        verify(monthlyTransfersRepository, times(1)).save(eq(new MonthlyTransfers(DATE.getMonthValue(), DATE.getYear(), 1)));
        verifyNoMoreInteractions(mongoOperations);
        verify(message, times(1)).getPayload();
        verify(bankStatement, atLeast(1)).getDate();
    }

    @Test
    void incrementYearlyTransfers_withNewYearlyData_inserts1AsNumberOfTransfers() {
        //given
        stubMessage();
        when(yearlyTransfersRepository.save(any(YearlyTransfers.class))).thenReturn(null);
        //when
        final MessageHandler messageHandler = configuration.incrementYearlyTransfers();
        messageHandler.handleMessage(message);
        //then
        verify(yearlyTransfersRepository, times(1)).save(eq(new YearlyTransfers(DATE.getYear(), 1)));
        verifyNoMoreInteractions(mongoOperations);
        verify(message, times(1)).getPayload();
        verify(bankStatement, atLeast(1)).getDate();
    }

    @Test
    void incrementYearlyTransfers_withExistingYearlyData_incrementsNumberOfTransfers() {
        //given
        stubMessage();
        when(yearlyTransfersRepository.save(any(YearlyTransfers.class))).thenThrow(new DuplicateKeyException("any Message"));
        when(mongoOperations.updateFirst(any(Query.class), any(Update.class), any(Class.class))).thenReturn(null);
        //when
        final MessageHandler messageHandler = configuration.incrementYearlyTransfers();
        messageHandler.handleMessage(message);
        //then
        verify(yearlyTransfersRepository, times(1)).save(eq(new YearlyTransfers(DATE.getYear(), 1)));
        verify(mongoOperations, times(1)).updateFirst(
                eq(new Query(Criteria
                        .where("year").is(DATE.getYear()))),
                eq(new Update().inc("transfers", 1)),
                eq(YearlyTransfers.class));
        verify(message, times(1)).getPayload();
        verify(bankStatement, atLeast(1)).getDate();
    }

    @Test
    void incrementYearlyTransfers_withAnotherException_doThrowException() {
        //given
        stubMessage();
        final String exceptionMessage = "any Message";
        when(yearlyTransfersRepository.save(any(YearlyTransfers.class))).thenThrow(new IllegalStateException(exceptionMessage));
        //when
        final MessageHandler messageHandler = configuration.incrementYearlyTransfers();
        final Throwable exception = assertThrows(IllegalStateException.class, () -> messageHandler.handleMessage(message));
        //then
        assertThat(exceptionMessage, equalTo(exception.getMessage()));
        verify(yearlyTransfersRepository, times(1)).save(eq(new YearlyTransfers(DATE.getYear(), 1)));
        verifyNoMoreInteractions(mongoOperations);
        verify(message, times(1)).getPayload();
        verify(bankStatement, atLeast(1)).getDate();
    }

    @Test
    void updateLastIBANTransfer() {
    }

    @Test
    void updateLastIBANTransfer_withNewIBAN_insertsBankTransferData() {
        //given
        stubMessage();
        when(lastIBANTransferRepository.save(any(LastIBANTransfer.class))).thenReturn(null);
        //when
        final MessageHandler messageHandler = configuration.updateLastIBANTransfer();
        messageHandler.handleMessage(message);
        //then
        verify(lastIBANTransferRepository, times(1)).save(eq(new LastIBANTransfer(IBAN, DNI, CURRENCY, AMOUNT, DATE)));
        verifyNoMoreInteractions(mongoOperations);
        verify(message, times(1)).getPayload();
        verify(bankStatement, atLeast(1)).getIban();
        verify(bankStatement, atLeast(1)).getDni();
        verify(bankStatement, atLeast(1)).getCurrency();
        verify(bankStatement, atLeast(1)).getAmount();
        verify(bankStatement, atLeast(1)).getDate();
    }

    @Test
    void updateLastIBANTransfer_withExistingIBAN_updatesBankTransferDataIfDateIsGreaterThanExistingOne() {
        //given
        stubMessage();
        when(lastIBANTransferRepository.save(any(LastIBANTransfer.class))).thenThrow(new DuplicateKeyException("any Message"));
        when(mongoOperations.updateFirst(any(Query.class), any(Update.class), any(Class.class))).thenReturn(null);;
        //when
        final MessageHandler messageHandler = configuration.updateLastIBANTransfer();
        messageHandler.handleMessage(message);
        //then
        verify(lastIBANTransferRepository, times(1)).save(eq(new LastIBANTransfer(IBAN, DNI, CURRENCY, AMOUNT, DATE)));
        verify(mongoOperations, times(1)).updateFirst(
                eq(new Query(Criteria
                                .where("iban").is(IBAN)
                                .and("date").lt(DATE)
                        )),
                eq(new Update()
                        .set("dni", DNI)
                        .set("currency", CURRENCY)
                        .set("amount", AMOUNT)
                        .set("date", DATE)),
                eq(LastIBANTransfer.class));
        verify(message, times(1)).getPayload();
        verify(bankStatement, atLeast(1)).getIban();
        verify(bankStatement, atLeast(1)).getDni();
        verify(bankStatement, atLeast(1)).getCurrency();
        verify(bankStatement, atLeast(1)).getAmount();
        verify(bankStatement, atLeast(1)).getDate();
    }

    @Test
    void updateLastIBANTransfer_withAnotherException_doThrowException() {
        //given
        stubMessage();
        final String exceptionMessage = "any Message";
        when(lastIBANTransferRepository.save(any(LastIBANTransfer.class))).thenThrow(new IllegalStateException(exceptionMessage));
        //when
        final MessageHandler messageHandler = configuration.updateLastIBANTransfer();
        final Throwable exception = assertThrows(IllegalStateException.class, () -> messageHandler.handleMessage(message));
        //then
        assertThat(exceptionMessage, equalTo(exception.getMessage()));
        verify(lastIBANTransferRepository, times(1)).save(eq(new LastIBANTransfer(IBAN, DNI, CURRENCY, AMOUNT, DATE)));
        verifyNoMoreInteractions(mongoOperations);
        verify(message, times(1)).getPayload();
        verify(bankStatement, atLeast(1)).getIban();
        verify(bankStatement, atLeast(1)).getDni();
        verify(bankStatement, atLeast(1)).getCurrency();
        verify(bankStatement, atLeast(1)).getAmount();
        verify(bankStatement, atLeast(1)).getDate();
    }

    @Test
    void saveCurrency_withNewCurrency_insertsCurrency() {
        //given
        stubMessage();
        when(currencyRepository.save(any(Currency.class))).thenReturn(null);
        //when
        final MessageHandler messageHandler = configuration.saveCurrency();
        messageHandler.handleMessage(message);
        //then
        verify(currencyRepository, times(1)).save(eq(new Currency(CURRENCY)));
        verify(message, times(1)).getPayload();
        verify(bankStatement, times(1)).getCurrency();
    }

    @Test
    void saveCurrency_withExistingCurrency_doNotThrowException() {
        //given
        stubMessage();
        when(currencyRepository.save(any(Currency.class))).thenThrow(new DuplicateKeyException("any Message"));
        //when
        final MessageHandler messageHandler = configuration.saveCurrency();
        messageHandler.handleMessage(message);
        //then
        verify(currencyRepository, times(1)).save(eq(new Currency(CURRENCY)));
        verify(message, times(1)).getPayload();
        verify(bankStatement, times(1)).getCurrency();
    }

    @Test
    void saveCurrency_withAnotherException_doThrowException() {
        //given
        stubMessage();
        final String exceptionMessage = "any Message";
        when(currencyRepository.save(any(Currency.class))).thenThrow(new IllegalStateException(exceptionMessage));
        //when
        final MessageHandler messageHandler = configuration.saveCurrency();
        final Throwable exception = assertThrows(IllegalStateException.class, () -> messageHandler.handleMessage(message));
        //then
        assertThat(exceptionMessage, equalTo(exception.getMessage()));
        verify(currencyRepository, times(1)).save(eq(new Currency(CURRENCY)));
        verify(message, times(1)).getPayload();
        verify(bankStatement, times(1)).getCurrency();
    }
}