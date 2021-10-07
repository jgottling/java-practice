package com.simple.exercises;

import java.util.Scanner;

public class PowLoops {
  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String []argh){
    String[][] queries;

    int queriesSize = scanner.nextInt();
    scanner.nextLine();

    if (!(queriesSize >= 0 && queriesSize <= 500)) endProgram();

    queries = new String[queriesSize][];

    for(int i=0; i < queriesSize;i++){
      queries[i] = readQuery();
    }

    for(int i=0; i < queriesSize;i++){
      showQueryResult(queries[i]);
    }

    endProgram();
  }

  private static void endProgram(){
    scanner.close();
    System.exit(0);
  }

  private static String[] readQuery(){
    return scanner.nextLine().split(" ");
  }

  private  static void showQueryResult(String[] query){
    String finalOutput = "";
    int numberA = Integer.parseInt(query[0]);
    int numberB = Integer.parseInt(query[1]);
    int exponentN = Integer.parseInt(query[2]);

    validateNumber(numberA);
    validateNumber(numberB);
    validateExponent(exponentN);

    for (int i = 0; i < exponentN ; i++){
      String output = calculateSeries(numberA, numberB, i);
      output = output.concat(" ");
      finalOutput = finalOutput.concat(output);
    }

    System.out.println(finalOutput.trim());

    scanner.close();
  }

  private static String calculateSeries(int numberA, int numberB, int exponent){
    int result = numberA;

   for (int i = 0; i <= exponent; i++) result += (int) Math.pow(2, i) * numberB;

    return String.valueOf(result);
  }

  private static void validateNumber(int number){
    if (!(number >= 0 && number <= 50)) endProgram();
  }

  private static void validateExponent(int exponent){
    if(!(exponent >= 1 && exponent <= 15)) endProgram();
  }
}