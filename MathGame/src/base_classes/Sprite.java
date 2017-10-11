package base_classes;

import java.awt.*;

/**
 * Class base_classes.Sprite
 * A class for creating sprites
 */
public class Sprite {

    // The Image of the base_classes.Sprite
    private Image image;
    // The dimensions of the base_classes.Sprite
    private int width = 0, height = 0;

    /**
     * Default constructor for the base_classes.Sprite object
     * @param image the Image of the base_classes.Sprite
     * @param width the width of the base_classes.Sprite
     * @param height the height of the base_classes.Sprite
     */
    public Sprite(Image image, int width, int height) {
        this.image = image;
        this.width = width;
        this.height = height;
    }

    /**
     * Alternative constructor for the base_classes.Sprite object
     * The width and height are set to the Image width and height
     * @param image the Image of the base_classes.Sprite
     */
    public Sprite(Image image) {
        this(image, image.getWidth(null), image.getHeight(null));
    }

    /**
     * Get the width of the base_classes.Sprite
     * @return the width of the base_classes.Sprite
     */
    public int getWidth() {
        return (width > 0)? width : image.getWidth(null);
    }

    /**
     * Get the height of the base_classes.Sprite
     * @return the height of the base_classes.Sprite
     */
    public int getHeight() {
        return (height > 0)? height : image.getHeight(null);
    }

    /**
     * Draw the base_classes.Sprite
     * @param g the game graphics
     * @param x the base_classes.Sprite X position
     * @param y the base_classes.Sprite Y position
     */
    public void draw(Graphics g, int x, int y) {
        g.drawImage(image, x, y, width, height, null);
    }

}
