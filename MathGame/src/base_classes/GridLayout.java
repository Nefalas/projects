package base_classes;

import extended_classes.LessonItem;
import extended_classes.NumberEntity;
import extended_classes.QuestionItem;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Class base_classes.GridLayout
 * A class for creating walls and items based on a two dimensional char array
 *      '-' or '|' for Walls
 *      '*' for start position
 *      'n' for NumberEntities
 *      '!' for extended_classes.LessonItem
 *      '?' for extended_classes.QuestionItem
 */
public class GridLayout {

    // Container for the walls
    private ArrayList<Wall> walls = new ArrayList<>();
    // Container for the NumberEntities
    private ArrayList<NumberEntity> numberEntities = new ArrayList<>();
    // extended_classes.LessonItem of the game
    private LessonItem lessonItem = null;
    // extended_classes.QuestionItem of the game
    private QuestionItem questionItem = null;
    // Start position of the player
    int startX = 0, startY = 0;
    // Empty positions
    ArrayList<int[]> emptyPos = new ArrayList<>();

    // Number of rows and columns
    private int rows, columns;
    // Color of the walls
    private Color color;
    // base_classes.Game to add the walls to
    private Game game;

    // The dimensions of the walls
    private double width, height;

    // Dash size
    final static float dash1[] = {8.0f};
    // BasicStroke object
    final static BasicStroke dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);

    /**
     * Default constructor for the base_classes.GridLayout object
     * @param rows the number of rows
     * @param columns the number of columns
     * @param gridArray the two dimensional char array
     * @param color the color of the walls
     * @param game the game to add the elements to
     */
    public GridLayout(int rows, int columns, char[][] gridArray, Color color, Game game) {
        this.game = game;
        this.rows = rows;
        this.columns = columns;
        this.color = color;

        // The width of horizontal walls
        width = (double)(game.WINDOW_WIDTH - 10) / (columns - 1);
        // The height of vertical walls
        height = (double)(game.WINDOW_HEIGHT - 10) / (rows - 1);

        convertArray(gridArray);
    }

    /**
     * Get the ArrayList of the empty positions of the base_classes.GridLayout object
     * @return the empty positions
     */
    public ArrayList<int[]> getEmptyPos() {
        return emptyPos;
    }

    /**
     * Get the NumberEntities of the GridLayour object
     * @return the NumberEntities
     */
    public ArrayList<NumberEntity> getNumberEntities() {
        return numberEntities;
    }

    /**
     * Get the Walls of the base_classes.GridLayout object
     * @return the Walls
     */
    public ArrayList<Wall> getWalls() {
        return walls;
    }

    /**
     * Get the X start position of the player
     * @return the X start position
     */
    public int getStartX() {
        return startX;
    }

    /**
     * Get the Y start position of the player
     * @return the Y start position
     */
    public int getStartY() {
        return startY;
    }

    /**
     * Get the extended_classes.LessonItem of the base_classes.GridLayout object
     * @return the extended_classes.LessonItem
     */
    public LessonItem getLessonItem() {
        return lessonItem;
    }

    /**
     * Get the extended_classes.QuestionItem of the base_classes.GridLayout object
     * @return the extended_classes.QuestionItem
     */
    public QuestionItem getQuestionItem() {
        return questionItem;
    }

    /**
     * Draw the grid used by the GridWall
     * @param g the game graphics
     */
    public void drawGrid(Graphics2D g) {
        g.setStroke(dashed);
        g.setColor(Color.black.brighter());
        for (int i = 0; i < rows; i++) {
            g.drawLine(0, (int)(i * height) + 5, game.WINDOW_WIDTH, (int)(i * height) + 5);
        }
        for (int i = 0; i < columns; i++) {
            g.drawLine((int)(i * width) + 5, 0, (int)(i * width) + 5, game.WINDOW_HEIGHT);
        }
        g.setStroke(null);
    }

    /**
     * Returns true if the character represents a base_classes.Wall
     * @param c the character to test
     * @return true if char is a base_classes.Wall
     */
    private boolean isWall(char c) {
        return (c == '|' || c == '-');
    }

    /**
     * Convert a two dimensional char array to walls and items
     * @param gridArray the two dimensional char array
     */
    private void convertArray(char[][] gridArray) {
        // The ArrayList of walls
        ArrayList<Wall> walls = new ArrayList<>();
        // Add horizontal walls
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns - 1; j++) {
                int x = (int) (j * width);
                int y = (int) (i * height);
                switch (gridArray[i][j]) {
                    case ' ':
                        emptyPos.add(new int[] {x + 5, y + 5});
                        break;
                    case '*':
                        startX = x + 5;
                        startY = y + 5;
                        break;
                    case '!':
                        lessonItem = new LessonItem(x + 5, y + 5, game);
                        break;
                    case '?':
                        questionItem = new QuestionItem(x + 5, y + 5, game);
                        break;
                    case 'n':
                        numberEntities.add(new NumberEntity(x + 5, y + 5).randomize(1, 9));
                        break;
                    case '-':
                    case '|':
                        try {
                            if (isWall(gridArray[i][j + 1])) {
                                walls.add(new Wall(x, y, (int) width + 10, 10, color));
                            }
                        } catch (Exception e) {
                            // Ignore
                        }
                        break;
                    default:
                        // Ignore
                }

            }
        }
        // Add vertical walls
        for (int i = 0; i < rows - 1; i++) {
            for (int j = 0; j < columns; j++) {
                try {
                    if (isWall(gridArray[i][j]) && isWall(gridArray[i + 1][j])) {
                        int x = (int)(j * width);
                        int y = (int)(i * height);
                        walls.add(new Wall(x, y, 10, (int)height + 10, color));
                    }
                } catch (Exception e) {
                    // Ignore
                    System.out.println( e + " Problem at i = " + i + " and j = " + j);
                }
            }

        }
        this.walls = walls;
        Collections.shuffle(emptyPos);
    }

}
