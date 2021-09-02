package com.percolation;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.Stopwatch;

public class Main {
  // delay in milliseconds (controls animation speed)
  private static final int DELAY = 100;

  public static void main(String[] args) {
    In in = new In(args[0]); // input file
    int n = in.readInt(); // n-by-n percolation system

    System.out.println("Percolation system of " + n + "x" + n);

    Stopwatch stopWatch = new Stopwatch();

    // turn on animation mode
    StdDraw.enableDoubleBuffering();

    // repeatedly read in sites to open and draw resulting system
    Percolation perc = new PercolationWeightedUnionFindGrid(n);
    // perc.open(1, 1);
    // perc.open(2, 1);
    // perc.open(3, 1);
    // System.out.println(perc.percolates());
    // perc.open(2, 1);
    // System.out.println(perc.percolates());
    PercolationVisualizer.draw(perc, n);
    StdDraw.show();
    StdDraw.pause(DELAY);
    while (!in.isEmpty()) {
      int i = in.readInt();
      int j = in.readInt();
      perc.open(i, j);
      PercolationVisualizer.draw(perc, n);
      StdDraw.show();
      StdDraw.pause(DELAY);
    }

    if (perc.percolates()) System.out.println("System percolates");
    else System.out.println("System doesn't percolate");

    System.out.println("Elapsed time: " + stopWatch.elapsedTime() + " seconds");

    System.exit(0);
  }
}
