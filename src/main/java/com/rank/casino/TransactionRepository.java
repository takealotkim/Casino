package com.rank.casino;

import com.rank.casino.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "select * from Transaction t WHERE t.player_id = ?1 orderby timestamp limit 10",
            nativeQuery = true)
    List<Transaction> listTransactions(Long playerId);
}