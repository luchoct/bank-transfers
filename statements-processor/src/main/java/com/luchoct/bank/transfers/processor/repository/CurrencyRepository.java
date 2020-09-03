package com.luchoct.bank.transfers.processor.repository;

import com.luchoct.bank.transfers.processor.entity.Currency;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends PagingAndSortingRepository<Currency, Long> {
}
