package com.rank.casino.model;

public class Promotion extends Transaction{

    String promoCode;
    public void setPromoCode(String promoCode){
        this.promoCode = promoCode;
    }
    public String getPromoCode() {
        return promoCode;
    }
}
