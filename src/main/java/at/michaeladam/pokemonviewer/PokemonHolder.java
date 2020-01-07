/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.michaeladam.pokemonviewer;

import at.michaeladam.pokemonviewer.Businesslogic.API_Reader;
import at.michaeladam.pokemonviewer.Businesslogic.PokeConfig;
import at.michaeladam.pokemonviewer.DataLayer.Pokemon;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael ADAM
 */
public class PokemonHolder implements Runnable, Serializable {

    private final API_Reader apr = new API_Reader();
    private HashMap<Integer, Pokemon> pokeMap ;

    public Pokemon getPokemon(int id) {
        if (pokeMap.containsKey(id)) {
            return pokeMap.get(id);
        }
        Pokemon pokemon = apr.getPokemon(id);
        pokeMap.put(id, pokemon);
        return pokemon;
    }

  
    public void saveMap() {
       
    }

    private void loadMap() { 
       pokeMap = new HashMap<>();
    }

    @Override
    public void run() {

        for (int i = 1; i <= PokeConfig.POKECOUNT; i++) {
            if (!pokeMap.containsKey(i)) { 
                Pokemon pokemon = apr.getPokemon(i);
                pokeMap.put(i, pokemon);
                pokemon.preLoad();
            }

        }

    }

    private PokemonHolder() {
        loadMap();
    }

    public static PokemonHolder getInstance() {
        return PokemonHolderHolder.INSTANCE;
    }

    private static class PokemonHolderHolder {

        private static final PokemonHolder INSTANCE = new PokemonHolder();

    }
}
