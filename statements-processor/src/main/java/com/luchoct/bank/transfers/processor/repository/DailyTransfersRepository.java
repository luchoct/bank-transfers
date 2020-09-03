package com.luchoct.bank.transfers.processor.repository;

import com.luchoct.bank.transfers.processor.entity.DailyTransfers;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyTransfersRepository extends PagingAndSortingRepository<DailyTransfers, Long> {
}
