package org.common.util;

public class GenerateIdCell {

  private int begin;
  private int current;

  public GenerateIdCell(int begin, int current) {
    this.begin = begin;
    this.current = current;
  }

  public int getBegin() {
    return begin;
  }

  public void setBegin(int begin) {
    this.begin = begin;
  }

  public int getCurrent() {
    return current;
  }

  public void setCurrent(int current) {
    this.current = current;
  }

}
