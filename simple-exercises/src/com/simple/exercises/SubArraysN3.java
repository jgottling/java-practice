package com.simple.exercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SubArraysN3 {
  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    int arrayLength = scanner.nextInt();
    scanner.nextLine();
    int[] intArray =
        Arrays.stream(scanner.nextLine().trim().split(" "))
            .limit(arrayLength)
            .mapToInt(Integer::valueOf)
            .toArray();
    System.out.println("-------------");
    System.out.println("Input: ");
    printArray(intArray);
    System.out.println("-------------");
    if (validInput(arrayLength, intArray)) {
      // System.out.println("\nInput ok \n");
      int count = getCountOfNegativeSubArrays(intArray);

      // System.out.println(count);
    } else System.out.println("Invalid input");
    System.out.println("-------------");
    scanner.close();
  }

  private static int getCountOfNegativeSubArrays(int[] intArray) {
    int count = 0;
    for (int lowerPointer = 0; lowerPointer < intArray.length; lowerPointer++) {
      for (int upperPointer = lowerPointer; upperPointer < intArray.length; upperPointer++) {
        List<Integer> temporaryArray = new ArrayList<>();
        System.out.println("lower pointer: " + lowerPointer + "; upperPointer: " + upperPointer);
        for (int i = lowerPointer; i <= upperPointer; i++) temporaryArray.add(intArray[i]);
        System.out.println("Temporary array, size: " + temporaryArray.size());
        printArray(temporaryArray.stream().mapToInt(Integer::valueOf).toArray());
        if (temporaryArray.stream().mapToInt(Integer::valueOf).sum() < 0) count++;
      }
    }
    return count;
  }

  private static boolean validInput(int arrayLength, int[] input) {
    return arrayLength == input.length
        && arrayLength >= 1
        && arrayLength <= 100
        && Arrays.stream(input)
            .noneMatch(number -> number <= (int) -10E+4 || number >= (int) 10E+4);
  }

  private static void printArray(int[] someArray) {
    int lastIndex = someArray.length - 1;
    System.out.print("[ ");
    for (int i = 0; i < lastIndex; i++) {
      System.out.print(someArray[i] + ", ");
    }
    System.out.print(someArray[lastIndex]);
    System.out.print(" ] ");
    System.out.println();
  }
}
