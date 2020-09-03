package com.luchoct.bank.transfers.api.controller;

import com.luchoct.bank.transfers.api.entity.DailyTransfers;
import com.luchoct.bank.transfers.api.entity.MonthlyTransfers;
import com.luchoct.bank.transfers.api.entity.YearlyTransfers;
import com.luchoct.bank.transfers.api.repository.DailyTransfersRepository;
import com.luchoct.bank.transfers.api.repository.MonthlyTransfersRepository;
import com.luchoct.bank.transfers.api.repository.YearlyTransfersRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransferStatsController.class)
@ActiveProfiles("test")
class TransferStatsControllerIntegrationTest {

    private static final Integer NUM_TRANSFERS = 54;
    private static final Integer YEAR = 1934;
    private static final Integer MONTH = 7;
    private static final Integer DAY = 23;

    @MockBean
    private DailyTransfersRepository dailyTransfersRepository;
    @MockBean
    private MonthlyTransfersRepository monthlyTransfersRepository;
    @MockBean
    private YearlyTransfersRepository yearlyTransfersRepository;

    @Autowired
    private MockMvc mvc;

    @Test
    void getYearlyTransfers_whenThereAreTransfers_returnsNumTransfers() throws Exception {
        //given
        when(yearlyTransfersRepository.findByYear(any(Integer.class))).thenReturn(
                new YearlyTransfers(YEAR, NUM_TRANSFERS));
        //when
        final ResultActions resultActions = mvc.perform(get("/transfers/" + YEAR));
        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(NUM_TRANSFERS.toString()));
        verify(yearlyTransfersRepository, times(1)).findByYear(eq(YEAR));
    }

    @Test
    void getYearlyTransfers_whenThereAreNotTransfers_returns0() throws Exception {
        //given
        when(yearlyTransfersRepository.findByYear(any(Integer.class))).thenReturn(null);
        //when
        final ResultActions resultActions = mvc.perform(get("/transfers/" + YEAR));
        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string("0"));
        verify(yearlyTransfersRepository, times(1)).findByYear(eq(YEAR));
    }

    @Test
    void getMonthlyTransfers_whenThereAreTransfers_returnsNumTransfers() throws Exception {
        //given
        when(monthlyTransfersRepository.findByMonthAndYear(any(Integer.class), any(Integer.class))).thenReturn(
                new MonthlyTransfers(MONTH, YEAR, NUM_TRANSFERS));
        //when
        final ResultActions resultActions = mvc.perform(get(String.format("/transfers/%d/%d", YEAR, MONTH)));
        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(NUM_TRANSFERS.toString()));
        verify(monthlyTransfersRepository, times(1)).findByMonthAndYear(eq(MONTH), eq(YEAR));
    }

    @Test
    void getMonthlyTransfers_whenThereAreNotTransfers_returns0() throws Exception {
        //given
        when(monthlyTransfersRepository.findByMonthAndYear(any(Integer.class), any(Integer.class))).thenReturn(null);
        //when
        final ResultActions resultActions = mvc.perform(get(String.format("/transfers/%d/%d", YEAR, MONTH)));
        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string("0"));
        verify(monthlyTransfersRepository, times(1)).findByMonthAndYear(eq(MONTH), eq(YEAR));
    }

    @Test
    void getDailyTransfers_whenThereAreTransfers_returnsNumTransfers() throws Exception {
        //given
        final DailyTransfers dailyTransfers = mock(DailyTransfers.class);
        when(dailyTransfers.getTransfers()).thenReturn(NUM_TRANSFERS);
        when(dailyTransfersRepository.findByDayAndMonthAndYear(any(Integer.class), any(Integer.class), any(Integer.class))).thenReturn(
                new DailyTransfers(DAY, MONTH, YEAR, NUM_TRANSFERS));
        //when
        final ResultActions resultActions = mvc.perform(get(String.format("/transfers/%d/%d/%d", YEAR, MONTH, DAY)));
        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(NUM_TRANSFERS.toString()));
        verify(dailyTransfersRepository, times(1)).findByDayAndMonthAndYear(eq(DAY), eq(MONTH), eq(YEAR));
    }

    @Test
    void getDailyTransfers_whenThereAreNotTransfers_returns0() throws Exception {
        //given
        when(dailyTransfersRepository.findByDayAndMonthAndYear(any(Integer.class), any(Integer.class), any(Integer.class))).thenReturn(null);
        //when
        final ResultActions resultActions = mvc.perform(get(String.format("/transfers/%d/%d/%d", YEAR, MONTH, DAY)));
        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string("0"));
        verify(dailyTransfersRepository, times(1)).findByDayAndMonthAndYear(eq(DAY), eq(MONTH), eq(YEAR));
    }

    @Test
    void getMaximumMonthlyTransfers_whenThereAreMonthsWithTransfers_returnsMaximumNumOfTransfers() throws Exception {
        //given
        final Page<MonthlyTransfers> page = mock(Page.class, Answers.RETURNS_DEEP_STUBS);
        when(page.getTotalElements()).thenReturn(2L);
        when(page.getContent().get(eq(0))).thenReturn(new MonthlyTransfers(MONTH, YEAR, NUM_TRANSFERS));
        when(page.getContent().get(eq(1))).thenReturn(new MonthlyTransfers(MONTH + 1, YEAR, NUM_TRANSFERS - 1));
        when(monthlyTransfersRepository.findByYear(any(Integer.class), any(Pageable.class))).thenReturn(page);
        //when
        final ResultActions resultActions = mvc.perform(get(String.format("/months/%d/max-transfers", YEAR)));
        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().json(String.format("{"
                        + "\"month\":%d,\"year\":%d,\"transfers\":%d}]", MONTH, YEAR, NUM_TRANSFERS), true));
        verify(monthlyTransfersRepository, times(1)).findByYear(eq(YEAR), eq(PageRequest.of(0, 1, Sort.Direction.DESC, "transfers")));
    }

    @Test
    void getMaximumMonthlyTransfers_whenThereAreNotMonthsWithTransfers_returns404() throws Exception {
        //given
        final Page<MonthlyTransfers> page = mock(Page.class, Answers.RETURNS_DEEP_STUBS);
        when(page.getTotalElements()).thenReturn(0L);
        when(monthlyTransfersRepository.findByYear(any(Integer.class), any(Pageable.class))).thenReturn(page);
        //when
        final ResultActions resultActions = mvc.perform(get(String.format("/months/%d/max-transfers", YEAR)));
        //then
        resultActions
                .andExpect(status().isNotFound());
        verify(monthlyTransfersRepository, times(1)).findByYear(eq(YEAR), eq(PageRequest.of(0, 1, Sort.Direction.DESC, "transfers")));
    }

    @Test
    void getMaximumMonthlyTransfersEver_whenThereAreMonthsWithTransfers_returnsMaximumNumOfTransfers() throws Exception {
        //given
        final Page<MonthlyTransfers> page = mock(Page.class, Answers.RETURNS_DEEP_STUBS);
        when(page.getTotalElements()).thenReturn(2L);
        when(page.getContent().get(eq(0))).thenReturn(new MonthlyTransfers(MONTH, YEAR, NUM_TRANSFERS));
        when(page.getContent().get(eq(1))).thenReturn(new MonthlyTransfers(MONTH + 1, YEAR - 2, NUM_TRANSFERS - 1));
        when(monthlyTransfersRepository.findAll(any(Pageable.class))).thenReturn(page);
        //when
        final ResultActions resultActions = mvc.perform(get(String.format("/months/max-transfers", YEAR)));
        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().json(String.format("{"
                        + "\"month\":%d,\"year\":%d,\"transfers\":%d}]", MONTH, YEAR, NUM_TRANSFERS), true));
        verify(monthlyTransfersRepository, times(1)).findAll(eq(PageRequest.of(0, 1, Sort.Direction.DESC, "transfers")));
    }

    @Test
    void getMaximumMonthlyTransfersEver_whenThereAreNotMonthsWithTransfers_returns404() throws Exception {
        //given
        final Page<MonthlyTransfers> page = mock(Page.class, Answers.RETURNS_DEEP_STUBS);
        when(page.getTotalElements()).thenReturn(0L);
        when(monthlyTransfersRepository.findAll(any(Pageable.class))).thenReturn(page);
        //when
        final ResultActions resultActions = mvc.perform(get(String.format("/months/max-transfers", YEAR)));
        //then
        resultActions
                .andExpect(status().isNotFound());
        verify(monthlyTransfersRepository, times(1)).findAll(eq(PageRequest.of(0, 1, Sort.Direction.DESC, "transfers")));
    }
}