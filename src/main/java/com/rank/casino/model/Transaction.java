package com.rank.casino.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Transaction {

    private @Id @GeneratedValue
    Long id;
    private Long transactionId;
    private Long playerId;
    private Double amount;
    private Character transactionType;
    private Date timestamp;

    public Transaction(){}

    public Transaction(Long playerId,  double amount, long transactionId){
        this.transactionId = transactionId;
        this.playerId = playerId;
        this.amount = amount;
        this.transactionType = 'D';
        this.timestamp = new Date();
    }

    public Long getTransactionId(){
        return this.transactionId;
    }

    public void setTransactionId(){
        this.transactionId = transactionId;
    }

    public Character getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(Character transactionType) {
        this.transactionType = transactionType;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp){
        this.timestamp = timestamp;
    }

    public void setPlayerId(Long playerId){
        this.playerId = playerId;
    }

    public Long getPlayerId(){
        return this.playerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(Double amount){
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" + "id=" + this.id + ", playerId='" + this.playerId + '\'' + ", amount='" + this.amount + '\'' + '}';
    }
}
