package com.simple.exercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ArrayPlay {
  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    int numberOfItems = scanner.nextInt();
    scanner.nextLine();

    if(numberOfItems < 1 || numberOfItems > 4000) terminate();

    List<Integer> listOfNumbers = Arrays.stream(scanner.nextLine().trim().split(" "))
        .limit(numberOfItems)
        .mapToInt(Integer::valueOf)
        .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

    System.out.println("----------------");
    System.out.print("Input: ");
    System.out.println(listOfNumbers.stream().map(Object::toString).collect(Collectors.joining(" ")));
    System.out.println("----------------");

    int numberOfQueries = scanner.nextInt();
    scanner.nextLine();

    if(numberOfQueries < 1 || numberOfQueries > 4000) terminate();

    System.out.println("Number of queries: " + numberOfQueries);

    for(int i = 1; i <= numberOfQueries; i++){
      executeQuery(scanner.nextLine(), listOfNumbers);
    }

    System.out.println("----------------");
    System.out.print("Final array: ");
    System.out.println(listOfNumbers.stream().map(Object::toString).collect(Collectors.joining(" ")));
    System.out.println("----------------");

    terminate();
  }

  private static void executeQuery(String query, List<Integer> listOfNumbers){
    if(query.equals("Insert")){
      String[] queryInput = scanner.nextLine().trim().split(" ");
      int numberIndex = Integer.parseInt(queryInput[0]);
      int number = Integer.parseInt(queryInput[1]);
      listOfNumbers.add(numberIndex, number);
    } else if(query.equals("Delete")){
      int number = scanner.nextInt();
      scanner.nextLine();
      listOfNumbers.remove(listOfNumbers.get(number));
    }

  }

  private static void terminate(){
    scanner.close();
    System.exit(0);
  }
}