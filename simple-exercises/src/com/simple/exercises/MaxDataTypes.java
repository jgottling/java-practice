package com.simple.exercises;

import java.util.Scanner;

public class MaxDataTypes {
  private static final Scanner scanner = new Scanner(System.in);
  private static final long minInt = Integer.MIN_VALUE;
  private static final long maxInt = Integer.MAX_VALUE;
  private static final long minLong = Long.MIN_VALUE;
  private static final long maxLong = Long.MAX_VALUE;

  public static void main(String[] argh) {
    int testCases = scanner.nextInt();
    for (int i = 0; i < testCases; i++) {
      try {
        long longInput = scanner.nextLong();
        System.out.println(longInput + " can be fitted in:");
        if (longInput >= -128 && longInput <= 127) System.out.println("* byte");
        if (longInput >= -32768 && longInput <= 32767) System.out.println("* short");
        if (longInput >= minInt && longInput < maxInt) System.out.println("* int");
        if (longInput >= minLong && longInput < maxLong) System.out.println("* long");
      } catch (Exception e) {
        System.out.println(scanner.next() + " can't be fitted anywhere.");

      }
    }
  }
}
