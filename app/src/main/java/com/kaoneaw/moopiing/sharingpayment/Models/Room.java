package com.kaoneaw.moopiing.sharingpayment.Models;

public class Room {
    String name;
    double food,drink,dessert;

    public void setName(String name){
        this.name = name;
    }
    public void setFood(double food){
        this.food = food;
    }

    public void setDrink(double drink) {
        this.drink = drink;
    }

    public void setDessert(double dessert) {
        this.dessert = dessert;
    }

    public String getName() {
        return name;
    }

    public double getFood() {
        return food;
    }

    public double getDrink() {
        return drink;
    }

    public double getDessert() {
        return dessert;
    }
}
