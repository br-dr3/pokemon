package com.github.brdr3.pokemon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;

import com.github.brdr3.human.Human;
import com.github.brdr3.position.Position;
import com.github.brdr3.world.EmptyObject;
import com.github.brdr3.world.PokemonWorld;
import com.github.brdr3.world.WorldObject;

import org.junit.jupiter.api.Test;

public class PokemonTest {
  @Test
  public void putEmptyObjectOnMapWhereAlreadyExistsPokemonShouldAddEmptyObjectInThatPosition() {
    PokemonWorld world = new PokemonWorld();
    world.putOnMap(new EmptyObject(), new Position(0, 0));

    HashMap<Position, WorldObject> pokemonWorldMap = world.getMap();
    assertEquals(new EmptyObject(), pokemonWorldMap.get(new Position(0, 0)));
  }

  @Test
  public void putEmptyObjectOnMapWhereExistsHumanShouldAddEmptyObjectInThatPosition() {
    PokemonWorld world = new PokemonWorld();
    world.putOnMap(new Human("Test"), new Position(0, 0));

    world.putOnMap(new EmptyObject(), new Position(0, 0));

    HashMap<Position, WorldObject> pokemonWorldMap = world.getMap();
    assertEquals(new EmptyObject(), pokemonWorldMap.get(new Position(0, 0)));
  }

  @Test
  public void putHumanOnMapWhereExistsPokemonShouldAddHumanInThatPosition() {
    PokemonWorld world = new PokemonWorld();
    world.putOnMap(new Human("Test"), new Position(0, 0));

    HashMap<Position, WorldObject> pokemonWorldMap = world.getMap();
    assertEquals(new Human("Test"), pokemonWorldMap.get(new Position(0, 0)));
  }

  @Test
  public void putHumanOnMapWhereExistsEmptyObjectShouldAddHumanInThatPosition() {
    PokemonWorld world = new PokemonWorld();
    world.putOnMap(new EmptyObject(), new Position(0, 0));

    world.putOnMap(new Human("Test"), new Position(0, 0));

    HashMap<Position, WorldObject> pokemonWorldMap = world.getMap();
    assertEquals(new Human("Test"), pokemonWorldMap.get(new Position(0, 0)));
  }

  @Test
  public void humanCatchPokemon() {
    PokemonWorld world = new PokemonWorld();
    Human test = new Human("Test");
    assertEquals(0, test.getCaughtPokemons());

    world.putOnMap(test, new Position(0, 0));

    assertEquals(1, test.getCaughtPokemons());
  }

  @Test
  public void humanDoesntCatchEmptyObject() {
    PokemonWorld world = new PokemonWorld();
    Human test = new Human("Test");

    assertEquals(test.getCaughtPokemons(), 0);

    world.putOnMap(new EmptyObject(), new Position(1, 1));
    world.putOnMap(test, new Position(1, 1));

    assertEquals(0, test.getCaughtPokemons());
  }

  @Test
  public void humanMoveNorth() {
    PokemonWorld world = new PokemonWorld();
    Human test = new Human("Test");
    Position position = new Position(10, 10);
    Position northPosition = new Position(10, 11);

    world.putOnMap(test, position);
    world.move(test, "N");

    HashMap<Position, WorldObject> pokemonMap = world.getMap();

    WorldObject emptyObject = pokemonMap.get(position);
    WorldObject human = pokemonMap.get(northPosition);

    assertEquals(test, human);
    assertEquals(new EmptyObject(), emptyObject);
  }

  @Test
  public void humanMoveSouth() {
    PokemonWorld world = new PokemonWorld();
    Human test = new Human("Test");
    Position position = new Position(10, 10);
    Position northPosition = new Position(10, 9);

    world.putOnMap(test, position);
    world.move(test, "S");

    HashMap<Position, WorldObject> pokemonMap = world.getMap();

    WorldObject emptyObject = pokemonMap.get(position);
    WorldObject human = pokemonMap.get(northPosition);

    assertEquals(test, human);
    assertEquals(new EmptyObject(), emptyObject);
  }

  @Test
  public void humanMoveEast() {
    PokemonWorld world = new PokemonWorld();
    Human test = new Human("Test");
    Position position = new Position(10, 10);
    Position northPosition = new Position(11, 10);

    world.putOnMap(test, position);
    world.move(test, "E");

    HashMap<Position, WorldObject> pokemonMap = world.getMap();

    WorldObject emptyObject = pokemonMap.get(position);
    WorldObject human = pokemonMap.get(northPosition);

    assertEquals(test, human);
    assertEquals(new EmptyObject(), emptyObject);
  }

  @Test
  public void humanMoveWest() {
    PokemonWorld world = new PokemonWorld();
    Human test = new Human("Test");
    Position position = new Position(10, 10);
    Position northPosition = new Position(9, 10);

    world.putOnMap(test, position);
    world.move(test, "O");

    HashMap<Position, WorldObject> pokemonMap = world.getMap();

    WorldObject emptyObject = pokemonMap.get(position);
    WorldObject human = pokemonMap.get(northPosition);

    assertEquals(test, human);
    assertEquals(new EmptyObject(), emptyObject);
  }

  @Test
  public void exceptionWhenDirectionDoesntExist() {
    PokemonWorld world = new PokemonWorld();
    Human test = new Human("Test");

    world.putOnMap(test, new Position(0, 0));

    assertThrows(RuntimeException.class, () -> {
      world.move(test, "Z");
    });
  }

  @Test
  public void exceptionWhenObjectIsNotPresent() {
    PokemonWorld world = new PokemonWorld();

    assertThrows(RuntimeException.class, () -> {
      world.move(new Human("Fake"), "N");
    });
  }
}