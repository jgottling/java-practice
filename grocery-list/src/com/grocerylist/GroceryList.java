package com.grocerylist;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GroceryList {

  private final List<Item> groceryList;
  private final String name;

  public GroceryList(String name) {
    this(name, new ArrayList<>());
  }

  public GroceryList(String name, ArrayList groceryList) {
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
}
