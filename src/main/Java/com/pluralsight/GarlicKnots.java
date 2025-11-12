package com.pluralsight;

public class GarlicKnots implements Pricable {

    public static final double PRICE_PER_ORDER = 1.50;

    private int quantity; // number of orders of garlic knots

    public GarlicKnots(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int amount) {
        this.quantity += amount;
    }

    @Override
    public double getPrice() {
        return quantity * PRICE_PER_ORDER;
    }

    @Override
    public String toString() {
        return String.format("Garlic Knots x%d - $%.2f", quantity, getPrice());
    }
}