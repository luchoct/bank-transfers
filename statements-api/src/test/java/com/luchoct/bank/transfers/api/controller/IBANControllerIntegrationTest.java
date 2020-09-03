package com.luchoct.bank.transfers.api.controller;

import com.luchoct.bank.transfers.api.entity.LastIBANTransfer;
import com.luchoct.bank.transfers.api.repository.LastIBANTransferRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.time.Instant;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(IBANController.class)
@ActiveProfiles("test")
class IBANControllerIntegrationTest {

    private static final String IBAN = "any IBAN";

    @MockBean
    private LastIBANTransferRepository repository;

    @Autowired
    private MockMvc mvc;

    @Test
    void getLastIBANTransfer_whenIBANDoesHaveLastTransfer_returnsIt() throws Exception {
        //given
        final Instant instant = Instant.now();
        when(repository.findByIban(anyString())).thenReturn(new LastIBANTransfer("any IBAN", "any DNI", "any Currency", new BigDecimal("23.45"), instant.toString()));
        //when
        final ResultActions resultActions = mvc.perform(get("/iban/any-IBAN/last-transfer"));
        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().json("{"
                        + "\"iban\":\"any IBAN\",\"dni\":\"any DNI\",\"currency\":\"any Currency\",\"amount\":23.45,\"date\":\"" + instant.toString() + "\"}]", true));
        verify(repository, times(1)).findByIban(eq("any-IBAN"));
    }

    @Test
    void getLastIBANTransfer_whenIBANDoesNotHaveLastTransfer_returns404() throws Exception {
        //given
        final Instant instant = Instant.now();
        when(repository.findByIban(anyString())).thenReturn(null);
        //when
        final ResultActions resultActions = mvc.perform(get("/iban/any-IBAN/last-transfer"));

        //then
        resultActions
                .andExpect(status().isNotFound());
        verify(repository, times(1)).findByIban(eq("any-IBAN"));
    }
}