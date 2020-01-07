package at.michaeladam.pokemonviewer.Businesslogic;

/**
 *
 * @author Michael ADAM
 */
public class PokeConfig {

    private static final String BASE_URL = "https://pokeapi.co/api/v2/";
    public static final String PKMN_URL = BASE_URL+"pokemon/%id%";
    //ID des letzten Pokemons welches verfügbar ist.
    public static final int POKECOUNT = 649;
}
