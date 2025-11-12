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
            System.out.println("1) Add Pizza");
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




