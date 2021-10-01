package com.simple.exercises;

import java.util.Scanner;

public class Solution {
  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    int number = scanner.nextInt();
    scanner.nextLine();

    for (int i = 1; i <= 10; i++) {
      System.out.println(number + " x " + i + " = " + number * i);
    }
    scanner.close();
  }
}
