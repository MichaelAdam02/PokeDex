package at.michaeladam.pokemonviewer.Businesslogic;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 *
 * @author Michael ADAM
 */
public class Helper {

    /**    
    * @param source: an Image wich needs to get resized
    * @param width: planned width of the new image
    * @param height: planned height of the new image
    * @return a resized image in the size of the paramters
    */
    public static BufferedImage resizeImage(BufferedImage source, int width, int height) {

        Image resizedImage = source.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage returnal = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB); //ARGB-Color model because the image has opacity

        Graphics2D grahpics = returnal.createGraphics();
        grahpics.drawImage(resizedImage, 0, 0, null);

        return returnal;
    }
}
