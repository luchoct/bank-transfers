package com.luchoct.bank.transfers.api.repository;

import com.luchoct.bank.transfers.api.entity.DailyTransfers;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyTransfersRepository extends PagingAndSortingRepository<DailyTransfers, Long> {
    DailyTransfers findByDayAndMonthAndYear(Integer day, Integer month, Integer year);
}
