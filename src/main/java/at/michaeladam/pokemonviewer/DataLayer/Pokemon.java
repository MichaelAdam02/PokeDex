package at.michaeladam.pokemonviewer.DataLayer;

import at.michaeladam.pokemonviewer.Businesslogic.CodeInternet;
import static at.michaeladam.pokemonviewer.Businesslogic.CodeInternet.resizeImage;
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

   public static int pokesize = 240;

    public static int getPokesize() {
        return pokesize;
    }

    public static void setPokesize(int pokesize) {
        Pokemon.pokesize = pokesize;
    }
 
    public final int ID;
    public final String name;
    public final String front_def, front_shy, back_def, back_shy;
    public transient BufferedImage front_default, front_shiny, back_default, back_shiny;

    /*
    * Generates a pokemon with the data of the API
    *
     */
    public Pokemon(int ID, String name, String front_def, String front_shy, String back_def, String back_shy) {
        this.ID = ID;
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1);
        this.front_def = front_def;
        this.front_shy = front_shy;
        this.back_def = back_def;
        this.back_shy = back_shy;
    }
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

    public BufferedImage getFront_default() {
        if (front_default == null) {
            try {
                BufferedImage bf = ImageIO.read(new URL(front_def));
                this.front_default =bf; 
            } catch (IOException ex) {
            }
        }
        return  resizeImage(front_default, pokesize, pokesize);
    }

    public BufferedImage getFront_shiny() {

        if (front_shiny == null) {
            try {
                BufferedImage bf = ImageIO.read(new URL(front_shy));
                this.front_shiny = bf; 
            } catch (IOException ex) {
            }
        }
        return resizeImage(front_shiny,pokesize,pokesize);
    }

    @Override
    public String toString() {
        return "ID=" + ID + " name=" + name;
    }

    public void preLoad() {
        getBack_default();
        getBack_shiny();
        getFront_default();
        getFront_shiny();
    }

    String typeString = "";

    public void addTypes(JsonArray types) {
        for (int i = 0; i < types.size(); i++) {
            typeString += types.get(i).getAsJsonObject().get("type").getAsJsonObject().get("name").getAsString() + " ";
        }
    }

    public String getType() {
        return typeString;
    }

    public void setBack_default(BufferedImage back_default) {
        this.back_default = back_default;
    }

    public void setBack_shiny(BufferedImage back_shiny) {
        this.back_shiny = back_shiny;
    }

    public void setFront_shiny(BufferedImage front_shiny) {
        this.front_shiny = front_shiny;
    }

    public void setFront_default(BufferedImage front_default) {
        this.front_default = front_default;
    }

}
