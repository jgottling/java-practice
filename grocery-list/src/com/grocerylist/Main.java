package com.grocerylist;

import java.util.Scanner;

public class Main {

  private static final Scanner scanner = new Scanner(System.in);
  private static final GroceryList myList = new GroceryList();
  private static boolean continueRunning = true;
  private static boolean firstTime = true;

  public static void main(String[] args) {

    while (continueRunning) {

      printWelcome();

      printOptions();

      System.out.println("Enter your option");
      int option = scanner.nextInt();
      scanner.nextLine();

      processChoice(option);
    }
  }

  private static void printWelcome() {
    if (firstTime) {
      System.out.println(
          "Welcome to this simple grocery list program. You'll be able to add, remove or modify items");
      firstTime = false;
    }
  }

  private static void printOptions() {
    System.out.println(
        "Choose one: \n"
            + "1. View current grocery list \n"
            + "2. Add item \n"
            + "3. Remove item \n"
            + "4. Modify item \n"
            + "5. Exit \n");
  }

  private static void processChoice(int option) {
    switch (option) {
      case 1:
        showList();
        break;
      case 2:
        addItem();
        break;
      case 3:
        break;
      case 4:
        break;
      case 5:
      default:
        System.out.println("Goodbye!");
        scanner.close();
        continueRunning = false;
    }
  }

  private static void showList() {
    myList.showList();
  }

  private static void addItem() {
    System.out.print("Enter item name: ");
    String name = scanner.nextLine();
    System.out.print("Enter item price: ");
    double itemPrice = scanner.nextDouble();
    scanner.nextLine();
    System.out.print("How many of this item? (enter a number): ");
    int quantity = scanner.nextInt();
    scanner.nextLine();

    myList.addItem(new Item(name, quantity, itemPrice));
  }
}
