package at.michaeladam.pokemonviewer.DataLayer;

import com.google.gson.JsonArray;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                this.back_default = ImageIO.read(new URL(back_def));
            } catch (IOException ex) {
            }
        }
        return back_default;
    }

    public BufferedImage getBack_shiny() {
        if (back_shiny == null) {
            try {
                this.back_shiny = ImageIO.read(new URL(back_shy)); 
            } catch (IOException ex) {
            }
        }
        return back_shiny;
    }

    public BufferedImage getFront_default() {
        if (front_default == null) {
            try {
                this.front_default = ImageIO.read(new URL(front_def));
            } catch (IOException ex) {
            }
        }
        return front_default;
    }

    public BufferedImage getFront_shiny() {

        if (front_shiny == null) {
            try {
                this.front_shiny = ImageIO.read(new URL(front_shy));
            } catch (IOException ex) {
            }
        }
        return front_shiny;
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

}
