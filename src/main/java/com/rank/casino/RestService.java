package com.rank.casino;

import com.rank.casino.error.InvalidPasswordException;
import com.rank.casino.error.OutOfFundsException;
import com.rank.casino.error.PlayerNotFoundException;
import com.rank.casino.model.Player;
import com.rank.casino.model.RequestInfo;
import com.rank.casino.model.Transaction;
import com.rank.casino.utils.SecurityUtil;
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
    public void saveWin(Transaction newTransaction) throws Exception {
        if (!playerRepository.existsById(newTransaction.getPlayerId()))
            throw new PlayerNotFoundException();
        if(transactionRepository.existsById(newTransaction.getTransactionId()))
            return;
        newTransaction.setTransactionType('W');
        playerRepository.updateBalance(newTransaction.getAmount(), newTransaction.getPlayerId());
        transactionRepository.save(newTransaction);
    }

    @Transactional(rollbackFor = { Exception.class })
    public void saveWager(Transaction newTransaction) throws PlayerNotFoundException, OutOfFundsException {
        if (!playerRepository.existsById(newTransaction.getPlayerId()))
            throw new PlayerNotFoundException();
        if(transactionRepository.existsById(newTransaction.getTransactionId()))
            return;
        Player player = playerRepository.findById(newTransaction.getPlayerId()).get();
        if(player.getBalance() < newTransaction.getAmount())
            throw new OutOfFundsException();

        playerRepository.updateBalance(newTransaction.getAmount() * -1, newTransaction.getPlayerId());
        newTransaction.setTransactionType('D');
        transactionRepository.save(newTransaction);
    }

    public Double getBalance(Long playerId) throws PlayerNotFoundException {
        if (!playerRepository.existsById(playerId))
            throw new PlayerNotFoundException();
        Optional<Player> player = playerRepository.findById(playerId);
        return player.get().getBalance();
    }

    public List<Transaction> listTransactions(RequestInfo requestInfo) throws PlayerNotFoundException, InvalidPasswordException {
        SecurityUtil.verifyPassword(requestInfo.getPassword());
        Player player = playerRepository.findByUsername(requestInfo.getUsername());
        if(!playerRepository.existsById(player.getId()))
            throw new PlayerNotFoundException();
        return transactionRepository.listTransactions(player.getId());
    }
}
