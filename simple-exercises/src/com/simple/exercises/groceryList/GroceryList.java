package com.simple.exercises.groceryList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GroceryList {

  private final List<Item> groceryList;
  private final String name;

  public GroceryList(String name) {
    this(name, new ArrayList<>());
  }

  public GroceryList(String name, ArrayList<Item> groceryList) {
    this.groceryList = groceryList;
    this.name = name;
  }

  public GroceryList filterListByLetter(String letter) {
    return new GroceryList(
        "Filtered list",
        this.groceryList.stream()
            .filter(item -> item.getName().startsWith(letter))
            .collect(Collectors.toCollection(ArrayList::new)));
  }

  public void addItem(Item item) {
    this.groceryList.add(item);
    System.out.println("Item: " + item + " was added successfully");
  }

  public void removeItem(Item item) {
    this.groceryList.remove(item);
  }

  public Item getItem(Item item) {
    int index = this.groceryList.indexOf(item);
    return index >= 0 ? this.groceryList.get(index) : null;
  }

  public void modifyItem(Item newItem) {
    int index = this.groceryList.indexOf(newItem);
    this.groceryList.remove(newItem);
    this.groceryList.add(index, newItem);
  }

  public void showList() {
    if (this.groceryList.isEmpty()) System.out.println("Your list is empty, please add items");
    else
      System.out.println(this);
  }

  @Override
  public String toString() {

    StringBuilder itemsAsString = new StringBuilder();

    for (Item item : this.groceryList) itemsAsString.append(item.toString());

    return "GroceryList: " + this.getName() + "{ " + '{' + itemsAsString + '}' + " }";
  }

  private String getName() {
    return this.name;
  }

  public static class Item {

    private final String name;
    private final double price;
    private int quantity;

    public Item(String name) {
      this(name, 0, 0);
    }

    public Item(String name, int quantity, double price) {
      this.name = name;
      this.quantity = quantity;
      this.price = price;
    }

    public int getQuantity() {
      return this.quantity;
    }

    public void setQuantity(int quantity) {
      this.quantity = quantity;
    }

    public double getPrice() {
      return this.price;
    }

    @Override
    public boolean equals(Object item) {

      if (item.getClass() != this.getClass()) return false;
      return this.getName().compareTo(((Item) item).getName()) == 0;
    }

    @Override
    public String toString() {
      return "Item {"
          + "name='"
          + this.name
          + '\''
          + ", quantity="
          + this.quantity
          + ", price="
          + this.price
          + '}';
    }

    public String getName() {
      return this.name;
    }
  }

}