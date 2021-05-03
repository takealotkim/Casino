package com.rank.casino;

import com.rank.casino.error.PlayerNotFoundException;
import com.rank.casino.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class Controller {
    @Autowired
    private RestService service;

    @GetMapping("api/balance")
    public String balance(@RequestHeader("playerId") long playerId) throws Exception {
        return service.getBalance(playerId).toString();
    }

    @PostMapping("/api/wager")
    public void wager(@RequestBody Transaction newTransaction) throws Exception {
        service.save(newTransaction);
    }
    // winning (depositing) money
    @PostMapping("/api/win")
    public void win(@RequestBody Transaction newTransaction) throws Exception {
        service.save(newTransaction);
    }

    @GetMapping("api/transactions")
    public List<Transaction> listTransactions(@RequestHeader("playerId") long playerId) throws PlayerNotFoundException {
        return service.listTransactions(playerId);
    }
}
