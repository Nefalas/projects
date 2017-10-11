package helpers;

import base_classes.Sprite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;

/**
 * Class helpers.SpriteSaver
 * A class for managing Sprites to load them only once
 */
public class SpriteSaver {

    // HashMap containing the Sprites
    private HashMap sprites = new HashMap();

    // Instance of the helpers.SpriteSaver class to return
    private static SpriteSaver singleSprite = new SpriteSaver();

    /**
     * Returns the instance of the helpers.SpriteSaver class
     * @return the helpers.SpriteSaver instance
     */
    public static SpriteSaver get() {
        return singleSprite;
    }

    /**
     * Get the base_classes.Sprite corresponding to the given reference, with dimensions
     * @param ref the URL/filepath of the base_classes.Sprite
     * @param width the width of the base_classes.Sprite
     * @param height the height of the base_classes.Sprite
     * @return the base_classes.Sprite
     */
    public Sprite getSprite(String ref, int width, int height) {
        // If base_classes.Sprite already exists in the HashMap, return it
        if (sprites.get(ref) != null) {
            return (Sprite) sprites.get(ref);
        }

        // BufferedImage of the base_classes.Sprite
        BufferedImage sourceImage = null;

        // Get the Image
        try {
            URL url = this.getClass().getClassLoader().getResource(ref);

            if (url == null) {
                errorAndExit("Cannot find ref " + ref);
            }

            sourceImage = ImageIO.read(url);
        } catch (Exception e) {
            errorAndExit("Failed to load " + ref);
        }

        // Get hold of the GraphicsConfiguration
        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        // Get hold of the image
        Image image = gc.createCompatibleImage(sourceImage.getWidth(), sourceImage.getHeight(), Transparency.BITMASK);
        // Draw the BufferedImage to the base_classes.Sprite Image
        image.getGraphics().drawImage(sourceImage, 0, 0, null);

        // Create the base_classes.Sprite
        Sprite sprite = (width != 0 && height != 0) ? new Sprite(image, width, height) : new Sprite(image);
        // Add the base_classes.Sprite to the HashMap
        sprites.put(ref, sprite);

        // Return the base_classes.Sprite
        return sprite;
    }

    /**
     * Get the base_classes.Sprite corresponding to the given reference, without dimensions
     * @param ref the URL/filepath of the base_classes.Sprite
     * @return the base_classes.Sprite
     */
    public Sprite getSprite(String ref) {
        return getSprite(ref, 0, 0);
    }

    /**
     * Print an error and stop the execution of the program
     * @param error the error
     */
    private void errorAndExit(String error) {
        System.err.println(error);
        System.exit(0);
    }

}
