package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Pizza implements Pricable {

    private PizzaSize size;
    private CrustType crustType;
    private boolean stuffedCrust;
    private List<Topping> toppings = new ArrayList<>();

    public Pizza(PizzaSize size, CrustType crustType, boolean stuffedCrust) {
        this.size = size;
        this.crustType = crustType;
        this.stuffedCrust = stuffedCrust;
    }

    public PizzaSize getSize() {
        return size;
    }

    public void setSize(PizzaSize size) {
        this.size = size;
    }

    public CrustType getCrustType() {
        return crustType;
    }

    public void setCrustType(CrustType crustType) {
        this.crustType = crustType;
    }

    public boolean isStuffedCrust() {
        return stuffedCrust;
    }

    public void setStuffedCrust(boolean stuffedCrust) {
        this.stuffedCrust = stuffedCrust;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    @Override
    public double getPrice() {
        double total = size.getBasePrice();

        for (Topping topping : toppings) {
            total += topping.getPrice(size);
        }

        // Requirements don't specify extra cost for stuffed crust
        // If needed, you can add a small upcharge here later.

        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(size.name())
                .append(" Pizza (")
                .append(crustType.name().toLowerCase())
                .append(" crust)");

        if (stuffedCrust) {
            sb.append(" with stuffed crust");
        }

        if (!toppings.isEmpty()) {
            sb.append("\n  Toppings: ");
            for (int i = 0; i < toppings.size(); i++) {
                sb.append(toppings.get(i));
                if (i < toppings.size() - 1) {
                    sb.append(", ");
                }
            }
        } else {
            sb.append("\n  Toppings: (none)");
        }

        sb.append(String.format("\n  Price: $%.2f", getPrice()));

        return sb.toString();
    }
}

