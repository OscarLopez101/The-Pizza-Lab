package com.pluralsight;

import java.util.Scanner;

public class Menu {

    private final Scanner scanner = new Scanner(System.in);
    private boolean running = true;

    public void display() {

        System.out.println(Colors.YELLOW + "Welcome to the PIZZA-Lab!" + Colors.RESET);

        while (running) {
            System.out.println("\n" + Colors.CYAN + "==== HOME SCREEN ====" + Colors.RESET);
            System.out.println("1) New Order");
            System.out.println("0) Exit");
            System.out.print("> ");

            switch (scanner.nextLine()) {
                case "1":
                    startNewOrder();
                    break;
                case "0":
                    running = false;
                    System.out.println(Colors.GREEN + "Goodbye!" + Colors.RESET);
                    break;
                default:
                    System.out.println(Colors.RED + "Invalid option!" + Colors.RESET);
            }
        }
    }

    private void startNewOrder() {
        Order order = new Order();
        boolean ordering = true;

        while (ordering) {
            System.out.println("\n" + Colors.CYAN + "==== ORDER SCREEN ====" + Colors.RESET);
            System.out.println("1) Add Pizza");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Garlic Knots");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");
            System.out.print("> ");

            switch (scanner.nextLine()) {
                case "1": addPizza(order); break;
                case "2": addDrink(order); break;
                case "3": addGarlicKnots(order); break;
                case "4": checkout(order); ordering = false; break;
                case "0":
                    System.out.println(Colors.RED + "Order cancelled." + Colors.RESET);
                    ordering = false;
                    break;
                default:
                    System.out.println(Colors.RED + "Invalid option!" + Colors.RESET);
            }
        }
    }

    private void addPizza(Order order) {

        System.out.println("\n" + Colors.YELLOW + "==== ADD PIZZA ====" + Colors.RESET);

        PizzaSize size = selectPizzaSize();
        CrustType crust = selectCrustType();

        System.out.print("Stuffed crust? (y/n): ");
        boolean stuffed = scanner.nextLine().equalsIgnoreCase("y");

        Pizza pizza = new Pizza(size, crust, stuffed);

        addToppings(pizza);

        order.addPizza(pizza);
        System.out.println(Colors.GREEN + "Pizza added!" + Colors.RESET);
    }

    private PizzaSize selectPizzaSize() {
        System.out.println("\nSelect Size:");
        System.out.println("1) Personal 8\"");
        System.out.println("2) Medium 12\"");
        System.out.println("3) Large 16\"");
        System.out.print("> ");

        switch (scanner.nextLine()) {
            case "1": return PizzaSize.PERSONAL_8;
            case "2": return PizzaSize.MEDIUM_12;
            case "3": return PizzaSize.LARGE_16;
            default:
                System.out.println(Colors.RED + "Invalid – defaulting to Medium" + Colors.RESET);
                return PizzaSize.MEDIUM_12;
        }
    }

    private CrustType selectCrustType() {
        System.out.println("\nSelect Crust:");
        System.out.println("1) Thin");
        System.out.println("2) Regular");
        System.out.println("3) Thick");
        System.out.println("4) Cauliflower");
        System.out.print("> ");

        switch (scanner.nextLine()) {
            case "1": return CrustType.THIN;
            case "2": return CrustType.REGULAR;
            case "3": return CrustType.THICK;
            case "4": return CrustType.CAULIFLOWER;
            default:
                System.out.println(Colors.RED + "Invalid – defaulting to Regular" + Colors.RESET);
                return CrustType.REGULAR;
        }
    }

    private void addToppings(Pizza pizza) {

        System.out.println("\n" + Colors.YELLOW + "==== ADD TOPPINGS ====" + Colors.RESET);

        // SAUCE
        handleCommaInput(
                "Sauce",
                "(Marinara, Alfredo, Pesto, BBQ, Buffalo, Olive Oil)",
                ToppingType.SAUCE,
                false,
                pizza
        );

        // CHEESE
        handleCommaInput(
                "Cheese",
                "(Cheddar, Swiss, American, Provolone)",
                ToppingType.CHEESE,
                true,
                pizza
        );

        // REGULAR TOPPINGS
        handleCommaInput(
                "Toppings",
                "(Lettuce, Pepper, Tomato, Onion, Jalapenos, Pickles, Cucumbers, Guac, Mushrooms)",
                ToppingType.REGULAR,
                false,
                pizza
        );

        // MEATS
        handleCommaInput(
                "Meats",
                "(Steak, Ham, Salami, Roast Beef, Chicken, Bacon)",
                ToppingType.MEAT,
                true,
                pizza
        );

        System.out.println(Colors.BLUE + "Toppings added!" + Colors.RESET);
    }


    // ✔️ CLEAN, REUSABLE TOPPING INPUT METHOD
    private void handleCommaInput(String label, String options, ToppingType type, boolean askExtra, Pizza pizza) {

        System.out.println("\nAdd " + label + " (comma separated):");
        System.out.println(options);
        System.out.print(Colors.GREEN + "> " + Colors.RESET);

        String input = scanner.nextLine().trim();
        if (input.isEmpty()) return;

        boolean extra = false;

        if (askExtra) {
            System.out.print("Extra " + label.toLowerCase() + "? (y/n): ");
            extra = scanner.nextLine().equalsIgnoreCase("y");
        }

        String[] items = input.split(",");

        for (String item : items) {
            String clean = capitalize(item.trim());
            if (!clean.isEmpty()) {
                pizza.addTopping(new Topping(clean, type, extra));
            }
        }
    }


    // AUTO-CAPITALIZATION (ham → Ham)
    private String capitalize(String word) {
        if (word.isEmpty()) return word;
        return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    }

    private void addDrink(Order order) {
        System.out.println("\n" + Colors.YELLOW + "==== ADD DRINK ====" + Colors.RESET);

        System.out.println("1) Small");
        System.out.println("2) Medium");
        System.out.println("3) Large");
        System.out.print("> ");

        DrinkSize size;
        switch (scanner.nextLine()) {
            case "1": size = DrinkSize.SMALL; break;
            case "2": size = DrinkSize.MEDIUM; break;
            case "3": size = DrinkSize.LARGE; break;
            default:
                size = DrinkSize.MEDIUM;
        }

        System.out.print("Enter flavor: ");
        String flavor = capitalize(scanner.nextLine());

        order.addDrink(new Drink(size, flavor));
        System.out.println(Colors.GREEN + "Drink added!" + Colors.RESET);
    }

    private void addGarlicKnots(Order order) {
        System.out.print("\nHow many orders of Garlic Knots? ");
        int qty = Integer.parseInt(scanner.nextLine());

        order.addGarlicKnots(new GarlicKnots(qty));
        System.out.println(Colors.GREEN + "Garlic Knots added!" + Colors.RESET);
    }


    private void checkout(Order order) {

        System.out.println("\n" + Colors.YELLOW + "==== CHECKOUT ====" + Colors.RESET);
        System.out.println(order);

        System.out.print("Confirm order? (y/n): ");
        if (scanner.nextLine().equalsIgnoreCase("y")) {

            ReceiptManager.saveReceipt(order);
            System.out.println(Colors.GREEN + "Receipt saved! Returning to Home Screen..." + Colors.RESET);

        } else {
            System.out.println(Colors.RED + "Order cancelled!" + Colors.RESET);
        }
    }
}