package base_classes;

import java.awt.*;

/**
 * Class base_classes.Wall
 * A class for creating walls
 */
public class Wall {

    // Position of the upper left corner
    private int x, y;
    // Width and height of the base_classes.Wall
    private int width, height;
    // Color of the base_classes.Wall
    private Color color;

    /**
     * Default constructor for the base_classes.Wall object
     * @param x the upper left corner X position
     * @param y the upper left corner Y position
     * @param width the width of the base_classes.Wall
     * @param height the height of the base_classes.Wall
     * @param color the color of the base_classes.Wall
     */
    public Wall(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /**
     * Draw the base_classes.Wall
     * @param g the game graphics
     */
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    /**
     * Get the X position of the left border
     * @return left border X position
     */
    public int getLeftBoundary() {
        return x;
    }

    /**
     * Get the X position of the right border
     * @return right border X position
     */
    public int getRightBoundary() {
        return x + width;
    }

    /**
     * Get the Y position of the top border
     * @return top border Y position
     */
    public int getTopBoundary() {
        return y;
    }

    /**
     * Get the Y position of the bottom border
     * @return bottom border Y position
     */
    public int getBottomBoundary() {
        return y + height;
    }

}
