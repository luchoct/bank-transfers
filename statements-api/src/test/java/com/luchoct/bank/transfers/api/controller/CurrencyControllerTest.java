package com.luchoct.bank.transfers.api.controller;

import com.luchoct.bank.transfers.api.entity.Currency;
import com.luchoct.bank.transfers.api.repository.CurrencyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrencyControllerTest {

    @Mock
    private CurrencyRepository repository;

    @InjectMocks
    private CurrencyController controller;

    @Test
    void getCurrencies_returnsAllCurrencies() {
        //given
        final Iterable<Currency> currencies = mock(List.class);
        when(repository.findAll()).thenReturn(currencies);
        //when
        final Iterable<Currency> returnedCurrencies = controller.getCurrencies();
        //then
        verify(repository, times(1)).findAll();
        assertThat(returnedCurrencies, sameInstance(currencies));
    }
}