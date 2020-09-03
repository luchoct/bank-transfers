package com.luchoct.bank.transfers.api.controller;

import com.luchoct.bank.transfers.api.entity.LastIBANTransfer;
import com.luchoct.bank.transfers.api.repository.LastIBANTransferRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IBANControllerTest {

    private static final String IBAN = "any IBAN";

    @Mock
    private LastIBANTransferRepository repository;

    @InjectMocks
    private IBANController controller;

    @Test
    void getLastIBANTransfer_whenIBANDoesHaveLastTransfer_returnsIt() {
        //given
        final LastIBANTransfer lastIBANTransfer = mock(LastIBANTransfer.class);
        when(repository.findByIban(anyString())).thenReturn(lastIBANTransfer);
        //when
        final LastIBANTransfer returnedLastIBANTransfer = controller.getLastIBANTransfer(IBAN);
        //then
        verify(repository, times(1)).findByIban(same(IBAN));
        assertThat(returnedLastIBANTransfer, sameInstance(lastIBANTransfer));
    }

    @Test
    void getLastIBANTransfer_whenIBANDoesNotHaveLastTransfer_returns404() {
        //given
        final LastIBANTransfer lastIBANTransfer = mock(LastIBANTransfer.class);
        when(repository.findByIban(anyString())).thenReturn(null);
        //when
        final Throwable exception = assertThrows(ResponseStatusException.class, () -> controller.getLastIBANTransfer(IBAN));

        //then
        verify(repository, times(1)).findByIban(same(IBAN));
        assertThat(((ResponseStatusException)exception).getStatus(), equalTo(HttpStatus.NOT_FOUND));
    }
}