package at.michaeladam.pokemonviewer.DataLayer;

import at.michaeladam.pokemonviewer.Businesslogic.Helper;
import static at.michaeladam.pokemonviewer.Businesslogic.Helper.resizeImage;
import static at.michaeladam.pokemonviewer.Businesslogic.PokeConfig.pokesize;
import com.google.gson.JsonArray;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author Michael ADAM
 */
public class Pokemon implements Serializable {

    public final int ID;
    public final String name;
    public final String front_def, front_shy, back_def, back_shy;
    public transient BufferedImage front_default, front_shiny, back_default, back_shiny;

    /**
     *
     * @param ID ID of the Pokemon
     * @param name Name of the Pokemon
     * @param front_def API-Url for the front default sprite
     * @param front_shy API-Url for the front shiny sprite
     * @param back_def API-Url for the back default sprite
     * @param back_shy API-Url for the back shiny sprite
     */
    public Pokemon(int ID, String name, String front_def, String front_shy, String back_def, String back_shy) {
        this.ID = ID;
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1);
        this.front_def = front_def;
        this.front_shy = front_shy;
        this.back_def = back_def;
        this.back_shy = back_shy;
    }

    /**
     * downloads the image if it has not been downloaded yet.
     *
     * @return returns the saved/downloaded image and resizes it.
     */
    public BufferedImage getBack_default() {
        if (back_default == null) {
            try {
                BufferedImage bf = ImageIO.read(new URL(back_def));
                this.back_default = bf;
            } catch (IOException ex) {
            }
        }
        return resizeImage(back_default, pokesize, pokesize);
    }

    /**
     * downloads the image if it has not been downloaded yet.
     *
     * @return returns the saved/downloaded image and resizes it.
     */
    public BufferedImage getBack_shiny() {
        if (back_shiny == null) {
            try {
                BufferedImage bf = ImageIO.read(new URL(back_shy));
                this.back_shiny = bf;
            } catch (IOException ex) {
            }
        }
        return resizeImage(back_shiny, pokesize, pokesize);
    }

    /**
     * downloads the image if it has not been downloaded yet.
     *
     * @return returns the saved/downloaded image and resizes it.
     */
    public BufferedImage getFront_default() {
        if (front_default == null) {
            try {
                BufferedImage bf = ImageIO.read(new URL(front_def));
                this.front_default = bf;
            } catch (IOException ex) {
            }
        }
        return resizeImage(front_default, pokesize, pokesize);
    }

    /**
     * downloads the image if it has not been downloaded yet.
     *
     * @return returns the saved/downloaded image and resizes it.
     */
    public BufferedImage getFront_shiny() {

        if (front_shiny == null) {
            try {
                BufferedImage bf = ImageIO.read(new URL(front_shy));
                this.front_shiny = bf;
            } catch (IOException ex) {
            }
        }
        return resizeImage(front_shiny, pokesize, pokesize);
    }

    @Override
    public String toString() {
        return "ID=" + ID + " name=" + name;
    }

    /**
     * calls all get*_*(); methods to preload all images
     */
    public void preLoad() {
        getBack_default();
        getBack_shiny();
        getFront_default();
        getFront_shiny();
    }

    String typeString = "";

    /**
     *
     * @param types: Is an jasonarray containing 1 or 2 types as Strings.
     * @save saves the type String
     */
    public void addTypes(JsonArray types) {
        for (int i = 0; i < types.size(); i++) {
            typeString += types.get(i).getAsJsonObject().get("type").getAsJsonObject().get("name").getAsString() + " ";
        }
    }

    /**
     *
     * @return the type-String
     */
    public String getType() {
        return typeString;
    }

    /**
     *
     * @param back_default unscaled! image
     */
    public void setBack_default(BufferedImage back_default) {
        this.back_default = back_default;
    }

    /**
     *
     * @param back_shiny unscaled! image
     */
    public void setBack_shiny(BufferedImage back_shiny) {
        this.back_shiny = back_shiny;
    }

    /**
     *
     * @param front_shiny unscaled! image
     */
    public void setFront_shiny(BufferedImage front_shiny) {
        this.front_shiny = front_shiny;
    }

    /**
     *
     * @param front_default unscaled! image
     */
    public void setFront_default(BufferedImage front_default) {
        this.front_default = front_default;
    }

}
