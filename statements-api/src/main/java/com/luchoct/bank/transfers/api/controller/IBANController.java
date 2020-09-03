package com.luchoct.bank.transfers.api.controller;


import com.luchoct.bank.transfers.api.entity.LastIBANTransfer;
import com.luchoct.bank.transfers.api.repository.LastIBANTransferRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/iban")
public class IBANController {

    private final LastIBANTransferRepository repository;

    public IBANController(final LastIBANTransferRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{iban}/last-transfer")
    public LastIBANTransfer getLastIBANTransfer(@PathVariable("iban") final String iban) {
        final LastIBANTransfer lastIBANTransfer = repository.findByIban(iban);
        if (lastIBANTransfer == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "IBAN without last transfer"
            );
        } else {
            return lastIBANTransfer;
        }
    }
}
