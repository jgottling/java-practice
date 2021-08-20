package com.grocerylist;

import java.util.ArrayList;

public class GroceryList {

  private final ArrayList<Item> groceryList = new ArrayList<>();

  public static void initGrid(int size) {}

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

  public void modifyItem(Item newItem) {}

  public void showList() {
    if (this.groceryList.isEmpty()) System.out.println("Your list is empty, please add items");
    else
      System.out.println(this);
  }

  @Override
  public String toString() {

    StringBuilder itemsAsString = new StringBuilder();

    for (Item item : this.groceryList) itemsAsString.append(item.toString());

    return "GroceryList {" + '{' + itemsAsString + '}' + '}';
  }
}
