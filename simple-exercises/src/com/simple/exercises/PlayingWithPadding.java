package com.simple.exercises;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayingWithPadding {

  //first input number of lines, then for each line input a string and a number, the output is a table
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int numberOfInputs = sc.nextInt();
    sc.nextLine();

    MyInput[] inputs = new MyInput[numberOfInputs];

    for (int i = 0; i < numberOfInputs; i++) {
      String[] splitedString = sc.nextLine().split(" ");
      String intInput = splitedString[1];
      String stringInput = splitedString[0];
      if (intInputValid(intInput) && stringInputValid(stringInput)) {
        inputs[i] = new MyInput(stringInput, intInput);
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
    for (MyInput input : inputs) {
      StringBuilder spacesPaddle = new StringBuilder();
      int spaces = 15 - input.stringInput.length();

      int spacesNeeded = Math.max(spaces, 0);
      for (int j = 0; j <= spacesNeeded; j++) {
        spacesPaddle.append(" ");
      }
      System.out.println(input.stringInput + spacesPaddle + input.intInput);
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
    Pattern pattern = Pattern.compile("[a-zA-Z]+");
    Matcher matcher = pattern.matcher(someString);

    if (!matcher.matches()) System.out.println("Invalid string input");
    matcher.reset();
    return matcher.matches();
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