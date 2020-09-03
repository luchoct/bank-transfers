package com.luchoct.bank.transfers.api.repository;

import com.luchoct.bank.transfers.api.entity.MonthlyTransfers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthlyTransfersRepository extends PagingAndSortingRepository<MonthlyTransfers, Long> {
    MonthlyTransfers findByMonthAndYear(Integer month, Integer year);

    Page<MonthlyTransfers> findByYear(Integer year, Pageable pageable);

    Page<MonthlyTransfers> findAll(Pageable pageable);
}
