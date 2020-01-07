package at.michaeladam.pokemonviewer;

import at.michaeladam.pokemonviewer.Businesslogic.API_Reader;
import at.michaeladam.pokemonviewer.Businesslogic.PokeConfig;
import at.michaeladam.pokemonviewer.DataLayer.Pokemon;
import java.io.Serializable;
import java.util.HashMap;

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
       //TODO
    }

    private void loadMap() { 
       //TODO
       pokeMap = new HashMap<>();
    }

    /*
       Loads Pokemon from the api in the baground of the program
    
    */
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
