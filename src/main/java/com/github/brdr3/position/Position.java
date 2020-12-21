package com.github.brdr3.position;

public class Position {
  public int x;
  public int y;

  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Position) {
      return this.x == ((Position) o).x && this.y == ((Position) o).y;
    }

    return false;
  }

  @Override
  public String toString() {
    return "(" + this.x + ", " + this.y + ")";
  }

  @Override
  public int hashCode() {
    return this.toString().hashCode();
  }
}
