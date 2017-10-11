package levels;

import base_classes.*;
import extended_classes.LessonItem;
import extended_classes.NumberEntity;
import extended_classes.QuestionItem;

import java.awt.*;

/**
 * Class levels.LevelOne - Extends base_classes.Level
 * A class for creating the level one
 */
public class LevelOne extends Level {

    // Color of the walls
    final private Color WALL_BASE_COLOR = Color.red.darker().darker();

    // Char array for layout
    final private char[][] LAYOUT_GRID = {
            {'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},
            {'|','!',' ','*','|',' ',' ',' ','|',' ',' ',' ','|',' ','?','|'},
            {'|',' ',' ',' ','|',' ',' ',' ','|',' ',' ',' ','|',' ',' ','|'},
            {'|',' ',' ',' ','|',' ','|',' ','|',' ','|',' ','|',' ',' ','|'},
            {'|','-','-',' ','|',' ','|',' ','|',' ','|',' ','|',' ',' ','|'},
            {'|',' ',' ',' ','|',' ','|',' ','|',' ','|',' ','|',' ',' ','|'},
            {'|',' ',' ',' ','|',' ','|',' ','|',' ','|',' ','|',' ',' ','|'},
            {'|',' ','-','-','|',' ','|',' ','|',' ','|',' ','|',' ',' ','|'},
            {'|',' ',' ',' ',' ',' ','|',' ','|',' ','|',' ','|',' ',' ','|'},
            {'|',' ',' ',' ',' ',' ','|',' ',' ',' ','|',' ',' ',' ',' ','|'},
            {'|',' ',' ',' ',' ',' ','|',' ',' ',' ','|',' ',' ',' ',' ','|'},
            {'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'}
    };

    // Creating ActionBoxes
    private ActionBox actionBox1 = new ActionBox(570, 300, 70);
    private ActionBox actionBox2 = new ActionBox(700, 300, 70);
    private ActionBox actionBox3 = new ActionBox(570, 400, 70);
    private ActionBox actionBox4 = new ActionBox(700, 400, 70);

    // Creating NumberEntities
    NumberEntity num1;
    NumberEntity num2;
    NumberEntity num3;
    NumberEntity num4;

    /**
     * Default constructor for the levels.LevelOne class
     * @param game the game to add the level to
     */
    public LevelOne(Game game) {
        super(game);

        // Creating base_classes.GridLayout and adding it to the level
        base_classes.GridLayout gridLayout = new base_classes.GridLayout(12, 16, LAYOUT_GRID, WALL_BASE_COLOR, game);
        addGridLayout(gridLayout);

        // Assigning NumberEntities
        num1 = new NumberEntity().randomPosition(gridLayout.getEmptyPos()).randomize(1, 9);
        num2 = new NumberEntity().randomPosition(gridLayout.getEmptyPos()).randomize(1, 9);
        num3 = new NumberEntity().randomPosition(gridLayout.getEmptyPos()).randomize(1, 9);
        num4 = new NumberEntity().randomPosition(gridLayout.getEmptyPos()).randomize(1, 9);

        // Adding NumberEntities to the level
        addEntity(num1);
        addEntity(num2);
        addEntity(num3);
        addEntity(num4);

        // Getting hold of the extended_classes.LessonItem
        LessonItem firstLesson = gridLayout.getLessonItem();

        // Setting the content of the left panel of the extended_classes.LessonItem
        firstLesson.setContent("Welcome to your first lesson ! \n" +
                "This one will be very basic since its purpose is to get you " +
                "to understand the mechanics of this game. " +
                "In every level you will find a Lesson item, " +
                "like this one, and a Question item. You will " +
                "also find different items on the ground that " +
                "you will need in order to pass the level. " +
                "The Lesson item teaches you a math lesson " +
                "and the Question item tests you on your " +
                "newly learned skills. \n" +
                "In this level you will have to use additions " +
                "and subtractions. Pick up the numbers you " +
                "see lying on the ground and place them in " +
                "the right order in the question items. \n" +
                "Good luck !");

        // Setting the content of the right panel of the extended_classes.LessonItem
        firstLesson.setActionContent("This is the example section. \n" +
                "As you might have guessed, this section shows you how to succeed the level. \n" +
                "Here's the example for this level:");

        // Adding ActionEntities to the info box of the extended_classes.LessonItem
        firstLesson.addActionEntity(new NumberEntity(570, 380).setNumber(3));
        firstLesson.addActionEntity(new NumberEntity(700, 380).setNumber(6));

        firstLesson.addSingleString("+", 619, 395, 40, 35, 148, 188);
        firstLesson.addSingleString("= 9", 750, 395, 40, 35, 148, 188);

        firstLesson.addActionEntity(new NumberEntity(570, 480).setNumber(8));
        firstLesson.addActionEntity(new NumberEntity(700, 480).setNumber(3));

        firstLesson.addSingleString("-", 627, 495, 40, 35, 148, 188);
        firstLesson.addSingleString("= 5", 750, 495, 40, 35, 148, 188);

        // Getting hold of the extended_classes.QuestionItem
        QuestionItem questionItem = gridLayout.getQuestionItem();

        // Setting the content of the left panel of the extended_classes.QuestionItem
        questionItem.setContent("This is the Question item. The left panel " +
                "contains the instructions for the exercise. \n" +
                "For this exercise you simply have to place the numbers you " +
                "picked up in the boxes so that the operations are correct. To place " +
                "a number, click on the box. Your backpack will open and you'll simply " +
                "have to click on the number you want to place. If you placed the wrong " +
                "number, click on it and you'll be able to change it.");

        // Setting the content of the right panel of the extended_classes.QuestionItem
        questionItem.setActionContent("Place your numbers in the correct boxes.");

        // Adding ActionBoxes to the info box of the extended_classes.QuestionItem
        questionItem.addActionBox(actionBox1);
        questionItem.addActionBox(actionBox2);
        questionItem.addActionBox(actionBox3);
        questionItem.addActionBox(actionBox4);

        questionItem.addSingleString("+", 619, 315, 40, 35, 148, 188);
        questionItem.addSingleString("= " + (num1.getValue() + num2.getValue()), 750, 315, 40, 35, 148, 188);

        questionItem.addSingleString("-", 627, 415, 40, 35, 148, 188);
        questionItem.addSingleString("= " + (num3.getValue() - num4.getValue()), 750, 415, 40, 35, 148, 188);
    }

    /**
     * Call this to pass the level
     */
    public void passLevel() {
        if (actionBox1.getEntity().getValue() + actionBox2.getEntity().getValue() == num1.getValue() + num2.getValue()
                && actionBox3.getEntity().getValue() - actionBox4.getEntity().getValue() == num3.getValue() - num4.getValue()) {
            super.passLevel();
        }
    }

}
