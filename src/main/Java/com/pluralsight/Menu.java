package com.pluralsight;

import java.util.Scanner;

public class Menu {

    private Scanner scanner = new Scanner(System.in);
    private boolean running = true;

    public void display() {
        System.out.println("Welcome to PIZZA-licious!");
        while (running) {
            System.out.println("\n==== HOME SCREEN ====");
            System.out.println("1) New Order");
            System.out.println("0) Exit");
            System.out.print("Select option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    startNewOrder();
                    break;
                case "0":
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option, try again.");
            }
        }
    }

    private void startNewOrder() {
        Order order = new Order();
        boolean ordering = true;

        while (ordering) {
            System.out.println("\n==== ORDER SCREEN ====");
            System.out.println("Items in order: "
                    + order.getPizzas().size() + " pizzas, "
                    + order.getDrinks().size() + " drinks, "
                    + order.getGarlicKnotsItems().size() + " garlic knots.");

            // 🔸 FRIENDLY REMINDER MESSAGE
            if (order.getPizzas().isEmpty()) {
                System.out.println("⚠️  Reminder: If you do not order any pizzas, you MUST add a drink or garlic knots before checkout.");
            }

            System.out.println("\n1) Add Pizza");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Garlic Knots");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");
            System.out.print("Select option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addPizza(order);
                    break;
                case "2":
                    addDrink(order);
                    break;
                case "3":
                    addGarlicKnots(order);
                    break;
                case "4":
                    checkout(order);
                    ordering = false;
                    break;
                case "0":
                    System.out.println("Order cancelled.");
                    ordering = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void addPizza(Order order) {
        System.out.println("\n==== ADD PIZZA ====");
        PizzaSize size = selectPizzaSize();
        CrustType crust = selectCrustType();

        System.out.print("Stuffed crust? (y/n): ");
        boolean stuffed = scanner.nextLine().equalsIgnoreCase("y");

        Pizza pizza = new Pizza(size, crust, stuffed);

        addToppings(pizza);
        order.addPizza(pizza);
        System.out.println("Pizza added successfully!");
    }

    private PizzaSize selectPizzaSize() {
        System.out.println("Select size:");
        System.out.println("1) Personal 8\"");
        System.out.println("2) Medium 12\"");
        System.out.println("3) Large 16\"");
        System.out.print("Choice: ");
        String input = scanner.nextLine();
        switch (input) {
            case "1": return PizzaSize.PERSONAL_8;
            case "2": return PizzaSize.MEDIUM_12;
            case "3": return PizzaSize.LARGE_16;
            default:
                System.out.println("Invalid, defaulting to Medium.");
                return PizzaSize.MEDIUM_12;
        }
    }

    private CrustType selectCrustType() {
        System.out.println("Select crust:");
        System.out.println("1) Thin");
        System.out.println("2) Regular");
        System.out.println("3) Thick");
        System.out.println("4) Cauliflower");
        System.out.print("Choice: ");
        String input = scanner.nextLine();
        switch (input) {
            case "1": return CrustType.THIN;
            case "2": return CrustType.REGULAR;
            case "3": return CrustType.THICK;
            case "4": return CrustType.CAULIFLOWER;
            default:
                System.out.println("Invalid, defaulting to regular.");
                return CrustType.REGULAR;
        }
    }

    private void addToppings(Pizza pizza) {
        System.out.println("Add toppings? (y/n)");
        String answer = scanner.nextLine();
        if (!answer.equalsIgnoreCase("y")) return;

        boolean adding = true;
        while (adding) {
            System.out.println("\nSelect topping type:");
            System.out.println("1) Meat");
            System.out.println("2) Cheese");
            System.out.println("3) Regular Topping");
            System.out.println("4) Sauce");
            System.out.println("0) Done adding toppings");
            System.out.print("Choice: ");
            String typeChoice = scanner.nextLine();

            switch (typeChoice) {
                case "1": addSpecificTopping(pizza, ToppingType.MEAT); break;
                case "2": addSpecificTopping(pizza, ToppingType.CHEESE); break;
                case "3": addSpecificTopping(pizza, ToppingType.REGULAR); break;
                case "4": addSpecificTopping(pizza, ToppingType.SAUCE); break;
                case "0": adding = false; break;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private void addSpecificTopping(Pizza pizza, ToppingType type) {
        System.out.print("Enter topping name: ");
        String name = scanner.nextLine();
        boolean extra = false;
        if (type == ToppingType.MEAT || type == ToppingType.CHEESE) {
            System.out.print("Extra portion? (y/n): ");
            extra = scanner.nextLine().equalsIgnoreCase("y");
        }
        pizza.addTopping(new Topping(name, type, extra));
        System.out.println(name + " added!");
    }

    private void addDrink(Order order) {
        System.out.println("\n==== ADD DRINK ====");
        System.out.println("Select size:");
        System.out.println("1) Small");
        System.out.println("2) Medium");
        System.out.println("3) Large");
        System.out.print("Choice: ");
        String input = scanner.nextLine();

        DrinkSize size;
        switch (input) {
            case "1": size = DrinkSize.SMALL; break;
            case "2": size = DrinkSize.MEDIUM; break;
            case "3": size = DrinkSize.LARGE; break;
            default: size = DrinkSize.MEDIUM;
        }

        System.out.print("Enter drink flavor: ");
        String flavor = scanner.nextLine();

        order.addDrink(new Drink(size, flavor));
        System.out.println("Drink added!");
    }

    private void addGarlicKnots(Order order) {
        System.out.print("How many orders of garlic knots? ");
        int qty = Integer.parseInt(scanner.nextLine());
        order.addGarlicKnots(new GarlicKnots(qty));
        System.out.println("Garlic knots added!");
    }

    private void checkout(Order order) {
        System.out.println("\n==== CHECKOUT ====");

        boolean hasPizza = !order.getPizzas().isEmpty();
        boolean hasDrink = !order.getDrinks().isEmpty();
        boolean hasKnots = !order.getGarlicKnotsItems().isEmpty();

        // 🔸 Rule: cannot have a completely empty order
        if (!hasPizza && !hasDrink && !hasKnots) {
            System.out.println("⚠️  You cannot checkout with an empty order! Please add at least one item.");
            return;
        }

        // 🔸 Rule: if no pizza, must have drink or garlic knots
        if (!hasPizza && !hasDrink && hasKnots == false) {
            System.out.println("⚠️  You must order a drink or garlic knots if you have no pizzas.");
            return;
        }

        // Otherwise, valid order
        System.out.println(order);
        System.out.print("Confirm order? (y/n): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("y")) {
            ReceiptManager.saveReceipt(order);
            System.out.println("✅ Receipt saved! Returning to home screen...");
        } else {
            System.out.println("Order cancelled. Returning to home screen...");
        }
    }
}
