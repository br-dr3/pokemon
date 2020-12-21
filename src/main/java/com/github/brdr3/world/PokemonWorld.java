package com.github.brdr3.world;

import java.util.HashMap;

import com.github.brdr3.direction.Direction;
import com.github.brdr3.pokemon.Pokemon;
import com.github.brdr3.position.Position;

public class PokemonWorld {
  HashMap<Position, WorldObject> map = new HashMap<>();

  public PokemonWorld() {
    this.map.put(new Position(0, 0), new Pokemon());
  }

  public HashMap<Position, WorldObject> getMap() {
    return this.map;
  }

  public void putOnMap(WorldObject object, Position p) {
    if (!this.map.containsKey(p)) {
      this.map.put(p, new Pokemon());
    }

    object.interact(map.get(p));
    this.map.put(p, object);
  }

  public void move(WorldObject object, String d) {
    Direction direction = Direction.getDirection(d);
    Position objectPosition = getObjectPosition(object);

    putOnMap(object, this.getNewPosition(objectPosition, direction));
    putOnMap(new EmptyObject(), objectPosition);
  }

  private Position getNewPosition(Position oldPosition, Direction d) {
    if (d.equals(Direction.N)) {
      return new Position(oldPosition.x, oldPosition.y + 1);
    }

    if (d.equals(Direction.S)) {
      return new Position(oldPosition.x, oldPosition.y - 1);
    }

    if (d.equals(Direction.E)) {
      return new Position(oldPosition.x + 1, oldPosition.y);
    }

    if (d.equals(Direction.O)) {
      return new Position(oldPosition.x - 1, oldPosition.y);
    }

    throw new RuntimeException("Could not define new position due to direction.");
  }

  private Position getObjectPosition(WorldObject object) {
    Position p = this.map.entrySet().stream().filter(e -> e.getValue().equals(object)).map(e -> e.getKey()).findFirst()
        .orElse(null);

    if (p == null) {
      throw new RuntimeException("Could not find object in PokemonWorld.");
    }

    return p;
  }
}