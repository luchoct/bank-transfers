package com.luchoct.bank.transfers.processor.repository;

import com.luchoct.bank.transfers.processor.entity.LastIBANTransfer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LastIBANTransferRepository extends PagingAndSortingRepository<LastIBANTransfer, Long> {
}
