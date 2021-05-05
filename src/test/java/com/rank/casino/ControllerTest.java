package com.rank.casino;
import com.rank.casino.error.InvalidPasswordException;
import com.rank.casino.error.OutOfFundsException;
import com.rank.casino.error.PlayerNotFoundException;
import com.rank.casino.model.RequestInfo;
import com.rank.casino.model.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ControllerTest{

    @Autowired
    Controller controller;
    @Test
    public void testWagerOutOfFunds() throws Exception {
        Double amount = controller.balance(1L);
        Transaction transaction = new Transaction(1L,amount + 10,100);
        Exception exception = null;
        try {
            controller.wager(transaction);
        }catch(OutOfFundsException ex){
            exception = ex;
        }
        assertNotNull(exception);
    }

    @Test
    public void testPlayerNotFound() throws Exception {
        Transaction transaction = new Transaction(3610L,100,101);
        Exception exception = null;
        try {
            controller.wager(transaction);
        }catch(PlayerNotFoundException ex){
            exception = ex;
        }
        assertNotNull(exception);
    }

    @Test
    public void testWin() throws Exception {
        Double balance1 = controller.balance(1L);
        System.out.println("Player one balance is: " + balance1);
        Transaction transaction = new Transaction(1L,100,102);
        controller.win(transaction);
        Double balance2 = controller.balance(1L);
        assertEquals(balance1 + 100, balance2);
        System.out.println("After winning 100 Player one balance is: " + balance2);
    }

    @Test
    public void testWager() throws Exception {
        Long playerId = 1L;
        Double balance1 = controller.balance(playerId);
        System.out.println("Player one balance is: " + balance1);
        Transaction transaction = new Transaction(playerId,12,103);
        controller.wager(transaction);
        controller.wager(transaction);
        controller.wager(transaction);
        controller.wager(transaction);
        Double balance2 = controller.balance(playerId);
        assertEquals(balance1 - 12, balance2);
        System.out.println("After wager 12 Player one balance is: " + balance2);
    }

    @Test
    public void testWinWithSameTransactionId() throws Exception {
        Long playerId = 1L;
        Double balance1 = controller.balance(playerId);
        Transaction transaction = new Transaction(playerId,8,104);
        controller.win(transaction);
        controller.win(transaction);
        controller.win(transaction);
        controller.win(transaction);
        Double balance2 = controller.balance(playerId);
        assertEquals(balance1 + 8, balance2);
    }

    @Test
    public void testWagerWithSameTransactionId() throws Exception {
        Long playerId = 6L;
        Double balance1 = controller.balance(playerId);
        System.out.println("Player six balance is: " + balance1);
        Transaction transaction = new Transaction(playerId,8,105);
        controller.wager(transaction);
        controller.wager(transaction);
        controller.wager(transaction);
        controller.wager(transaction);
        Double balance2 = controller.balance(playerId);
        assertEquals(balance1 - 8, balance2);
        System.out.println("After wager=8 Player six balance is: " + balance2);
    }

    @Test
    public void testListTransactions() throws Exception {
        Long playerId = 3L;
        Exception exception = null;
        try {
            RequestInfo requestInfo = new RequestInfo("George","swordfish" );
            Transaction transaction = new Transaction(playerId, 8, 106);
            controller.win(transaction);
            transaction = new Transaction(playerId, 8, 107);
            controller.win(transaction);
            transaction = new Transaction(playerId, 8, 108);
            controller.win(transaction);
            transaction = new Transaction(playerId, 8, 109);
            controller.win(transaction);
            transaction = new Transaction(playerId, 8, 110);
            controller.win(transaction);
            transaction = new Transaction(playerId, 8, 111);
            controller.win(transaction);
            transaction = new Transaction(playerId, 8, 112);
            controller.win(transaction);
            transaction = new Transaction(playerId, 8, 113);
            controller.win(transaction);
            transaction = new Transaction(playerId, 8, 114);
            controller.win(transaction);
            transaction = new Transaction(playerId, 8, 115);
            controller.win(transaction);
            transaction = new Transaction(playerId, 8, 116);
            controller.win(transaction);
            transaction = new Transaction(playerId, 8, 117);
            controller.win(transaction);
            transaction = new Transaction(playerId, 8, 118);
            controller.win(transaction);

            List<Transaction> list = controller.listTransactions(requestInfo);
            int size = list.size();
            assertEquals(10, size);
            boolean found = false;
            for( Transaction t : list){
                System.out.println(t);
                if(t.getTransactionId()==107){
                    found = true;
                }
            }
            assertFalse(found);

        }catch (Exception e){
            exception = e;
        }
        assertNull(exception);
    }
    @Test
    public void testListTransactionsInvalidPassword() throws PlayerNotFoundException {
        Exception exception = null;
        List<Transaction> list1 = null;
        List<Transaction> list2 = null;
        RequestInfo requestInfo = null;
        String valid = "swordfish";
        String invalid = "password";
        try{
            requestInfo = new RequestInfo("George", valid );
            list1 = controller.listTransactions(requestInfo);
        }catch (InvalidPasswordException e){
            exception = e;
        }
        assertNotNull(list1);
        assertNull(exception);
        try {
            requestInfo = new RequestInfo("George", invalid );
            list2 = controller.listTransactions(requestInfo);

        }catch (InvalidPasswordException e){
            exception = e;
        }
        assertNull(list2);
        assertNotNull(exception);
    }
}