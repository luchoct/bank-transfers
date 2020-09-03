package com.luchoct.bank.transfers.processor.repository;

import com.luchoct.bank.transfers.processor.entity.YearlyTransfers;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YearlyTransfersRepository extends PagingAndSortingRepository<YearlyTransfers, Long> {
}
