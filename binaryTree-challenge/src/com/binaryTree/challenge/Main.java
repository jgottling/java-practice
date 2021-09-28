package com.binaryTree.challenge;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final boolean debugLog = true;

    public static void main(String args[]) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        String inputLinks = scanner.nextLine();

        validateInput(inputLinks);

        String[] linksList = inputLinks.split(" ");

        validateNoDuplicates(linksList);
        Arrays.sort(linksList);

        BinaryTree tree = new BinaryTree(new MyNode(linksList[0].charAt(1)));

        connectNodes(tree, linksList);

        if (debugLog) {
            System.out.println("Printing tree: ");
            System.out.println(tree);
        }

        scanner.close();
    }

    private static void validateInput(String inputLinks) throws Exception {
        // validate no trailing or beginning whitespaces
        if (inputLinks.length() != inputLinks.trim().length()) throw new Exception("E1");

        // validate nodes format "(\\([A-Z]\,[A-Z]\\))" (A,B)
        String[] nodesList = inputLinks.split(" ");

        for (String node : nodesList) {
            if (!Pattern.matches("(\\([A-Z],[A-Z]\\))", node)) throw new Exception("E1");
        }

        if (debugLog) System.out.println("Validation: input format OK");
    }

    private static void validateNoDuplicates(String[] inputLinks) throws Exception {
        for (int i = 0; i < inputLinks.length; i++) {
            for (int j = 0; j < inputLinks.length; j++) {
                if (i != j) {
                    if (inputLinks[i].equals(inputLinks[j])) throw new Exception("E2");
                }
            }
        }
        if (debugLog) System.out.println("Validation: no duplicates OK");
    }

    private static void connectNodes(BinaryTree tree, String[] links) throws Exception {
        for (String link : links) {
            tree.connectNodes(link.charAt(1), link.charAt(3));
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
            return "{ Node " + letter + ", left = " + left + ", right = " + right + " }";
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
            } else return "";
        }

        public MyNode getNode(char letter) {
            return getNode(root, letter);
        }

        private MyNode getNode(MyNode myNode, char letter) {
            if (myNode == null) return null;
            if (myNode.letter == letter) return myNode;
            if (myNode.letter < letter) return getNode(myNode.left, letter);
            return getNode(myNode.right, letter);
        }

        public void connectNodes(char letter1, char letter2) throws Exception {
            MyNode node1 = getNode(letter1);
            MyNode node2 = getNode(letter2);

            // Create nodes if they don't exist
            if (node2 == null) node2 = new MyNode(letter2);
            if (node1 == null) node1 = new MyNode(letter1);

            // Connect nodes
            // throw error if Parent already has children
            if (node1.left != null && node1.right != null) throw new Exception("E3");

            System.out.println("\n connecting...");
            System.out.println(node1);
            System.out.println(node2);

            if (node1.left == null) {

                // todo check multiple roots

                // todo check cycles

                // add to free node
                node1.left = node2;
            } else node1.right = node2;

            System.out.println("\n Done connecting");
            System.out.println(node1);
            System.out.println(node2);
        }
    }
}
