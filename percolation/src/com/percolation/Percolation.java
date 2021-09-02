package com.percolation;

public interface Percolation {

  void open(int row, int col);

  // is the site (row, col) full?
  boolean isFull(int row, int col);

  // is the site (row, col) open?
  boolean isOpen(int row, int col);

  // does the system percolate?
  boolean percolates();
}
