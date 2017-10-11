package extended_classes;

import base_classes.Game;
import base_classes.GameItem;

/**
 * Class extended_classes.LessonItem - Extends base_classes.GameItem
 * A class for creating items that give lessons
 */
public class LessonItem extends GameItem {

    /**
     * Default constructor for the extended_classes.LessonItem object
     * @param x the extended_classes.LessonItem X position
     * @param y the extended_classes.LessonItem Y position
     * @param game the game to add the extended_classes.LessonItem to
     */
    public LessonItem(int x, int y, Game game) {
        super("Lesson Item", x, y, "lessonItem.png", 96, 96, game);
    }

}
