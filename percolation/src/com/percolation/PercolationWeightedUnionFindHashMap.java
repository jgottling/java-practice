package com.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.util.HashMap;
import java.util.Map.Entry;

public class PercolationWeightedUnionFind implements Percolation {

  private final WeightedQuickUnionUF unionFind;
  private final HashMap<Integer, Site> siteList;
  private final int size, maxSize;

  // creates n-by-n grid, with all sites initially blocked
  // i are rows, j are columns
  // border sites with (1..n,1),(1..n,n),(1,1..n)
  public PercolationWeightedUnionFind(int n) {
    this.siteList = new HashMap<>();
    this.size = n;
    this.maxSize = n * n + 2;
    this.unionFind = new WeightedQuickUnionUF(this.maxSize);
    int arrayCounter = 1;

    System.out.println("MaxSize: " + this.maxSize);
    System.out.println("Nodes count: " + this.unionFind.count());

    this.siteList.put(0, new Site(0, 1, 0, SiteStatus.OPEN));

    for (int i = 1; i <= this.size; i++) {
      for (int j = 1; j <= this.size; j++) {
        this.siteList.put(arrayCounter, new Site(i, j, arrayCounter));
        arrayCounter++;
      }
    }

    this.siteList.put(arrayCounter, new Site(n + 1, 1, arrayCounter, SiteStatus.OPEN));

    // this.printHashMap();
    // this.printGrid();
  }

  private void printHashMap() {
    for (Entry<Integer, Site> entry : this.siteList.entrySet()) {
      System.out.println("Key: " + entry.getKey() + " => value: " + entry.getValue());
    }
    System.out.println();
  }

  private void printGrid() {
    for (int i = 1; i <= this.size; i++) {
      for (int j = 1; j <= this.size; j++) {
        System.out.print(this.getSite(i, j).getSiteStatus() + " ");
      }
      System.out.println();
    }
    System.out.println();
  }

  private void printUnionFind() {
    for (int i = 0; i <= this.maxSize - 1; i++) {
      System.out.println("Node: " + i + " Root: " + this.unionFind.find(i));
    }
    System.out.println();
  }

  // opens the site (row, col) if it is not open already
  @Override
  public void open(int row, int col) {
    Site site = this.getSite(row, col);
    if (site.getSiteStatus() == SiteStatus.CLOSED) {
      site.setSiteStatus(SiteStatus.OPEN);
      this.connectWithNeighbours(row, col, site);
    }
    // this.printHashMap();
    // this.printUnionFind();
    // this.printGrid();
  }

  private Site getSite(int row, int col) {
    Site found = null;
    for (Entry<Integer, Site> entry : this.siteList.entrySet()) {
      Site entrySite = entry.getValue();
      if (entrySite.getRow() == row && entrySite.getCol() == col) found = entrySite;
    }
    return found;
  }

  private void connectWithNeighbours(int row, int col, Site connectingSite) {
    int connectingNode = connectingSite.getNodeNumber();
    // topVirtualNode
    if (row == 1) {
      this.connectNodes(0, connectingNode);
      this.siteList.get(0).setSiteStatus(SiteStatus.FULL);
      connectingSite.setSiteStatus(SiteStatus.FULL);
    }
    // bottomVirtualNode
    if (row == this.size) {
      this.connectNodes(connectingNode, this.maxSize - 1);
      connectingSite.setSiteStatus(SiteStatus.FULL);
      this.siteList.get(this.maxSize - 1).setSiteStatus(SiteStatus.FULL);
    }
    // up
    if (row - 1 >= 1) {
      Site upSite = this.getSite(row - 1, col);
      if (upSite.siteStatus != SiteStatus.CLOSED) {
        this.connectNodes(connectingNode, upSite.getNodeNumber());
        connectingSite.setSiteStatus(SiteStatus.FULL);
        upSite.setSiteStatus(SiteStatus.FULL);
      }
    }
    // down
    if (row + 1 <= this.size) {
      Site downSite = this.getSite(row + 1, col);
      if (downSite.siteStatus != SiteStatus.CLOSED) {
        this.connectNodes(connectingNode, downSite.getNodeNumber());
        connectingSite.setSiteStatus(SiteStatus.FULL);
        downSite.setSiteStatus(SiteStatus.FULL);
      }
    }
    // left
    if (col - 1 >= 1) {
      Site leftSite = this.getSite(row, col - 1);
      if (leftSite.siteStatus != SiteStatus.CLOSED) {
        this.connectNodes(connectingNode, leftSite.getNodeNumber());
        connectingSite.setSiteStatus(SiteStatus.FULL);
        leftSite.setSiteStatus(SiteStatus.FULL);
      }
    }
    // right
    if (col + 1 <= this.size) {
      Site rightSite = this.getSite(row, col + 1);
      if (rightSite.siteStatus != SiteStatus.CLOSED) {
        this.connectNodes(connectingNode, rightSite.getNodeNumber());
        connectingSite.setSiteStatus(SiteStatus.FULL);
        rightSite.setSiteStatus(SiteStatus.FULL);
      }
    }
    //    this.printGrid();
    //    this.printHashMap();
  }

  private void connectNodes(int node1, int node2) {
    // System.out.println("Connecting Node: " + node1 + " with Node: " + node2 + "...");
    if (node1 < node2) this.unionFind.union(node1, node2);
    else if (node1 > node2) this.unionFind.union(node2, node1);
    // System.out.println();
  }

  @Override
  public boolean isOpen(int row, int col) {
    return this.getSite(row, col).getSiteStatus() == SiteStatus.OPEN;
  }

  @Override
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

  @Override
  public boolean percolates() {
    //    this.printGrid();
    return this.unionFind.find(0) == this.unionFind.find(this.maxSize - 1);
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

  private class Site {
    private final int row, col, nodeNumber;
    private SiteStatus siteStatus;

    Site(int row, int col, int nodeNumber, SiteStatus siteStatus) {
      this.row = row;
      this.col = col;
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

    protected int getCol() {
      return this.col;
    }

    protected int getRow() {
      return this.row;
    }

    @Override
    public String toString() {
      return "Site {"
          + "coordinate= ("
          + this.getRow()
          + ","
          + this.getCol()
          + "), nodeNumber= "
          + this.nodeNumber
          + ", siteStatus= "
          + this.siteStatus
          + " }";
    }
  }
}
