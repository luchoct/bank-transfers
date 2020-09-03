package com.luchoct.bank.transfers.api.controller;


import com.luchoct.bank.transfers.api.entity.Currency;
import com.luchoct.bank.transfers.api.repository.CurrencyRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    private CurrencyRepository repository;

    public CurrencyController(final CurrencyRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public Iterable<Currency> getCurrencies() {
        return repository.findAll();
    }
}
