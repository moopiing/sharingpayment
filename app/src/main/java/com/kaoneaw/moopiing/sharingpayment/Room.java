package com.kaoneaw.moopiing.sharingpayment;

public class Room {
    String name;
    int food,drink,dessert;

    public void setName(String name){
        this.name = name;
    }
    public void setFood(int food){
        this.food = food;
    }

    public void setDrink(int drink) {
        this.drink = drink;
    }

    public void setDessert(int dessert) {
        this.dessert = dessert;
    }

    public String getName() {
        return this.name;
    }

    public int getFood() {
        return this.food;
    }

    public int getDrink() {
        return this.drink;
    }

    public int getDessert() {
        return this.dessert;
    }
}
