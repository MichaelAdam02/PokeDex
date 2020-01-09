package at.michaeladam.pokemonviewer;

import at.michaeladam.pokemonviewer.Businesslogic.PokemonApiExtractor;
import at.michaeladam.pokemonviewer.Businesslogic.PokeConfig;
import static at.michaeladam.pokemonviewer.Businesslogic.PokeConfig.IMAGE_FILE_URL;
import at.michaeladam.pokemonviewer.DataLayer.Pokemon;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import static java.lang.Thread.sleep;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Singleton-class
 * @author Michael ADAM
 */
public class PokemonHolder implements Runnable, Serializable {

    private final PokemonApiExtractor apiAccess = PokemonApiExtractor.getInstance();
    private Map<Integer, Pokemon> pokeMap;

    /**
     * @param id
     * @return creates or gets the pokemon with the id TODO: Async
     * @see Pokemon
     */
    public Pokemon getPokemon(int id) {
        if (pokeMap.containsKey(id)) {
            return pokeMap.get(id);
        }
        Pokemon pokemon = apiAccess.getPokemon(id);
        pokeMap.put(id, pokemon);
        return pokemon;
    }

    /**
     * Saves all image files and pokeman data from the specified folder
     *
     * @throws java.io.IOException
     */
    public void saveMap() throws IOException {
        RUNNING = false;
        while (!done) {
            System.out.println("waiting for thread...");
            try {
                sleep(500);
            } catch (InterruptedException ex) {
            }
        }
        new File(IMAGE_FILE_URL).mkdir();

        for (Map.Entry<Integer, Pokemon> en : pokeMap.entrySet()) {
            Pokemon pkm = en.getValue();
            String url = IMAGE_FILE_URL + "\\" + en.getKey() + "\\";

            new File(url.substring(0, url.length() - 1)).mkdir();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(url + "data.bin"));
            oos.writeObject(pkm);
            oos.flush();

            ImageIO.write(pkm.getBack_default(), "png", new File(url + "bd.png"));
            ImageIO.write(pkm.getBack_shiny(), "png", new File(url + "bs.png"));
            ImageIO.write(pkm.getFront_default(), "png", new File(url + "fd.png"));
            ImageIO.write(pkm.getFront_shiny(), "png", new File(url + "fs.png"));

        }
    }

    /**
     * Loads all image files and pokeman data from the specified folder
     */
    private void loadMap() {
        pokeMap = Collections.synchronizedMap(new HashMap<>());
        File f = new File(IMAGE_FILE_URL);
        File[] OrdnerListe = f.listFiles();
        if (OrdnerListe != null) {
            for (File ordner : OrdnerListe) {
                try {
                    Pokemon pkm;
                    try (ObjectInputStream data = new ObjectInputStream(new FileInputStream(ordner + "\\data.bin"))) {
                        pkm = (Pokemon) data.readObject();
                    }

                    pkm.setBack_default(ImageIO.read(new File(ordner + "\\bd.png")));
                    pkm.setBack_shiny(ImageIO.read(new File(ordner + "\\bs.png")));
                    pkm.setFront_default(ImageIO.read(new File(ordner + "\\fd.png")));
                    pkm.setFront_shiny(ImageIO.read(new File(ordner + "\\fs.png")));
                    pokeMap.put(Integer.parseInt(ordner.getName()), pkm);

                } catch (IOException ex) {
                    Logger.getLogger(PokemonHolder.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(PokemonHolder.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

    }

    boolean RUNNING = true;
    boolean done = false;

    /**
     * Loads Pokemon from the api in the background
     */
    @Override
    public void run() {

        for (int pokemonID = 1; pokemonID < PokeConfig.POKECOUNT && RUNNING; pokemonID++) {
            if (!pokeMap.containsKey(pokemonID)) {
                Pokemon pokemon = apiAccess.getPokemon(pokemonID);
                pokeMap.put(pokemonID, pokemon);
                pokemon.preLoad();
            }

        }
        System.out.println("done");
        done = true;

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
