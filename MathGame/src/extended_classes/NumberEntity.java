package extended_classes;

import base_classes.Entity;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class extended_classes.NumberEntity - Extends base_classes.Entity
 * A class for creating Entities containing a number from 0 to 9 that can be interacted with
 */
public class NumberEntity extends Entity {

    // The random number generator
    private static Random rand = new Random();
    // The number of the extended_classes.NumberEntity
    private int number;

    /**
     * Default constructor of the extended_classes.NumberEntity object
     * @param x the X position of the extended_classes.NumberEntity
     * @param y the Y position of the extended_classes.NumberEntity
     */
    public NumberEntity(int x, int y) {
        super("number-0.png", x, y);
    }

    /**
     * Alternative constructor of the extended_classes.NumberEntity object
     * The position is set to 0, 0
     */
    public NumberEntity() {
        this(0, 0);
    }

    /**
     * Set position to a random position among the empty positions from the base_classes.GridLayout
     * @param emptyPos the empty positions
     * @return the extended_classes.NumberEntity
     */
    public NumberEntity randomPosition(ArrayList<int[]> emptyPos) {
        setXPos(emptyPos.get(0)[0]);
        setYPos(emptyPos.get(0)[1]);
        emptyPos.remove(0);
        return this;
    }

    /**
     * Set the number to a random number between the given numbers
     * @param min the minimum value
     * @param max the maximum value (included)
     * @return the extended_classes.NumberEntity
     */
    public NumberEntity randomize(int min, int max) {
        number = min + rand.nextInt(max - min + 1);
        setRef("number-" + number + ".png");
        return this;
    }

    /**
     * Set the number to the given number
     * @param number the number value
     * @return the extended_classes.NumberEntity
     */
    public NumberEntity setNumber(int number) {
        this.number = number;
        setRef("number-" + number + ".png");
        return this;
    }

    /**
     * Get the value of the extended_classes.NumberEntity
     * @return the number value
     */
    public int getValue() {
        return number;
    }
}
