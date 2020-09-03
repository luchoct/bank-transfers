package com.luchoct.bank.transfers.api.controller;

import com.luchoct.bank.transfers.api.entity.DailyTransfers;
import com.luchoct.bank.transfers.api.entity.MonthlyTransfers;
import com.luchoct.bank.transfers.api.entity.YearlyTransfers;
import com.luchoct.bank.transfers.api.repository.DailyTransfersRepository;
import com.luchoct.bank.transfers.api.repository.MonthlyTransfersRepository;
import com.luchoct.bank.transfers.api.repository.YearlyTransfersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransferStatsControllerTest {

    private static final Integer NUM_TRANSFERS = 54;
    private static final Integer YEAR = 1934;
    private static final Integer MONTH = 7;
    private static final Integer DAY = 23;

    @Mock
    private DailyTransfersRepository dailyTransfersRepository;
    @Mock
    private MonthlyTransfersRepository monthlyTransfersRepository;
    @Mock
    private YearlyTransfersRepository yearlyTransfersRepository;

    @InjectMocks
    private TransferStatsController controller;

    @Test
    void getYearlyTransfers_whenThereAreTransfers_returnsNumTransfers() {
        //given
        final YearlyTransfers yearlyTransfers = mock(YearlyTransfers.class);
        when(yearlyTransfers.getTransfers()).thenReturn(NUM_TRANSFERS);
        when(yearlyTransfersRepository.findByYear(any(Integer.class))).thenReturn(yearlyTransfers);
        //when
        final Integer returnedNumTransfers = controller.getYearlyTransfers(YEAR);
        //then
        verify(yearlyTransfersRepository, times(1)).findByYear(same(YEAR));
        verify(yearlyTransfers, atLeast(1)).getTransfers();
        assertThat(returnedNumTransfers, sameInstance(NUM_TRANSFERS));
    }

    @Test
    void getYearlyTransfers_whenThereAreNotTransfers_returns0() {
        //given
        when(yearlyTransfersRepository.findByYear(any(Integer.class))).thenReturn(null);
        //when
        final Integer returnedNumTransfers = controller.getYearlyTransfers(YEAR);
        //then
        verify(yearlyTransfersRepository, times(1)).findByYear(same(YEAR));
        assertThat(returnedNumTransfers, equalTo(0));
    }

    @Test
    void getMonthlyTransfers_whenThereAreTransfers_returnsNumTransfers() {
        //given
        final MonthlyTransfers monthlyTransfers = mock(MonthlyTransfers.class);
        when(monthlyTransfers.getTransfers()).thenReturn(NUM_TRANSFERS);
        when(monthlyTransfersRepository.findByMonthAndYear(any(Integer.class), any(Integer.class))).thenReturn(monthlyTransfers);
        //when
        final Integer returnedNumTransfers = controller.getMonthlyTransfers(YEAR, MONTH);
        //then
        verify(monthlyTransfersRepository, times(1)).findByMonthAndYear(same(MONTH), same(YEAR));
        verify(monthlyTransfers, atLeast(1)).getTransfers();
        assertThat(returnedNumTransfers, sameInstance(NUM_TRANSFERS));
    }

    @Test
    void getMonthlyTransfers_whenThereAreNotTransfers_returns0() {
        //given
        when(monthlyTransfersRepository.findByMonthAndYear(any(Integer.class), any(Integer.class))).thenReturn(null);
        //when
        final Integer returnedNumTransfers = controller.getMonthlyTransfers(YEAR, MONTH);
        //then
        verify(monthlyTransfersRepository, times(1)).findByMonthAndYear(same(MONTH), same(YEAR));
        assertThat(returnedNumTransfers, equalTo(0));
    }

    @Test
    void getDailyTransfers_whenThereAreTransfers_returnsNumTransfers() {
        //given
        final DailyTransfers dailyTransfers = mock(DailyTransfers.class);
        when(dailyTransfers.getTransfers()).thenReturn(NUM_TRANSFERS);
        when(dailyTransfersRepository.findByDayAndMonthAndYear(any(Integer.class), any(Integer.class), any(Integer.class))).thenReturn(dailyTransfers);
        //when
        final Integer returnedNumTransfers = controller.getDailyTransfers(YEAR, MONTH, DAY);
        //then
        verify(dailyTransfersRepository, times(1)).findByDayAndMonthAndYear(same(DAY), same(MONTH), same(YEAR));
        verify(dailyTransfers, atLeast(1)).getTransfers();
        assertThat(returnedNumTransfers, sameInstance(NUM_TRANSFERS));
    }

    @Test
    void getDailyTransfers_whenThereAreNotTransfers_returns0() {
        //given
        when(dailyTransfersRepository.findByDayAndMonthAndYear(any(Integer.class), any(Integer.class), any(Integer.class))).thenReturn(null);
        //when
        final Integer returnedNumTransfers = controller.getDailyTransfers(YEAR, MONTH, DAY);
        //then
        verify(dailyTransfersRepository, times(1)).findByDayAndMonthAndYear(same(DAY), same(MONTH), same(YEAR));
        assertThat(returnedNumTransfers, equalTo(0));
    }

    @Test
    void getMaximumMonthlyTransfers_whenThereAreMonthsWithTransfers_returnsMaximumNumOfTransfers() {
        //given
        final MonthlyTransfers monthlyTransfers = mock(MonthlyTransfers.class);
        final Page<MonthlyTransfers> page = mock(Page.class, Answers.RETURNS_DEEP_STUBS);
        when(page.getTotalElements()).thenReturn(1L);
        when(page.getContent().get(anyInt())).thenReturn(monthlyTransfers);
        when(monthlyTransfersRepository.findByYear(any(Integer.class), any(Pageable.class))).thenReturn(page);
        //when
        final MonthlyTransfers returnedMonthlyTransfers = controller.getMaximumMonthlyTransfers(YEAR);
        //then
        verify(monthlyTransfersRepository, times(1)).findByYear(same(YEAR), eq(PageRequest.of(0, 1, Sort.Direction.DESC, "transfers")));
        verify(page, times(1)).getTotalElements();
        verify(page, atLeast(1)).getContent();
        verify(page.getContent(), times(1)).get(eq(0));
        assertThat(returnedMonthlyTransfers, sameInstance(monthlyTransfers));
    }

    @Test
    void getMaximumMonthlyTransfers_whenThereAreNotMonthsWithTransfers_returns404() {
        //given
        final Page<MonthlyTransfers> page = mock(Page.class, Answers.RETURNS_DEEP_STUBS);
        when(page.getTotalElements()).thenReturn(0L);
        when(monthlyTransfersRepository.findByYear(any(Integer.class), any(Pageable.class))).thenReturn(page);
        //when
        final Throwable exception = assertThrows(ResponseStatusException.class, () -> controller.getMaximumMonthlyTransfers(YEAR));
        //then
        verify(monthlyTransfersRepository, times(1)).findByYear(same(YEAR), eq(PageRequest.of(0, 1, Sort.Direction.DESC, "transfers")));
        verify(page, times(1)).getTotalElements();
        assertThat(((ResponseStatusException)exception).getStatus(), equalTo(HttpStatus.NOT_FOUND));
    }

    @Test
    void getMaximumMonthlyTransfersEver_whenThereAreMonthsWithTransfers_returnsMaximumNumOfTransfers() {
        //given
        final MonthlyTransfers monthlyTransfers = mock(MonthlyTransfers.class);
        final Page<MonthlyTransfers> page = mock(Page.class, Answers.RETURNS_DEEP_STUBS);
        when(page.getTotalElements()).thenReturn(1L);
        when(page.getContent().get(anyInt())).thenReturn(monthlyTransfers);
        when(monthlyTransfersRepository.findAll(any(Pageable.class))).thenReturn(page);
        //when
        final MonthlyTransfers returnedMonthlyTransfers = controller.getMaximumMonthlyTransfersEver();
        //then
        verify(monthlyTransfersRepository, times(1)).findAll(eq(PageRequest.of(0, 1, Sort.Direction.DESC, "transfers")));
        verify(page, times(1)).getTotalElements();
        verify(page, atLeast(1)).getContent();
        verify(page.getContent(), times(1)).get(eq(0));
        assertThat(returnedMonthlyTransfers, sameInstance(monthlyTransfers));
    }

    @Test
    void getMaximumMonthlyTransfersEver_whenThereAreNotMonthsWithTransfers_returns404() {
        //given
        final Page<MonthlyTransfers> page = mock(Page.class, Answers.RETURNS_DEEP_STUBS);
        when(page.getTotalElements()).thenReturn(0L);
        when(monthlyTransfersRepository.findAll(any(Pageable.class))).thenReturn(page);
        //when
        final Throwable exception = assertThrows(ResponseStatusException.class, () -> controller.getMaximumMonthlyTransfersEver());
        //then
        verify(monthlyTransfersRepository, times(1)).findAll(eq(PageRequest.of(0, 1, Sort.Direction.DESC, "transfers")));
        verify(page, times(1)).getTotalElements();
        assertThat(((ResponseStatusException)exception).getStatus(), equalTo(HttpStatus.NOT_FOUND));
    }
}