package com.github.brdr3.direction;

import java.util.Arrays;

public enum Direction {
  N("N"), S("S"), E("E"), O("O");

  String direction;

  private Direction(String direction) {
    this.direction = direction;
  }

  public static Direction getDirection(String d) {
    return Arrays.stream(values()).filter(direction -> Direction.valueOf(d).equals(direction)).findFirst().orElse(null);
  }
}