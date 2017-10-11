package base_classes;

import java.awt.*;

/**
 * Class base_classes.ActionBox
 * A class for creating clickable boxes that can contain an entity
 */
public class ActionBox {

    // Horizontal center position
    private int x;
    // Vertical center position
    private int y;
    // Size of the box
    private int size;

    // Containing an entity
    private boolean hasEntity = false;

    // Waiting for an entity (after a click)
    private boolean waitingForItem = false;

    // base_classes.Entity holder
    private Entity entity;

    /**
     * Default constructor for the base_classes.ActionBox object
     * @param x horizontal center position
     * @param y vertical center position
     * @param size size of the box
     */
    public ActionBox(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    /**
     * Draw the base_classes.ActionBox and its entity if it has one
     * @param g the game graphics
     */
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x - size/2, y - size/2, size, size);
        if (hasEntity) {
            entity.draw(g);
        }
    }

    /**
     * Returns true if the mouse position matches the base_classes.ActionBox position
     * @param mouseX mouse click X position
     * @param mouseY mouse click Y position
     * @return true if the box is clicked
     */
    public boolean clicked(int mouseX, int mouseY) {
        return (mouseX >= (x - size/2) && mouseX <= (x + size/2) && mouseY >= (y - size/2) && mouseY <= (y + size/2));
    }

    /**
     * Set the waiting for item state
     * @param waitingForItem state of the variable
     */
    public void setWaitingForItem(boolean waitingForItem) {
        this.waitingForItem = waitingForItem;
    }

    /**
     * Get the waiting for item state
     * @return true if waiting for item
     */
    public boolean isWaitingForItem() {
        return waitingForItem;
    }

    /**
     * Set base_classes.ActionBox entity
     * @param entity the entity
     */
    public void setEntity(Entity entity) {
        entity.setXPos(x);
        entity.setYPos(y);
        this.entity = entity;
        hasEntity = true;
    }

    /**
     * Get base_classes.ActionBox entity
     * @return
     */
    public Entity getEntity() {
        return entity;
    }

    /**
     * Remove the base_classes.ActionBox entity
     */
    public void removeEntity() {
        entity = null;
        hasEntity = false;
    }

    /**
     * Returns true if the base_classes.ActionBox has an entity
     * @return true
     */
    public boolean hasEntity() {
        return hasEntity;
    }

}
