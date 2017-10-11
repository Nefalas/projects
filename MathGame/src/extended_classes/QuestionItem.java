package extended_classes;

import base_classes.ActionBox;
import base_classes.Game;
import base_classes.GameItem;

import java.awt.*;
import java.util.ArrayList;

/**
 * Class extended_classes.QuestionItem - Extends base_classes.GameItem
 * A class for creating items that asks questions
 */
public class QuestionItem extends GameItem {

    // The ActionBoxes of the extended_classes.QuestionItem
    ArrayList<ActionBox> actionBoxes = new ArrayList<>();

    /**
     * Default constructor for the extended_classes.QuestionItem object
     * @param x the extended_classes.QuestionItem X position
     * @param y the extended_classes.QuestionItem Y position
     * @param game the game to add the extended_classes.QuestionItem to
     */
    public QuestionItem(int x, int y, Game game) {
        super("Question item", x, y, "questionItem.png", 96, 96, game);
    }

    /**
     * Draw the info box of the extended_classes.QuestionItem
     * @param g the game graphics
     */
    public void drawItemInfo(Graphics g) {
        super.drawItemInfo(g);
        for (ActionBox actionBox : actionBoxes) {
            actionBox.draw(g);
        }
    }

    /**
     * Add an base_classes.ActionBox to the extended_classes.QuestionItem
     * @param actionBox the base_classes.ActionBox to add
     */
    public void addActionBox(ActionBox actionBox) {
        actionBoxes.add(actionBox);
    }

    /**
     * Get the ActionsBoxes of the extended_classes.QuestionItem
     * @return the ActionBoxes
     */
    public ArrayList<ActionBox> getActionBoxes() {
        return actionBoxes;
    }

}
