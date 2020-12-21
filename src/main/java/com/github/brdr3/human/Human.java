package com.github.brdr3.human;

import com.github.brdr3.pokemon.Pokemon;
import com.github.brdr3.world.WorldObject;

public class Human implements WorldObject {
  String name;
  int caughtPokemons;

  public Human(String name) {
    this.name = name;
    this.caughtPokemons = 0;
  }

  private void catchPokemon() {
    this.caughtPokemons++;
  }

  public int getCaughtPokemons() {
    return this.caughtPokemons;
  }

  @Override
  public void interact(WorldObject worldObject) {
    if (worldObject instanceof Pokemon) {
      this.catchPokemon();
    }
  }

  @Override
  public String toString() {
    return this.name;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Human) {
      return ((Human) o).name.equals(this.name);
    }

    return false;
  }
}