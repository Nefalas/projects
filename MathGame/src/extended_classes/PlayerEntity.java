package extended_classes;

import base_classes.Entity;
import base_classes.Game;
import base_classes.Wall;
import helpers.Helpers;

import java.awt.*;
import java.util.ArrayList;

/**
 * Class extended_classes.PlayerEntity - Extends base_classes.Entity
 * A class to create an base_classes.Entity that the player can control
 */
public class PlayerEntity extends Entity {

    // The maximum distance between the player and a base_classes.Wall
    private final int MAX_WALL_DISTANCE = 15;

    // The game to add the extended_classes.PlayerEntity to
    private Game game;
    // The backpack of the extended_classes.PlayerEntity
    private ArrayList<Entity> backpack;

    /**
     * Default constructor for the extended_classes.PlayerEntity object
     * @param game the game to add the extended_classes.PlayerEntity to
     * @param ref the URL/filepath to the sprite
     * @param x the player X position
     * @param y the player Y position
     */
    public PlayerEntity(Game game, String ref, int x, int y) {
        super(ref, x, y);
        this.game = game;
        backpack = new ArrayList<>();
    }

    /**
     * Move the extended_classes.PlayerEntity and stop it at Walls
     * @param delta time since last loop of the game loop
     */
    public void move(long delta) {
        if (!outOfBoundaries()) {
            stopAtWall();
            super.move(delta);
        }

    }

    /**
     * Add an base_classes.Entity to the backpack
     * @param entity the base_classes.Entity to add
     */
    public void addToBackpack(Entity entity) {
        backpack.add(entity);
        organizeBackpack();
    }

    /**
     * Remove an base_classes.Entity from the backpack
     * @param id the ID of the base_classes.Entity
     */
    public void removeFromBackpack(String id) {
        for (int i = 0; i < backpack.size(); i++) {
            if (backpack.get(i).getId().equals(id)) {
                backpack.remove(i);
                organizeBackpack();
                break;
            }
        }
    }

    /**
     * Empty the backpack
     */
    public void emptyBackpack() {
        backpack = new ArrayList<>();
        organizeBackpack();
    }

    /**
     * Pack the backpack so that there are no empty spaces between the Entities
     */
    public void organizeBackpack() {
        int index = 0;
        for (int i = 0; i < 5; i++) {
            int y = 221 + i * 85;
            for (int j = 0; j < 9; j++) {
                int x = 137 + j * 85;
                if (index < backpack.size()) {
                    backpack.get(index).setXPos(x + 35);
                    backpack.get(index).setYPos(y + 35);
                    index++;
                }
            }
        }
    }

    /**
     * Get the extended_classes.PlayerEntity backpack
     * @return the backpack
     */
    public ArrayList<Entity> getBackpack() {
        return backpack;

    }

    /**
     * Draw the backpack and its Entities
     * @param g the game graphics
     */
    public void showBackpack(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(100, 100, 824, 568);
        // Draw inner rectangle
        g.setColor(Color.white);
        g.fillRect(105, 105, 814, 558);
        // Draw title rectangle
        g.setColor(Color.lightGray);
        g.fillRect(120, 120, 784, 70);
        // Draw title
        g.setColor(Color.black);
        Helpers.drawCenteredString(120, 120, 784, 70, g, "Backpack", new Font("Lucida Blackletter", Font.BOLD, 28));
        // Draw content rectangle
        g.setColor(new Color(197, 195, 255));
        g.fillRect(120, 205, 784, 443);
        // Draw item rectangles
        g.setColor(Color.white);
        int index = 0;
        for (int i = 0; i < 5; i++) {
            int y = 221 + i * 85;
            for (int j = 0; j < 9; j++) {
                int x = 137 + j * 85;
                g.fillRect(x, y, 70, 70);
                if (index < backpack.size()) {
                    backpack.get(index).draw(g);
                    index++;
                }
            }
        }
    }

    /**
     * Get the backpack item at the given position
     * @param mouseX the mouse X position
     * @param mouseY the mouse Y position
     * @return the item at the given position
     */
    public Entity getBackpackItem(int mouseX, int mouseY) {
        for (Entity item : backpack) {
            if (mouseX >= (item.getXPos() - 35) && mouseX <= (item.getXPos() + 35) && mouseY >= (item.getYPos() - 35) && mouseY <= (item.getYPos() + 35)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Returns true if the extended_classes.PlayerEntity is outside of the panel
     * @return true if out of boundaries
     */
    private boolean outOfBoundaries() {
        return (getDx() < 0 && getLeftBoundary() < 0) // If we're moving left and have reached the left hand side
                || (getDx() > 0 && getRightBoundary() > game.WINDOW_WIDTH) // If we're moving right and have reached the right hand side
                || (getDy() < 0 && getTopBoundary() < 0) // If we're moving up and have reached the top side
                || (getDy() > 0 && getBottomBoundary() > game.WINDOW_HEIGHT); // If we're moving down and have reached the bottom side
    }

    /**
     * Set the extended_classes.PlayerEntity speed to 0 if it is going through a base_classes.Wall
     */
    private void stopAtWall() {
        for (Wall wall : game.getCurrentLevel().getWalls()) {
            // If we're moving left
            if (getDx() < 0 && isTooCloseLeft(wall) && isInVerticalRange(wall)) {
                setHorizontalMovement(0);
            }
            // If we're moving right
            if (getDx() > 0 && isTooCloseRight(wall) && isInVerticalRange(wall)) {
                setHorizontalMovement(0);
            }
            // If we're moving up
            if (getDy() < 0 && isTooCloseTop(wall) && isInHorizontalRange(wall)) {
                setVerticalMovement(0);
            }
            // If we're moving down
            if (getDy() > 0 && isTooCloseBottom(wall) && isInHorizontalRange(wall)) {
                setVerticalMovement(0);
            }
        }
    }

    /**
     * Returns true if the extended_classes.PlayerEntity is too close to a base_classes.Wall on the left side
     * @param wall the base_classes.Wall to test
     * @return true if too close to base_classes.Wall to left
     */
    private boolean isTooCloseLeft(Wall wall) {
        return getRightBoundary() - wall.getRightBoundary() < getSpriteWidth() + MAX_WALL_DISTANCE && getRightBoundary() - wall.getRightBoundary() > 0;
    }

    /**
     * Returns true if the extended_classes.PlayerEntity is too close to a base_classes.Wall on the right side
     * @param wall the base_classes.Wall to test
     * @return true if too close to base_classes.Wall to right
     */
    private boolean isTooCloseRight(Wall wall) {
        return wall.getLeftBoundary() - getLeftBoundary() < getSpriteWidth() + MAX_WALL_DISTANCE && wall.getLeftBoundary() - getLeftBoundary() > 0;
    }

    /**
     * Returns true if the extended_classes.PlayerEntity is too close to a base_classes.Wall over it
     * @param wall the base_classes.Wall to test
     * @return true if too close to base_classes.Wall at the top
     */
    private boolean isTooCloseTop(Wall wall) {
        return getBottomBoundary() - wall.getBottomBoundary() < getSpriteHeight() + MAX_WALL_DISTANCE && getBottomBoundary() - wall.getBottomBoundary() > 0;
    }

    /**
     * Returns true if the extended_classes.PlayerEntity is too close to a base_classes.Wall under it
     * @param wall the base_classes.Wall to test
     * @return true if too close to base_classes.Wall at the bottom
     */
    private boolean isTooCloseBottom(Wall wall) {
        return wall.getTopBoundary() - getTopBoundary() < getSpriteHeight() + MAX_WALL_DISTANCE && wall.getTopBoundary() - getTopBoundary() > 0;
    }

    /**
     * Returns true if the extended_classes.PlayerEntity is in the vertical range of a base_classes.Wall
     * @param wall the base_classes.Wall to test
     * @return true if in vertical range
     */
    private boolean isInVerticalRange(Wall wall) {
        return getBottomBoundary() - 10 >= wall.getTopBoundary() && getTopBoundary() + 10 <= wall.getBottomBoundary();
    }

    /**
     * Return true if the extended_classes.PlayerEntity is in the horizontal range of a base_classes.Wall
     * @param wall the base_classes.Wall to test
     * @return true if in horizontal range
     */
    private boolean isInHorizontalRange(Wall wall) {
        return getRightBoundary() - 10 >= wall.getLeftBoundary() && getLeftBoundary() + 10 <= wall.getRightBoundary();
    }

}
