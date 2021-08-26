package com.percolation;

import edu.princeton.cs.algs4.In;

public class Main {
  // delay in milliseconds (controls animation speed)
  private static final int DELAY = 100;

  public static void main(String[] args) {
    In in = new In(args[0]); // input file
    int n = in.readInt(); // n-by-n percolation system

    System.out.println("Percolation system of " + n + "x" + n);

    // turn on animation mode
    // StdDraw.enableDoubleBuffering();

    // repeatedly read in sites to open and draw resulting system
    Percolation perc = new Percolation(n);
    //    PercolationVisualizer.draw(perc, n);
    //    StdDraw.show();
    //    StdDraw.pause(DELAY);
    //    while (!in.isEmpty()) {
    //      int i = in.readInt();
    //      int j = in.readInt();
    //      perc.open(i, j);
    //      PercolationVisualizer.draw(perc, n);
    //      StdDraw.show();
    //      StdDraw.pause(DELAY);
    //    }
  }
}
