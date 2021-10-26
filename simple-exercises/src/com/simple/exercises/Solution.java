package com.simple.exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;

class Result {

  /*
   * Complete the 'staircase' function below.
   *
   * The function accepts INTEGER n as parameter.
   */

  public static void staircase(int n) {
    // Write your code here
    String space = " ";
    for(int i = 0, j = n - 1; i < n && j >= 0; i++, j--){
      System.out.println(String.join("", Collections.nCopies(j, space)).concat("#").concat(String.join("", Collections.nCopies(i, "#"))));
    }
  }
  public int findNumbers(int[] nums) {
    int count = 0;

    for(int num: nums){
      if(String.valueOf(num).length() % 2 == 0) count++;
    }
    return count;
  }
}

public class Solution {
  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(bufferedReader.readLine().trim());

    Result.staircase(n);

    bufferedReader.close();
  }
}
