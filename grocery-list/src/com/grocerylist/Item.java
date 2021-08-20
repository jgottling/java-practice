package com.grocerylist;

public class Item {

  private String name;
  private int quantity;
  private double price;

  public Item(String name, int quantity, double price) {
    this.name = name;
    this.quantity = quantity;
    this.price = price;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
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

  public void setPrice(double price) {
    this.price = price;
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
}
