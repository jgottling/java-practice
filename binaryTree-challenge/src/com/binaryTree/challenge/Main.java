package com.binaryTree.challenge;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    String inputLinks = scanner.nextLine();

    try {
        validateInput(inputLinks);
    } catch (Exception e) {
        endExecution(e.getMessage());
    }

    String[] linksList = inputLinks.split(" ");

    try {
        validateNoDuplicates(linksList);
    } catch (Exception e){
        endExecution(e.getMessage());
    }

    HashMap<Character, MyNode> allMyNodes = createNodes(linksList);

    for(Entry<Character, MyNode> entry: allMyNodes.entrySet()){
        System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
    }

    BinaryTree tree = new BinaryTree(allMyNodes.get(linksList[0].charAt(1)));

    try {
        connectNodes(linksList, allMyNodes, tree);
    } catch (Exception e){
        endExecution(e.getMessage());
    }

    try {
        checkMultipleRoots(allMyNodes, tree);
    } catch (Exception e){
        endExecution(e.getMessage());
    }

    try {
        checkCycles(allMyNodes, tree);
    } catch (Exception e){
        endExecution(e.getMessage());
    }

     endExecution(tree.toString());
  }

  private static void endExecution(String message){
      System.out.println(message);
      scanner.close();
      System.exit(0);
  }

  private static void validateInput(String inputLinks) throws Exception {
    // validate no trailing or beginning whitespaces
      if (inputLinks.length() != inputLinks.trim().length()) {
          throw new Exception("E1");
      }

    String[] nodesList = inputLinks.split(" ");

    for (String node : nodesList) {
        if (!Pattern.matches("(\\([A-Z],[A-Z]\\))", node)) {
            throw new Exception("E1");
        }
    }
  }

  private static void validateNoDuplicates(String[] inputLinks) throws Exception {
    for (int i = 0; i < inputLinks.length; i++) {
      for (int j = 0; j < inputLinks.length; j++) {
        if (i != j) {
            if (inputLinks[i].equals(inputLinks[j])) {
                throw new Exception("E2");
            }
        }
      }
    }
  }

  private static HashMap<Character, MyNode> createNodes(String[] linksList) {
    HashMap<Character, MyNode> nodes = new HashMap<>();
    for (String link : linksList) {
      nodes.putIfAbsent(link.charAt(1), new MyNode(link.charAt(1)));
      nodes.putIfAbsent(link.charAt(3), new MyNode(link.charAt(3)));
    }
    return nodes;
  }

  private static void checkMultipleRoots(HashMap<Character, MyNode> allMyNodes, BinaryTree tree)
      throws Exception {
    for (Character letter : allMyNodes.keySet()) {
        if (!tree.toString().contains(letter.toString())) {
            throw new Exception("E4");
        }
    }
  }

  private static void checkCycles(HashMap<Character, MyNode> allMyNodes, BinaryTree tree) throws Exception {
      String treeString = tree.toString();
      int treeStringLength = treeString.length();
      for (Character letter : allMyNodes.keySet()) {
          int occurences = 0;
          for(int i = 0; i < treeStringLength; i++){
              if(treeString.charAt(i) == letter) occurences++;
              if (occurences == 2) throw new Exception("E5");
          }
      }
  }

  private static void connectNodes(String[] linksList, HashMap<Character, MyNode> allMyNodes,
      BinaryTree tree)
      throws Exception {
    for (String link : linksList) {
      MyNode node1 = allMyNodes.get(link.charAt(1));
      MyNode node2 = allMyNodes.get(link.charAt(3));
      tree.connectNodes(node1, node2);
    }
  }

  private static class MyNode {

    private final char letter;
    private MyNode left;
    private MyNode right;

    public MyNode(char data) {
      left = null;
      right = null;
      letter = data;
    }

    @Override
    public String toString() {
      String leftLetter = left != null ? String.valueOf(left.letter) : "null";
      String rightLetter = right != null ? String.valueOf(right.letter) : "null";
      return "{ Node " + letter + ", left = " + leftLetter + ", right = " + rightLetter + " }";
    }
  }

  private static class BinaryTree {

    private final MyNode root;

    public BinaryTree(MyNode root) {
      this.root = root;
    }

    @Override
    public String toString() {
      return treeAsString(root);
    }

    private String treeAsString(MyNode node) {
        if (node != null) {
            return "(" + node.letter + treeAsString(node.left) + treeAsString(node.right) + ")";
        } else {
            return "";
        }
    }

    public void connectNodes(MyNode node1, MyNode node2) throws Exception {
      // throw error if Parent already has children
        if (node1.left != null && node1.right != null) {
            throw new Exception("E3");
        }
      if (node1.left != null) {
            if (node2.letter < node1.left.letter) {
                node1.right = node1.left;
                node1.left = node2;
            } else {
                node1.right = node2;
            }
        } else {
            node1.left = node2;
        }
    }
  }
}