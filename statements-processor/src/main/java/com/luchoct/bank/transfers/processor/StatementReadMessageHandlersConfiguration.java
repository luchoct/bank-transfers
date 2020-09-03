package com.luchoct.bank.transfers.processor;

import com.luchoct.bank.transfers.processor.entity.Currency;
import com.luchoct.bank.transfers.processor.entity.DailyTransfers;
import com.luchoct.bank.transfers.processor.entity.LastIBANTransfer;
import com.luchoct.bank.transfers.processor.entity.MonthlyTransfers;
import com.luchoct.bank.transfers.processor.entity.YearlyTransfers;
import com.luchoct.bank.transfers.processor.repository.CurrencyRepository;
import com.luchoct.bank.transfers.processor.repository.DailyTransfersRepository;
import com.luchoct.bank.transfers.processor.repository.LastIBANTransferRepository;
import com.luchoct.bank.transfers.processor.repository.MonthlyTransfersRepository;
import com.luchoct.bank.transfers.processor.repository.YearlyTransfersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;

/**
 * All message handlers for statementsRead channel.
 * This channel receives a message once a correct {@link BankStatement} is read.
 */
@Configuration
public class StatementReadMessageHandlersConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatementReadMessageHandlersConfiguration.class);

    private final DailyTransfersRepository dailyTransfersRepository;
    private final MonthlyTransfersRepository monthlyTransfersRepository;
    private final YearlyTransfersRepository yearlyTransfersRepository;
    private final LastIBANTransferRepository lastIBANTransferRepository;
    private final CurrencyRepository currencyRepository;
    private final MongoOperations mongoOperations;

    public StatementReadMessageHandlersConfiguration(
            final DailyTransfersRepository dailyTransfersRepository,
            final MonthlyTransfersRepository monthlyTransfersRepository,
            final YearlyTransfersRepository yearlyTransfersRepository,
            final LastIBANTransferRepository lastIBANTransferRepository,
            final CurrencyRepository currencyRepository,
            final MongoOperations mongoOperations) {
        this.dailyTransfersRepository = dailyTransfersRepository;
        this.monthlyTransfersRepository = monthlyTransfersRepository;
        this.yearlyTransfersRepository = yearlyTransfersRepository;
        this.lastIBANTransferRepository = lastIBANTransferRepository;
        this.currencyRepository = currencyRepository;
        this.mongoOperations = mongoOperations;
    }

    @Bean
    @ServiceActivator(inputChannel = "statementRead")
    public MessageHandler incrementDailyTransfers() {
        return message -> {
            final BankStatement bankStatement = (BankStatement)message.getPayload();
            final Integer day = bankStatement.getDate().getDayOfMonth();
            final Integer month = bankStatement.getDate().getMonthValue();
            final Integer year = bankStatement.getDate().getYear();
            try {
                dailyTransfersRepository.save(new DailyTransfers(day, month, year, 1));
                LOGGER.debug("Daily Transfers: day {}, month {}, year {} : Counted first daily transfer", day, month, year);
            } catch (DuplicateKeyException dke) {
                mongoOperations.updateFirst(new Query(Criteria
                                .where("day").is(day)
                                .and("month").is(month)
                                .and("year").is(year)),
                        new Update()
                                .inc("transfers", 1),
                        DailyTransfers.class);
                LOGGER.debug("Daily Transfers: day {}, month {}, year {} : Incremented value", day, month, year);
            }
        };
    }

    @Bean
    @ServiceActivator(inputChannel = "statementRead")
    public MessageHandler incrementMonthlyTransfers() {
        return message -> {
            final BankStatement bankStatement = (BankStatement)message.getPayload();
            final Integer month = bankStatement.getDate().getMonthValue();
            final Integer year = bankStatement.getDate().getYear();
            try {
                monthlyTransfersRepository.save(new MonthlyTransfers(month, year, 1));
                LOGGER.debug("Monthly Transfers: month {}, year {} : Counted first daily transfer", month, year);
            } catch (DuplicateKeyException dke) {
                mongoOperations.updateFirst(new Query(Criteria
                                .where("month").is(month)
                                .and("year").is(year)),
                        new Update()
                                .inc("transfers", 1),
                        MonthlyTransfers.class);
                LOGGER.debug("Monthly Transfers: month {}, year {} : Incremented value", month, year);
            }
        };
    }

    @Bean
    @ServiceActivator(inputChannel = "statementRead")
    public MessageHandler incrementYearlyTransfers() {
        return message -> {
            final BankStatement bankStatement = (BankStatement)message.getPayload();
            final Integer year = bankStatement.getDate().getYear();
            try {
                yearlyTransfersRepository.save(new YearlyTransfers(year, 1));
                LOGGER.debug("Yearly Transfers: year {} : Counted first daily transfer", year);
            } catch (DuplicateKeyException dke) {
                mongoOperations.updateFirst(new Query(Criteria
                                .where("year").is(year)),
                        new Update()
                                .inc("transfers", 1),
                        YearlyTransfers.class);
                LOGGER.debug("Yearly Transfers: year {} : Incremented value", year);
            }
        };
    }

    @Bean
    @ServiceActivator(inputChannel = "statementRead")
    public MessageHandler updateLastIBANTransfer() {
        return message -> {
            final BankStatement bankStatement = (BankStatement)message.getPayload();
            final String iban = bankStatement.getIban();
            try {
                lastIBANTransferRepository.save(new LastIBANTransfer(bankStatement));
                LOGGER.debug("Last IBAN Transfer: IBAN {} : first transfer year {}", iban, bankStatement);
            } catch (DuplicateKeyException dke) {
                mongoOperations.updateFirst(new Query(Criteria
                                .where("iban").is(iban)
                                .and("date").lt(bankStatement.getDate())
                        ),
                        new Update()
                                .set("dni", bankStatement.getDni())
                                .set("currency", bankStatement.getCurrency())
                                .set("amount", bankStatement.getAmount())
                                .set("date", bankStatement.getDate()),
                        LastIBANTransfer.class);
                LOGGER.debug("Last IBAN Transfer: IBAN {} : updated to {}", iban, bankStatement);
            }
        };
    }

    @Bean
    @ServiceActivator(inputChannel = "statementRead")
    public MessageHandler saveCurrency() {
        return message -> {
            final BankStatement bankStatement = (BankStatement)message.getPayload();
            final String currency = bankStatement.getCurrency();
            try {
                currencyRepository.save(new Currency(currency));
                LOGGER.debug("Currency: {} : new currency", currency);
            } catch (DuplicateKeyException dke) {
                LOGGER.trace("Currency: {} : already stored", currency);
            }
        };
    }
}
