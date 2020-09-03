package com.luchoct.bank.transfers.processor.repository;

import com.luchoct.bank.transfers.processor.entity.MonthlyTransfers;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthlyTransfersRepository extends PagingAndSortingRepository<MonthlyTransfers, Long> {
}
