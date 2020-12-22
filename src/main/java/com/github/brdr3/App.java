package com.github.brdr3;

import java.util.Scanner;

import com.github.brdr3.human.Human;
import com.github.brdr3.position.Position;
import com.github.brdr3.world.PokemonWorld;

public class App {
    public static void main(String[] args) {
        PokemonWorld pokemonWorld = new PokemonWorld();
        Human ash = new Human("Ash");
        pokemonWorld.putOnMap(ash, new Position(0, 0));

        Scanner x = new Scanner(System.in);
        String directions = x.nextLine();

        directions.chars().mapToObj(d -> String.valueOf((char) d)).forEach(d -> pokemonWorld.move(ash, d));

        // System.out.println(pokemonWorld.getMap());
        System.out.println(ash.getCaughtPokemons());
        x.close();
    }
}
