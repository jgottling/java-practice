package com.simple.exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EvenlySplittingStrings {
  public static String getSmallestAndLargest(String s, int k) {
    String smallest = "";
    String largest = "";

    List<String> splittedString = splitString(s, k);
    List<String> sortedString = splittedString.stream().sorted(String::compareTo).collect(Collectors.toList());

    smallest = sortedString.get(0);
    largest = sortedString.get(sortedString.size()-1);

    return smallest + "\n" + largest;
  }

  private static List<String> splitString(String string, int subStringLength){
    ArrayList<String> mySplitString = new ArrayList<>();
    int maxSubstrings = (int) Math.ceil((double) string.length() / subStringLength);
    int finalChunkSize = string.length() % subStringLength;
    int currentIndex = 0;

    for(int i = 0; i < maxSubstrings; i++){
      String subString = "";
      if(i != maxSubstrings - 1) subString = string.substring(currentIndex,currentIndex + subStringLength);
      else subString = string.substring(currentIndex, currentIndex + finalChunkSize);
      mySplitString.add(subString);
      currentIndex += subStringLength;
    }
    return mySplitString;
  }


  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    String s = scan.nextLine();
    int k = scan.nextInt();
    scan.close();

    System.out.println(getSmallestAndLargest(s, k));
  }

}