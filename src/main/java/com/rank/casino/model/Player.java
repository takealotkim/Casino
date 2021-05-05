package com.rank.casino.model;

import javax.persistence.*;

@Entity
public class Player {

    private @Id @GeneratedValue
    Long id;
    private double balance=0;
    @Column(unique=true)
    private String username;

    Player() {}

    public Player(double balance, String username) {
        this.balance = balance;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }
}
