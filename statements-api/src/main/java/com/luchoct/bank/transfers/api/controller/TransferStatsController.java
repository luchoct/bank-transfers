package com.luchoct.bank.transfers.api.controller;


import com.luchoct.bank.transfers.api.entity.DailyTransfers;
import com.luchoct.bank.transfers.api.entity.MonthlyTransfers;
import com.luchoct.bank.transfers.api.entity.YearlyTransfers;
import com.luchoct.bank.transfers.api.repository.DailyTransfersRepository;
import com.luchoct.bank.transfers.api.repository.MonthlyTransfersRepository;
import com.luchoct.bank.transfers.api.repository.YearlyTransfersRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping
public class TransferStatsController {
    private final DailyTransfersRepository dailyTransfersRepository;
    private final MonthlyTransfersRepository monthlyTransfersRepository;
    private final YearlyTransfersRepository yearlyTransfersRepository;

    public TransferStatsController(
            final DailyTransfersRepository dailyTransfersRepository,
            final MonthlyTransfersRepository monthlyTransfersRepository,
            final YearlyTransfersRepository yearlyTransfersRepository) {
        this.dailyTransfersRepository = dailyTransfersRepository;
        this.monthlyTransfersRepository = monthlyTransfersRepository;
        this.yearlyTransfersRepository = yearlyTransfersRepository;
    }

    @GetMapping("/transfers/{year}")
    public Integer getYearlyTransfers(
            @PathVariable("year") final Integer year) {
        final YearlyTransfers transfers = yearlyTransfersRepository.findByYear(year);
        return (transfers == null) ? 0 : transfers.getTransfers();
    }

    @GetMapping("/transfers/{year}/{month}")
    public Integer getMonthlyTransfers(
            @PathVariable("year") final Integer year,
            @PathVariable("month") final Integer month) {
        final MonthlyTransfers transfers = monthlyTransfersRepository.findByMonthAndYear(month, year);
        return (transfers == null) ? 0 : transfers.getTransfers();
    }

    @GetMapping("/transfers/{year}/{month}/{day}")
    public Integer getDailyTransfers(
            @PathVariable("year") final Integer year,
            @PathVariable("month") final Integer month,
            @PathVariable("day") final Integer day) {
        final DailyTransfers transfers = dailyTransfersRepository.findByDayAndMonthAndYear(day, month, year);
        return (transfers == null) ? 0 : transfers.getTransfers();
    }

    @GetMapping("/months/{year}/max-transfers")
    public MonthlyTransfers getMaximumMonthlyTransfers(
            @PathVariable("year") final Integer year) {
        final PageRequest request = PageRequest.of(0, 1, Sort.Direction.DESC, "transfers");
        final Page<MonthlyTransfers> page = monthlyTransfersRepository.findByYear(year, request);
        if (page.getTotalElements() == 0) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Year without month with max transfers"
            );
        } else {
            return page.getContent().get(0);
        }
    }

    @GetMapping("/months/max-transfers")
    public MonthlyTransfers getMaximumMonthlyTransfersEver() {
        final PageRequest request = PageRequest.of(0, 1, Sort.Direction.DESC, "transfers");
        final Page<MonthlyTransfers> page = monthlyTransfersRepository.findAll(request);
        if (page.getTotalElements() == 0) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No month with max transfers"
            );
        } else {
            return page.getContent().get(0);
        }
    }
}
