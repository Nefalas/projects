package levels;

import base_classes.*;
import extended_classes.NumberEntity;

import java.awt.*;

/**
 * Class levels.LevelTwo - Extends base_classes.Level
 */
public class LevelTwo extends Level {

    // Color of the walls
    final private Color WALL_BASE_COLOR = new Color(95, 255, 154);

    // Char array for layout
    final private char[][] LAYOUT_GRID = {
            {'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},
            {'|','!',' ',' ',' ',' ',' ',' ','|',' ',' ',' ',' ',' ',' ','|'},
            {'|',' ','*','|','-','-','|',' ','|',' ','|','-','|','-',' ','|'},
            {'|','-','-','|',' ',' ','|',' ',' ',' ','|','n','|',' ',' ','|'},
            {'|',' ',' ',' ',' ',' ','|','-','-','-','|','-','|',' ','-','|'},
            {'|',' ',' ','|',' ',' ',' ',' ','|',' ',' ',' ','|',' ',' ','|'},
            {'|',' ','-','-','-','-',' ','|','|',' ','|',' ','|','|',' ','|'},
            {'|',' ',' ',' ',' ','|',' ','|',' ',' ','|',' ',' ','|',' ','|'},
            {'|','-','-','-',' ','|',' ',' ',' ','|','-','|',' ','|',' ','|'},
            {'|','?',' ','|',' ','|',' ','-','-','|',' ','|',' ','|',' ','|'},
            {'|',' ',' ',' ',' ','|',' ',' ',' ',' ',' ','|',' ',' ',' ','|'},
            {'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'}
    };

    // Creating NumberEntities
    private NumberEntity num1;
    private NumberEntity num2;
    private NumberEntity num3;
    private NumberEntity num4;
    private NumberEntity num5;
    private NumberEntity num6;
    private NumberEntity num7;
    private NumberEntity num8;

    /**
     * Default constructor for the levels.LevelTwo class
     * @param game the game to add the level to
     */
    public LevelTwo(Game game) {
        super(game, 70, 70);

        // Creating base_classes.GridLayout and adding it to the level
        base_classes.GridLayout gridLayout = new base_classes.GridLayout(12, 16, LAYOUT_GRID, WALL_BASE_COLOR, game);
        addGridLayout(gridLayout);

        // Assigning NumberEntities
        num1 = new NumberEntity().randomPosition(gridLayout.getEmptyPos()).randomize(1, 9);
        num2 = new NumberEntity().randomPosition(gridLayout.getEmptyPos()).randomize(1, 9);
        num3 = new NumberEntity().randomPosition(gridLayout.getEmptyPos()).randomize(1, 9);
        num4 = new NumberEntity().randomPosition(gridLayout.getEmptyPos()).randomize(1, 9);
        num5 = new NumberEntity().randomPosition(gridLayout.getEmptyPos()).randomize(1, 9);
        num6 = new NumberEntity().randomPosition(gridLayout.getEmptyPos()).randomize(1, 9);
        num7 = new NumberEntity().randomPosition(gridLayout.getEmptyPos()).randomize(1, 9);
        num8 = new NumberEntity().randomPosition(gridLayout.getEmptyPos()).randomize(1, 9);

        // Adding NumberEntities to the level
        addEntity(num1);
        addEntity(num2);
        addEntity(num3);
        addEntity(num4);
        addEntity(num5);
        addEntity(num6);
        addEntity(num7);
        addEntity(num8);
    }

}
