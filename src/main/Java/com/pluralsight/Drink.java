package com.pluralsight;

public class Drink implements Pricable {

    private DrinkSize size;
    private String flavor;

    public Drink(DrinkSize size, String flavor) {
        this.size = size;
        this.flavor = flavor;
    }

    public DrinkSize getSize() {
        return size;
    }

    public void setSize(DrinkSize size) {
        this.size = size;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    @Override
    public double getPrice() {
        return size.getPrice();
    }

    @Override
    public String toString() {
        return String.format("%s Drink (%s) - $%.2f",
                size.name(),
                flavor,
                getPrice());
    }
}


