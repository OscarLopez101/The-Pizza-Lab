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




