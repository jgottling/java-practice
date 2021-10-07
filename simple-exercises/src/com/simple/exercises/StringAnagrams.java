package com.simple.exercises;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Scanner;

public class StringAnagrams {

  static boolean isAnagram(String a, String b) {
    if(a.length() != b.length()) return false;

    HashMap<Character, Integer> occurrencesA = countCharOccurrences(a.toLowerCase(Locale.ROOT));
    HashMap<Character, Integer> occurrencesB = countCharOccurrences(b.toLowerCase(Locale.ROOT));

    for(Entry<Character, Integer> entry : occurrencesA.entrySet()){
      if(!occurrencesB.containsKey(entry.getKey())) return false;
      if(!Objects.equals(occurrencesB.get(entry.getKey()), entry.getValue())) return false;
    }

    return true;
  }

  private static HashMap<Character, Integer> countCharOccurrences(String a){
    HashMap<Character, Integer> occurrences = new HashMap<>();

    for(int i = 0; i < a.length(); i++){
      int numberOfOccurrences = 0;
      char letter = a.charAt(i);

      for(int j = 0; j < a.length(); j++){
        if(letter == a.charAt(j)) numberOfOccurrences++;
      }
      occurrences.putIfAbsent(letter, numberOfOccurrences);
    }

    System.out.println("Occurrences in: " + a);
    System.out.println(occurrences);
    return occurrences;
  }

  public static void main(String[] args) {

    Scanner scan = new Scanner(System.in);
    String a = scan.next();
    String b = scan.next();
    scan.close();
    boolean ret = isAnagram(a, b);
    System.out.println( (ret) ? "Anagrams" : "Not Anagrams" );
  }
}
