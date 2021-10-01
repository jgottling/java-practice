package com.simple.exercises;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class playWithEOF {

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    int numberOfLines = 1;
    HashMap<Integer, String> lines = new HashMap<>();

    while (!scanner.hasNext("EOF;")) {
      lines.put(numberOfLines, scanner.nextLine());
      numberOfLines++;
    }

    for (Entry<Integer, String> line : lines.entrySet()) {
      System.out.println(line.getKey() + " " + line.getValue());
    }

    scanner.close();

  }
}
