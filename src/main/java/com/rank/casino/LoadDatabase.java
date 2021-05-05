package com.rank.casino;

import com.rank.casino.model.Player;
import com.rank.casino.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Date;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(PlayerRepository repository, TransactionRepository transactionRepository) {
        return args -> {
            log.info("Preloading " + repository.save(new Player(0.00, "Donald")));
            log.info("Preloading " + repository.save(new Player(100.0, "Kim")));
            log.info("Preloading " + repository.save(new Player(10.0, "George")));
            log.info("Preloading " + repository.save(new Player(800.0, "Bird")));
            log.info("Preloading " + repository.save(new Player(99.0, "Lulu")));
            log.info("Preloading " + repository.save(new Player(1000.0, "Sharon")));
            log.info("Preloading " + repository.save(new Player(1000.0, "Karl")));
            log.info("Preloading " + transactionRepository.save(new Transaction(1L, 1000.0,1)));
            log.info("Preloading " + transactionRepository.save(new Transaction(2L, 88.0,2)));
            log.info("Preloading " + transactionRepository.save(new Transaction(3L, 200.0,3)));
            log.info("Preloading " + transactionRepository.save(new Transaction(4L, 808.0,4)));
            log.info("Preloading " + transactionRepository.save(new Transaction(5L, 4.0,5)));
            log.info("Preloading " + transactionRepository.save(new Transaction(6L, 8.0,6)));
        };
    }
}