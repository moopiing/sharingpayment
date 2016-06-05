package com.kaoneaw.moopiing.sharingpayment.Models;

public class Account {
    double balance;
    String username,password;

    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setBalance(double balance){
        this.balance = balance;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public double getBalance(){
        return balance;
    }
}
