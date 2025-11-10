package com.pluralsight;

public enum PizzaSize {
    PERSONAL_8(8, 8.50),
    MEDIUM_12(12, 12.00),
    LARGE_16(16, 16.50);

    private final int diameterInInches;
    private final double basePrice;

    PizzaSize(int diameterInInches, double basePrice) {
        this.diameterInInches = diameterInInches;
        this.basePrice = basePrice;
    }

    public int getDiameterInInches() {
        return diameterInInches;
    }

    public double getBasePrice() {
        return basePrice;
    }
}


