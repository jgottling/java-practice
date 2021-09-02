package com.percolation;

import java.util.Objects;

enum SiteStatus {
  CLOSED,
  OPEN,
  FULL
}

public class Site {
  private final int row, col, nodeNumber;
  private SiteStatus siteStatus;

  public Site(int row, int col, int nodeNumber, SiteStatus siteStatus) {
    this.row = row;
    this.col = col;
    this.nodeNumber = nodeNumber;
    this.siteStatus = siteStatus;
  }

  public Site(int row, int col, int nodeNumber) {
    this(row, col, nodeNumber, SiteStatus.CLOSED);
  }

  public int getNodeNumber() {
    return this.nodeNumber;
  }

  public SiteStatus getSiteStatus() {
    return this.siteStatus;
  }

  public void setSiteStatus(SiteStatus newStatus) {
    this.siteStatus = newStatus;
  }

  public int getCol() {
    return this.col;
  }

  public int getRow() {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }
    Site site = (Site) o;
    return this.getRow() == site.getRow()
        && this.getCol() == site.getCol()
        && this.getNodeNumber() == site.getNodeNumber();
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getRow(), this.getCol(), this.getNodeNumber()) + 57;
  }
}
