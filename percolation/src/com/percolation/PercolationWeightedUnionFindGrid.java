package com.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationWeightedUnionFindGrid implements Percolation {
  private final WeightedQuickUnionUF unionFind;
  private final Object[][] siteGrid;
  private final int size, maxSize;

  // creates n-by-n grid, with all sites initially blocked
  // i are rows, j are columns
  // border sites with (1..n,1),(1..n,n),(1,1..n)
  public PercolationWeightedUnionFindGrid(int n) {
    this.siteGrid = new Object[n + 2][n];
    this.size = n;
    this.maxSize = n * n + 2;
    this.unionFind = new WeightedQuickUnionUF(this.maxSize);
    int arrayCounter = 1;

    System.out.println("Size: " + n);
    System.out.println("MaxSize: " + this.maxSize);
    System.out.println("Nodes count: " + this.unionFind.count());

    this.siteGrid[0][0] = new Site(0, 1, 0, SiteStatus.OPEN);

    for (int i = 1; i <= this.size; i++) {
      for (int j = 0; j < this.size; j++) {
        this.siteGrid[i][j] = new Site(i, j + 1, arrayCounter);
        arrayCounter++;
      }
    }

    this.siteGrid[n + 1][0] = new Site(n + 1, 1, arrayCounter, SiteStatus.OPEN);

    // this.printGrid();
  }

  private void printGrid() {
    for (int i = 1; i <= this.size; i++) {
      for (int j = 0; j < this.size; j++) {
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
      this.connectWithNeighbours(site);
    }
    // this.printUnionFind();
    // this.printGrid();
  }

  private Site getSite(int row, int col) {
    return (Site) this.siteGrid[row][col - 1];
  }

  private void connectWithNeighbours(Site connectingSite) {
    int row = connectingSite.getRow();
    int col = connectingSite.getCol();
    int connectingNode = connectingSite.getNodeNumber();

    // topVirtualNode
    if (row == 1) {
      this.connectNodes(0, connectingNode);
      this.getSite(0, 1).setSiteStatus(SiteStatus.FULL);
      connectingSite.setSiteStatus(SiteStatus.FULL);
    }

    // bottomVirtualNode
    if (row == this.size) {
      this.connectNodes(connectingNode, this.maxSize - 1);
      connectingSite.setSiteStatus(SiteStatus.FULL);
      this.getSite(this.size + 1, 1).setSiteStatus(SiteStatus.FULL);
    }

    // up
    if (row - 1 >= 1) {
      Site upSite = this.getSite(row - 1, col);
      if (upSite.getSiteStatus() != SiteStatus.CLOSED) {
        this.connectNodes(connectingNode, upSite.getNodeNumber());
        connectingSite.setSiteStatus(SiteStatus.FULL);
        upSite.setSiteStatus(SiteStatus.FULL);
      }
    }
    // down
    if (row + 1 <= this.size) {
      Site downSite = this.getSite(row + 1, col);
      if (downSite.getSiteStatus() != SiteStatus.CLOSED) {
        this.connectNodes(connectingNode, downSite.getNodeNumber());
        connectingSite.setSiteStatus(SiteStatus.FULL);
        downSite.setSiteStatus(SiteStatus.FULL);
      }
    }
    // left
    if (col - 1 >= 1) {
      Site leftSite = this.getSite(row, col - 1);
      if (leftSite.getSiteStatus() != SiteStatus.CLOSED) {
        this.connectNodes(connectingNode, leftSite.getNodeNumber());
        connectingSite.setSiteStatus(SiteStatus.FULL);
        leftSite.setSiteStatus(SiteStatus.FULL);
      }
    }
    // right
    if (col + 1 <= this.size) {
      Site rightSite = this.getSite(row, col + 1);
      if (rightSite.getSiteStatus() != SiteStatus.CLOSED) {
        this.connectNodes(connectingNode, rightSite.getNodeNumber());
        connectingSite.setSiteStatus(SiteStatus.FULL);
        rightSite.setSiteStatus(SiteStatus.FULL);
      }
    }
    //    this.printGrid();
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

    for (int i = 1; i <= this.size; i++) {
      for (int j = 1; j <= this.size; j++) {
        if (this.getSite(i, j).getSiteStatus() == SiteStatus.OPEN) counter++;
      }
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
}
