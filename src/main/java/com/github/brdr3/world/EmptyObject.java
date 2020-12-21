package com.github.brdr3.world;

public class EmptyObject implements WorldObject {
  @Override
  public void interact(WorldObject worldObject) {
  }

  @Override
  public String toString() {
    return "Empty Space";
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof Object;
  }
}