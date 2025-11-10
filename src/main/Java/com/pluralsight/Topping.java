package com.pluralsight;

public class Topping {

    private final String name;
    private final ToppingType type;
    private final boolean extra; // true = extra meat/cheese portion

    public Topping(String name, ToppingType type) {
        this(name, type, false);
    }

    public Topping(String name, ToppingType type, boolean extra) {
        this.name = name;
        this.type = type;
        this.extra = extra;
    }

    public String getName() {
        return name;
    }

    public ToppingType getType() {
        return type;
    }

    public boolean isExtra() {
        return extra;
    }


     // Returns the price of this topping based on pizza size.
      //Regular toppings and sauces are included (0 cost).

    public double getPrice(PizzaSize size) {
        switch (type) {
            case MEAT:
                return extra ? getExtraMeatPrice(size) : getMeatPrice(size);
            case CHEESE:
                return extra ? getExtraCheesePrice(size) : getCheesePrice(size);
            case REGULAR:
            case SAUCE:
            default:
                return 0.0; // Included
        }
    }

    private double getMeatPrice(PizzaSize size) {
        switch (size) {
            case PERSONAL_8:
                return 1.00;
            case MEDIUM_12:
                return 2.00;
            case LARGE_16:
                return 3.00;
            default:
                return 0.0;
        }
    }

    private double getExtraMeatPrice(PizzaSize size) {
        switch (size) {
            case PERSONAL_8:
                return 0.50;
            case MEDIUM_12:
                return 1.00;
            case LARGE_16:
                return 1.50;
            default:
                return 0.0;
        }
    }

    private double getCheesePrice(PizzaSize size) {
        switch (size) {
            case PERSONAL_8:
                return 0.75;
            case MEDIUM_12:
                return 1.50;
            case LARGE_16:
                return 2.25;
            default:
                return 0.0;
        }
    }

    private double getExtraCheesePrice(PizzaSize size) {
        switch (size) {
            case PERSONAL_8:
                return 0.30;
            case MEDIUM_12:
                return 0.60;
            case LARGE_16:
                return 0.90;
            default:
                return 0.0;
        }
    }

    @Override
    public String toString() {
        String extraLabel = extra ? " (extra)" : "";
        return name + extraLabel;
    }
}


