package com.luchoct.bank.transfers.api.repository;

import com.luchoct.bank.transfers.api.entity.Currency;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends PagingAndSortingRepository<Currency, Long> {
}
