package com.rank.casino;

import com.rank.casino.model.Player;
import com.rank.casino.model.Transaction;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RepositoryTest {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    public void savePlayerTest() {
        Player player = new Player(10, "Tom");
        playerRepository.save(player);
        Assert.assertNotNull(playerRepository.findByUsername("Tom"));
    }

    @Test
    public void getUpdateBalance() {
        Long playerId = 2L;
        Player player = playerRepository.findById(playerId).get();
        Double balance1 = player.getBalance();
        playerRepository.updateBalance(1000.00, playerId);
        player = playerRepository.findById(playerId).get();
        Double balance2 = player.getBalance();
        assertEquals(balance1 + 1000, balance2);
    }
}