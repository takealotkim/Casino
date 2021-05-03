package com.rank.casino.model;

import javax.persistence.*;

@Entity
public class Player {

    private @Id @GeneratedValue
    Long id;
    private double balance;

    Player() {}

    public Player(double balance) {
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
