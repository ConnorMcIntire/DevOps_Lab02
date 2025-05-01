package edu.westga.devops.a7.model;

public class Item {
    private final String name;
    private int quantity;

    public Item(String name) {
        this.name = name;
        this.quantity = 0; 
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return name + " (x" + quantity + ")";
    }
}