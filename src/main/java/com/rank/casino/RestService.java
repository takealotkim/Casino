package com.rank.casino;

import com.rank.casino.error.OutOfFundsException;
import com.rank.casino.error.PlayerNotFoundException;
import com.rank.casino.model.Player;
import com.rank.casino.model.Transaction;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RestService {

    private final PlayerRepository playerRepository;
    private final TransactionRepository transactionRepository;

    public RestService(PlayerRepository playerRepository, TransactionRepository transactionRepository) {
        this.playerRepository = playerRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional(rollbackFor = { Exception.class })
    public void save(Transaction newTransaction) throws Exception {
        if (!playerRepository.existsById(newTransaction.getPlayerId()))
            throw new PlayerNotFoundException();
        Player player = playerRepository.findById(newTransaction.getPlayerId()).get();
        if(player.getBalance() < newTransaction.getAmount())
            throw new OutOfFundsException();
        playerRepository.updateBalance(newTransaction.getAmount(), newTransaction.getPlayerId());
        transactionRepository.save(newTransaction);
    }

    public Double getBalance(Long playerId) throws PlayerNotFoundException {
        if (!playerRepository.existsById(playerId))
            throw new PlayerNotFoundException();
        Optional<Player> player = playerRepository.findById(playerId);
        return player.get().getBalance();
    }

    public List<Transaction> listTransactions(Long playerId) throws PlayerNotFoundException {
        if (!playerRepository.existsById(playerId))
            throw new PlayerNotFoundException();
        return transactionRepository.listTransactions(playerId);
    }
}
