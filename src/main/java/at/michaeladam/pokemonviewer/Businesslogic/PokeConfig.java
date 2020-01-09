package at.michaeladam.pokemonviewer.Businesslogic;

/**
 *
 * @author Michael ADAM
 */
public class PokeConfig {

    //Wo die pokemons gespeichert werden
    public static final String IMAGE_FILE_URL = System.getProperty("user.home") + "/pokemonProjectTemp";
    //API BaseUrl
    private static final String BASE_URL = "https://pokeapi.co/api/v2/";
    //Url eines Pokemons, %id% ist ein platzhalter
    public static final String PKMN_URL = BASE_URL + "pokemon/%id%";
    //ID des letzten Pokemons welches verfügbar ist in der ersten Generation.
    public static final int POKECOUNT = 151;

    public static int pokesize = 240;

    public static int getPokesize() {
        return pokesize;
    }

    public static void setPokesize(int pokesize) {
        PokeConfig.pokesize = pokesize;
    }

}
