package com.luchoct.bank.transfers.api.repository;

import com.luchoct.bank.transfers.api.entity.LastIBANTransfer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LastIBANTransferRepository extends PagingAndSortingRepository<LastIBANTransfer, Long> {
    LastIBANTransfer findByIban(String iban);
}
