package com.simple.exercises;

import java.util.Scanner;

public class PlayingWithPadding {

  public static void main(String[] args) {
    MyInput[] inputs = new MyInput[3];
    Scanner sc = new Scanner(System.in);
    for (int i = 0; i < 3; i++) {
      String unsplitedString = sc.nextLine();
      String[] splitedString = unsplitedString.split(" ");
      String x = splitedString[1];
      String s1 = splitedString[0];
      if (intInputValid(x) && stringInputValid(s1)) {
        inputs[i] = new MyInput(s1, x);
      } else {
        sc.close();
        System.exit(-1);
      }
    }
    printUserInput(inputs);
    sc.close();
  }

  private static void printUserInput(MyInput[] inputs) {
    System.out.println("================================");
    for (int i = 0; i < 3; i++) {
      String spacesPaddle = "";
      int spaces = 15 - inputs[i].stringInput.length();

      int spacesNeeded = Math.max(spaces, 0);
      for (int j = 0; j <= spacesNeeded; j++) {
        spacesPaddle += " ";
      }

      System.out.println(inputs[i].stringInput + spacesPaddle + inputs[i].intInput);
    }
    System.out.println("================================");
  }

  private static boolean stringInputValid(String someString) {
    return someString.length() <= 10 && someString.length() > 0 && allAlphabeticChars(someString);
  }

  private static boolean allAlphabeticChars(String someString) {
    if (someString == null || someString.equals("")) {
      return false;
    }
    for (int i = 0; i < someString.length(); i++) {
      if (!Character.isLetter(someString.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  private static boolean intInputValid(String someInt) {
    int number = Integer.parseInt(someInt);
    return number >= 0 && number <= 100;
  }

  private static class MyInput {

    String stringInput;
    String intInput;

    public MyInput(String someString, String someInt) {
      stringInput = someString;
      intInput = paddleIntInput(someInt);
    }

    private static String paddleIntInput(String someInput) {
      if (someInput.length() == 1) {
        return "00" + someInput;
      } else if (someInput.length() == 2) {
        return "0" + someInput;
      } else {
        return someInput;
      }
    }
  }
}