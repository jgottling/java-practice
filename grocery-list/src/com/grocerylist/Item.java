package com.grocerylist;

public class Item {

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
