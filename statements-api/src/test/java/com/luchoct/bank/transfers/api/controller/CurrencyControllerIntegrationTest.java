package com.luchoct.bank.transfers.api.controller;

import com.luchoct.bank.transfers.api.entity.Currency;
import com.luchoct.bank.transfers.api.repository.CurrencyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CurrencyController.class)
@ActiveProfiles("test")
class CurrencyControllerIntegrationTest {

    @MockBean
    private CurrencyRepository repository;

    @Autowired
    private MockMvc mvc;

    @Test
    public void getCurrencies_returnsAllCurrencies() throws Exception {
        //given
        when(repository.findAll()).thenReturn(List.of(new Currency("any currency")));
        //when
        final ResultActions resultActions = mvc.perform(get("/currencies"));
        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().json("[{"
                        + "\"symbol\":\"any currency\"}]", true));
        verify(repository, times(1)).findAll();
    }
}