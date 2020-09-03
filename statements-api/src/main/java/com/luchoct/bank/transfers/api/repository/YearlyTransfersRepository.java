package com.luchoct.bank.transfers.api.repository;

import com.luchoct.bank.transfers.api.entity.YearlyTransfers;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YearlyTransfersRepository extends PagingAndSortingRepository<YearlyTransfers, Long> {
    YearlyTransfers findByYear(Integer year);
}
