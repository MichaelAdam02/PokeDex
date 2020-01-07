package at.michaeladam.pokemonviewer;

/**
 *
 * @author Michael ADAM
 */
public class PokeMain {
    public static void main(String[] args) {
        
        PokemonHolder ph = PokemonHolder.getInstance();
        
        Thread th = new Thread(ph);
        th.start();
        
        Pokedex pd = new Pokedex();
        pd.setVisible(true);
        
        
    }
}
