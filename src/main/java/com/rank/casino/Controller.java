package com.rank.casino;

import com.rank.casino.error.InvalidPasswordException;
import com.rank.casino.error.PlayerNotFoundException;
import com.rank.casino.model.RequestInfo;
import com.rank.casino.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class Controller {
    @Autowired
    private RestService service;
    @GetMapping("api/balance")
    public Double balance(@RequestParam("playerId") long playerId) throws Exception {
        return service.getBalance(playerId);
    }

    @PostMapping("/api/wager")
    public void wager(@RequestBody Transaction newTransaction) throws Exception {
        service.saveWager(newTransaction);
    }

    @PostMapping("/api/win")
    public void win(@RequestBody Transaction newTransaction) throws Exception {
        service.saveWin(newTransaction);
    }

    @PostMapping("api/transactions")
    public List<Transaction> listTransactions(@RequestBody RequestInfo requestInfo) throws PlayerNotFoundException, InvalidPasswordException {
        return service.listTransactions(requestInfo);
    }

}
