package com.percolation;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.util.HashMap;
import java.util.Map.Entry;

public class Percolation {

  private final int[][] grid;
  private final WeightedQuickUnionUF unionFind;
  private final HashMap<Integer, Site> siteList;
  private final int size, maxSize;

  // creates n-by-n grid, with all sites initially blocked
  // i are rows, j are columns
  // border sites with (1..n,1),(1..n,n),(1,1..n)
  // 1 is open, 0 is closed, -1 is full
  public Percolation(int n) {
    this.grid = new int[n][n];
    this.siteList = new HashMap<>();
    this.size = n;
    this.maxSize = n * n + 1;
    this.unionFind = new WeightedQuickUnionUF(this.maxSize);
    int arrayCounter = 1;

    this.siteList.put(0, new Site(0, 1, 0, SiteStatus.OPEN));

    for (int i = 0; i < this.grid.length; i++) {
      for (int j = 0; j < this.grid[i].length; j++) {
        this.grid[i][j] = 0;
        this.siteList.put(arrayCounter, new Site(i + 1, j + 1, arrayCounter));
        arrayCounter++;
      }
    }
    this.siteList.put(arrayCounter, new Site(n + 1, 1, arrayCounter, SiteStatus.OPEN));
    for (Entry<Integer, Site> entry : this.siteList.entrySet()) {
      System.out.println("Key: " + entry.getKey() + " => value: " + entry.getValue());
    }
  }

  private void printGrid() {
    for (int[] ints : this.grid) {
      for (int anInt : ints) {
        System.out.print(anInt + " ");
      }
      System.out.println(); // printing of new line
    }
  }

  // opens the site (row, col) if it is not open already
  public void open(int row, int col) {
    if (this.grid[row - 1][col - 1] == 0) {
      this.grid[row - 1][col - 1] = 1;
      this.getSite(row, col).setSiteStatus(SiteStatus.OPEN);
    }
    this.connectWithNeighbours(row, col);
  }

  private int getSiteNode(int row, int col) {
    int found = -1;
    for (Entry<Integer, Site> entry : this.siteList.entrySet()) {
      Site entrySite = entry.getValue();
      if (entrySite.getRow() == row && entrySite.getCol() == col) found = entry.getKey();
    }
    return found;
  }

  private Site getSite(int row, int col) {
    Site found = null;
    for (Entry<Integer, Site> entry : this.siteList.entrySet()) {
      Site entrySite = entry.getValue();
      if (entrySite.getRow() == row && entrySite.getCol() == col) found = entrySite;
    }
    return found;
  }

  private void connectWithNeighbours(int row, int col) {}

  // is the site (row, col) open?
  public boolean isOpen(int row, int col) {
    return this.getSite(row, col).getSiteStatus() == SiteStatus.OPEN;
  }

  // is the site (row, col) full?
  public boolean isFull(int row, int col) {
    return this.getSite(row, col).getSiteStatus() == SiteStatus.FULL;
  }

  // returns the number of open sites
  public int numberOfOpenSites() {

    int counter = 0;

    for (Entry<Integer, Site> entry : this.siteList.entrySet()) {
      Site entrySite = entry.getValue();
      if (entrySite.siteStatus == SiteStatus.OPEN) counter++;
    }

    return counter;
  }

  // does the system percolate?
  public boolean percolates() {
    return this.unionFind.find(0) == this.unionFind.find(this.size ^ 2 + 2);
  }

  // check if site is (1,1), (1,n), (n,1), (n,n)
  private boolean isCorner(int row, int col) {
    return (row == 1 && (col == 1 || col == this.size))
        || (row == this.size && (col == 1 || col == this.size));
  }

  protected enum SiteStatus {
    CLOSED,
    OPEN,
    FULL
  }

  private class Site implements Comparable<Site> {
    private final Point2D coordinate;
    private final int nodeNumber;
    private SiteStatus siteStatus;

    Site(int row, int col) {
      this(row, col, -1, SiteStatus.CLOSED);
    }

    Site(int row, int col, int nodeNumber, SiteStatus siteStatus) {
      this.coordinate = new Point2D(row, col);
      this.nodeNumber = nodeNumber;
      this.siteStatus = siteStatus;
    }

    Site(int row, int col, int nodeNumber) {
      this(row, col, nodeNumber, SiteStatus.CLOSED);
    }

    protected int getNodeNumber() {
      return this.nodeNumber;
    }

    protected SiteStatus getSiteStatus() {
      return this.siteStatus;
    }

    protected void setSiteStatus(SiteStatus newStatus) {
      this.siteStatus = newStatus;
    }

    @Override
    public boolean equals(Object obj) {
      if (obj.getClass() != Site.class) return false;
      return this.coordinate.equals(((Site) obj).coordinate);
    }

    protected int getCol() {
      return (int) this.coordinate.x();
    }

    protected int getRow() {
      return (int) this.coordinate.y();
    }

    @Override
    public int compareTo(Site site) {
      return this.coordinate.compareTo(site.coordinate);
    }

    @Override
    public String toString() {
      return "Site {"
          + "coordinate= ("
          + this.getCol()
          + ","
          + this.getRow()
          + "), nodeNumber= "
          + this.nodeNumber
          + ", siteStatus= "
          + this.siteStatus
          + " }";
    }
  }
}
