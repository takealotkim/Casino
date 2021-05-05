package com.rank.casino;

import com.rank.casino.model.Player;
import com.rank.casino.model.RequestInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


interface PlayerRepository extends JpaRepository<Player, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE Player p SET p.balance = balance + ?1 WHERE p.ID = ?2",
            nativeQuery = true)
    void updateBalance(Double amount, Long playerId);

    Player findByUsername(String username);
}