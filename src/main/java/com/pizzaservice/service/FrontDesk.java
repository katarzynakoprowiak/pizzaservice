package com.pizzaservice.service;

import java.util.InputMismatchException;
import java.util.Scanner;

public class FrontDesk {
    private static PizzaService service;
    private static Scanner scanner;
    private static final int TAKE_ORDER = 1;
    private static final int PRINT_MENU = 2;
    private static final int PRINT_ORDERS = 3;
    private static final int QUIT = 4;

    public static void main(String[] args) {
        service = new PizzaService(new PizzaFactory());
        scanner = new Scanner(System.in);

        System.out.print("Welcome to PizzaService!\n");

        boolean carryOn = true;

        while (carryOn) {
            System.out.print("What would you like to do?\n");
            System.out.print(listOptions());

            try{
                int option = scanner.nextInt();

                if (option == TAKE_ORDER) {
                    takeOrder();
                }
                else if (option == PRINT_MENU){
                    printMenu();
                }
                else if (option == PRINT_ORDERS) {
                    printOrders();
                }
                else if (option == QUIT) {
                    carryOn = false;
                }
            } catch (InputMismatchException e){
                System.out.println("Option you chose is unavailable, please select other option.");
            }

            }
        }

    private static void takeOrder() {
        Order order = buildOrder();
        service.takeOrder(order);
    }


    private static Order buildOrder() {
        Order.Builder builder = new Order.Builder(new PizzaFactory());

        addItems(builder);
        setPaymentMethod(builder);
        addComment(builder);

        return builder.build();
    }

    private static void addComment(Order.Builder builder) {
        System.out.println("Would you like to add comment to the order?");
        builder.comment(scanner.nextLine());
    }

    private static void setPaymentMethod(Order.Builder builder) {
        boolean invalidPaymentMethod = true;
        while (invalidPaymentMethod)
        try {
            System.out.println("How would you like to pay? (cash/credit card)");
            builder.paymentMethod(scanner.nextLine());
            invalidPaymentMethod = false;
        } catch (IllegalArgumentException iae){
        System.out.println(iae.getMessage());
        }
    }

    private static void addItems(Order.Builder builder) {
        boolean orderComplete = false;
        scanner.nextLine();
        while (!orderComplete) {
            System.out.println("What would you like to order?");
            try{
                String pizzaType = scanner.nextLine();
                System.out.println("How many?");
                int quantity = scanner.nextInt();
                for (int i = 0; i < quantity; i++){
                    builder.addItem(pizzaType);}
                scanner.nextLine();
            } catch (IllegalArgumentException iae){
                System.out.println(iae.getMessage());
            } catch (InputMismatchException e){
                System.out.println("Invalid quantity input, please select correct quantity.");
            }
            System.out.println("Would you like anything else? (y/n)");
            if (scanner.nextLine().equalsIgnoreCase("n"))
            {
                orderComplete = true;
            }
        }
    }

    private static void printMenu() {
        System.out.print(service.printMenu());
    }

    private static void printOrders() {
        System.out.println(service.printOrders());
    }

    private static String listOptions(){
        StringBuilder builder = new StringBuilder();

        builder.append(TAKE_ORDER);
        builder.append(" - Place an order\n");
        builder.append(PRINT_MENU);
        builder.append(" - List menu\n");
        builder.append(PRINT_ORDERS);
        builder.append(" - List orders\n");
        builder.append(QUIT);
        builder.append(" - Quit\n");

        return builder.toString();
    }
}
