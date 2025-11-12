package com.pluralsight;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private final List<Pizza> pizzas = new ArrayList<>();
    private final List<Drink> drinks = new ArrayList<>();
    private final List<GarlicKnots> garlicKnotsItems = new ArrayList<>();
    private final LocalDateTime orderTime;

    public Order() {
        this.orderTime = LocalDateTime.now();
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public List<GarlicKnots> getGarlicKnotsItems() {
        return garlicKnotsItems;
    }

    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    public void addGarlicKnots(GarlicKnots garlicKnots) {
        garlicKnotsItems.add(garlicKnots);
    }

    public double getTotal() {
        double total = 0.0;

        for (Pizza pizza : pizzas) {
            total += pizza.getPrice();
        }

        for (Drink drink : drinks) {
            total += drink.getPrice();
        }

        for (GarlicKnots item : garlicKnotsItems) {
            total += item.getPrice();
        }

        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Order placed at: ").append(orderTime).append("\n\n");

        if (pizzas.isEmpty() && drinks.isEmpty() && garlicKnotsItems.isEmpty()) {
            sb.append("No items in order.\n");
        } else {
            if (!pizzas.isEmpty()) {
                sb.append("Pizzas:\n");
                for (Pizza pizza : pizzas) {
                    sb.append(pizza).append("\n\n");
                }
            }

            if (!drinks.isEmpty()) {
                sb.append("Drinks:\n");
                for (Drink drink : drinks) {
                    sb.append("  ").append(drink).append("\n");
                }
                sb.append("\n");
            }

            if (!garlicKnotsItems.isEmpty()) {
                sb.append("Garlic Knots:\n");
                for (GarlicKnots item : garlicKnotsItems) {
                    sb.append("  ").append(item).append("\n");
                }
                sb.append("\n");
            }

            sb.append(String.format("TOTAL: $%.2f%n", getTotal()));
        }

        return sb.toString();
    }
}