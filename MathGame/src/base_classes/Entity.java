package base_classes;

import helpers.SpriteSaver;

import java.awt.*;
import java.util.UUID;

/**
 * Class base_classes.Entity
 * A class for creating entities in the game
 */
public class Entity {

    // The current x location of this entity
    private double x;
    // The current y location of this entity
    private double y;
    // The sprite that represents this entity
    private Sprite sprite;
    // The current speed of this entity horizontally (pixels/sec)
    private double dx;
    // The current speed of this entity vertically (pixels/sec)
    private double dy;

    // base_classes.Entity unique ID
    private String id;

    /**
     * Default constructor for the base_classes.Entity object
     * @param ref URL/filepath to the sprite image
     * @param x horizontal start position
     * @param y vertical start position
     */
    public Entity(String ref, int x, int y, int width, int height) {
        this.sprite = SpriteSaver.get().getSprite(ref, width, height);
        this.x = x;
        this.y = y;
        id = UUID.randomUUID().toString();
    }

    public Entity(String ref, int x, int y) {
        this.sprite = SpriteSaver.get().getSprite(ref);
        this.x = x;
        this.y = y;
        id = UUID.randomUUID().toString();
    }

    /**
     * Move the entity
     * @param delta time since last loop of the game loop
     */
    public void move(long delta) {
        x += (delta * dx) / 1000;
        y += (delta * dy) / 1000;
    }

    /**
     * Draw the entity
     * @param g the game graphics
     */
    public void draw(Graphics g) {
        sprite.draw(g, (int)(x - sprite.getWidth()/2), (int)(y - sprite.getHeight()/2));
    }

    /**
     * Returns true if the player is closer than 40 pixels to the entity
     * @param playerX player horizontal position
     * @param playerY player vertical position
     * @return true if player is close
     */
    public boolean playerIsClose(int playerX, int playerY) {
        return Math.sqrt(Math.pow(x - playerX, 2) + Math.pow(y - playerY, 2)) < 40;
    }

    /**
     * Set the URL/filepath to the sprite image
     * @param ref the URL/filepath
     */
    public void setRef(String ref) {
        this.sprite = SpriteSaver.get().getSprite(ref);
    }

    /**
     * Get the ID of the entity
     * @return the ID
     */
    public String getId() {
        return id;
    }

    /**
     * Get the entity value
     * @return -1 since it has no value
     */
    public int getValue() {
        return -1;
    }

    /**
     * Move to the left for an amount of pixels
     * @param pixels the amount of pixels
     */
    public void moveLeft(int pixels) {
        x -= pixels;
    }

    /**
     * Move to the right for an amount of pixels
     * @param pixels the amount of pixels
     */
    public void moveRight(int pixels) {
        x += pixels;
    }

    /**
     * Move up for an amount of pixels
     * @param pixels the amount of pixels
     */
    public void moveUp(int pixels) {
        y -= pixels;
    }

    /**
     * Move down for an amount of pixels
     * @param pixels the amount of pixels
     */
    public void moveDown(int pixels) {
        y += pixels;
    }

    /**
     * Get the horizontal position
     * @return entity X position
     */
    public int getXPos() {
        return (int)x;
    }

    /**
     * Get the vertical position
     * @return entity Y position
     */
    public int getYPos() {
        return (int)y;
    }

    /**
     * Set the horizontal position
     * @param x entity X position
     */
    public void setXPos(int x) {
        this.x = x;
    }

    /**
     * Set the vertical position
     * @param y entity Y position
     */
    public void setYPos(int y) {
        this.y = y;
    }

    /**
     * Get the horizontal speed
     * @return entity X speed
     */
    public double getDx() {
        return dx;
    }

    /**
     * Get the vertical speed
     * @return entity Y speed
     */
    public double getDy() {
        return dy;
    }

    /**
     * Set the horizontal speed
     * @param dx entity X speed
     */
    public void setHorizontalMovement(double dx) {
        this.dx = dx;
    }

    /**
     * Set the vertical speed
     * @param dy entity Y speed
     */
    public void setVerticalMovement(double dy) {
        this.dy = dy;
    }

    /**
     * Get the sprite left border
     * @return sprite left border
     */
    public int getLeftBoundary() {
        return (int)(x - sprite.getWidth()/2) - 5;
    }

    /**
     * Get the sprite right border
     * @return sprite right border
     */
    public int getRightBoundary() {
        return (int)(x + sprite.getWidth()/2) + 5;
    }

    /**
     * Get the sprite top border
     * @return sprite top border
     */
    public int getTopBoundary() {
        return (int)(y - sprite.getHeight()/2) - 5;
    }

    /**
     * Get the sprite bottom border
     * @return sprite bottom border
     */
    public int getBottomBoundary() {
        return (int)(y + sprite.getHeight()/2) + 5;
    }

    /**
     * Get the height of the sprite
     * @return height of the sprite
     */
    public int getSpriteHeight() {
        return sprite.getHeight();
    }

    /**
     * Get the width of the sprite
     * @return width of the sprite
     */
    public int getSpriteWidth() {
        return sprite.getWidth();
    }
}
