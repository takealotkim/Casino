package com.rank.casino.model;

public class RequestInfo {
    private String username;
    private String password;

    RequestInfo(){}

    public RequestInfo(String username, String password){
        this.password = password;
        this.username = username;
    }
    public String getUsername(){
        return this.username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return this.password;
    }
}