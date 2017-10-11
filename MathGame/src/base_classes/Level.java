package base_classes;

import extended_classes.LessonItem;
import extended_classes.PlayerEntity;
import extended_classes.QuestionItem;
import helpers.Helpers;

import java.awt.*;
import java.util.ArrayList;

/**
 * Class base_classes.Level
 * A class for creating levels for the game
 */
public class Level {

    // The game to add the level to
    public Game game;

    // Start position of the player
    public int startPosX, startPosY;

    // Walls of the level
    private ArrayList<Wall> walls = new ArrayList<>();
    // GridWalls of the level
    private ArrayList<GridLayout> gridLayouts = new ArrayList<>();
    // base_classes.Game items of the level
    private ArrayList<GameItem> items = new ArrayList<>();
    // Entities of the level
    private ArrayList<Entity> entities = new ArrayList<>();

    // Lesson item of the level
    private LessonItem lessonItem = null;
    // Question item of the level
    private QuestionItem questionItem = null;

    // base_classes.Level number
    private int levelNumber = 0;

    // Has passed the level
    private boolean levelPass = false;

    // Dimensions of the game
    public int maxWidth;
    public int maxHeight;

    /**
     * Default constructor for the base_classes.Level Object
     * @param game the game to add the level to
     * @param startPosX the player X start position
     * @param startPosY the player Y start position
     */
    public Level(Game game, int startPosX, int startPosY) {
        this.game = game;
        maxWidth = game.getWidth();
        maxHeight = game.getHeight();
        this.startPosX = startPosX;
        this.startPosY = startPosY;
    }

    public Level(Game game) {
        this(game, 0, 0);
    }

    /**
     * Set the level number
     * @param levelNumber the level number
     */
    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    /**
     * Draw the level and all its components
     * @param g the game graphics
     */
    public void draw(Graphics g) {
        if (game.showGrid()) {
            try {
                for (GridLayout gridWall : gridLayouts) {
                    gridWall.drawGrid((Graphics2D) g);
                }
            } catch (Exception ex) {
                // Ignore
            }
        }
        if (lessonItem != null) {
            lessonItem.draw(g);
        }
        if (questionItem != null) {
            questionItem.draw(g);
        }
        for (Entity entity : entities) {
            entity.draw(g);
        }
        for (Wall wall : walls) {
            wall.draw(g);
        }
    }

    /**
     * Draw the game items info boxes if the player is close
     * @param player the player entity
     * @param g the game graphics
     */
    public void drawInfo(PlayerEntity player, Graphics g) {
        for (GameItem item : items) {
            if (item.playerIsClose(player.getXPos(), player.getYPos())) {
                item.drawItemInfo(g);
            }
        }
    }

    /**
     * Set the levelPass variable to true and empty the backpack
     */
    public void passLevel() {
        levelPass = true;
        game.getPlayer().emptyBackpack();
    }

    /**
     * Get the state of the levelPass variable
     * @return
     */
    public boolean getLevelPass() {
        return levelPass;
    }

    /**
     * Draw the level pass message
     * @param g the game graphics
     */
    public void drawPassLevel(Graphics g) {
        if (levelPass) {
            g.setColor(Color.green);
            g.fillRect(game.getWidth() / 4, game.getHeight() / 4, game.getWidth() / 2, game.getHeight() / 2);
            g.setColor(new Color(255, 249, 168));
            g.fillRect(game.getWidth() / 4 + 10, game.getHeight() / 4 + 10, game.getWidth() / 2 - 20, game.getHeight() / 2 - 20);
            g.setColor(Color.blue);
            Helpers.drawCenteredString(game.getWidth() / 4, game.getHeight() / 4, game.getWidth() / 2, game.getHeight() / 4, g, "Congratulations!", new Font("Lucida Blackletter", Font.BOLD, 28));
            Helpers.drawCenteredString(game.getWidth() / 4, game.getHeight() / 4 + 50, game.getWidth() / 2, game.getHeight() / 4, g, "You passed level " + levelNumber, new Font("Lucida Blackletter", Font.BOLD, 28));
            Helpers.drawCenteredString(game.getWidth() / 4, game.getHeight() / 2, game.getWidth() / 2, game.getHeight() / 4, g, "Press any key", new Font("Lucida Blackletter", Font.BOLD, 24));
        }
    }

    /**
     * Pick up an entity if the player is close
     * @param player the player entity
     */
    public void pickUp(PlayerEntity player) {
        for (Entity entity : entities) {
            if (entity.playerIsClose(player.getXPos(), player.getYPos()) && player.getBackpack().size() <= 45) {
                player.addToBackpack(entity);
                removeEntity(entity.getId());
                break;
            }
        }
    }

    /**
     * Add action to clicks on action boxes
     * @param mouseX the mouse X position
     * @param mouseY the mouse Y position
     */
    public void actionBoxClick(int mouseX, int mouseY) {
        if (!(questionItem == null) && questionItem.getActionBoxes().size() > 0 && questionItem.playerIsClose(game.getPlayer().getXPos(), game.getPlayer().getYPos())) {
            for (ActionBox actionBox : questionItem.getActionBoxes()) {
                if (actionBox.clicked(mouseX, mouseY)) {
                    if (!actionBox.hasEntity()) {
                        actionBox.setWaitingForItem(true);
                        game.setShowBackpack(true);
                        break;
                    } else {
                        game.getPlayer().addToBackpack(actionBox.getEntity());
                        actionBox.removeEntity();
                        actionBox.setWaitingForItem(true);
                        game.setShowBackpack(true);
                    }
                }
            }
        }
        // Place item in action if click on item in backpack
        try {
            for (ActionBox actionBox : getQuestionItem().getActionBoxes()) {
                if (actionBox.isWaitingForItem()) {
                    if (!(game.getPlayer().getBackpackItem(mouseX, mouseY) == null)) {
                        Entity item = game.getPlayer().getBackpackItem(mouseX, mouseY);
                        game.getPlayer().removeFromBackpack(item.getId());
                        actionBox.setEntity(item);
                        game.setShowBackpack(false);
                        actionBox.setWaitingForItem(false);
                        passLevel();
                    }
                }
            }
        } catch (Exception ex) {
            // Ignore
        }
    }

    /**
     * Add a base_classes.Wall to the level
     * @param wall the wall to add
     */
    public void addWall(Wall wall) {
        walls.add(wall);
    }

    /**
     * Add a base_classes.GridLayout to the level
     * @param gridLayout the base_classes.GridLayout to add
     */
    public void addGridLayout(GridLayout gridLayout) {
        gridLayouts.add(gridLayout);
        if (gridLayout.getStartX() != 0 && gridLayout.getStartY() != 0) {
            startPosX = gridLayout.getStartX();
            startPosY = gridLayout.getStartY();
        }
        if (gridLayout.getLessonItem() != null) {
            setLessonItem(gridLayout.getLessonItem());
        }
        if (gridLayout.getQuestionItem() != null) {
            setQuestionItem(gridLayout.getQuestionItem());
        }
        entities.addAll(gridLayout.getNumberEntities());
        walls.addAll(gridLayout.getWalls());
    }

    /**
     * Set the extended_classes.LessonItem of the level
     * @param lessonItem the extended_classes.LessonItem
     */
    public void setLessonItem(LessonItem lessonItem) {
        this.lessonItem = lessonItem;
        addGameItem(lessonItem);
    }

    /**
     * Set the extended_classes.QuestionItem for the level
     * @param questionItem the extended_classes.QuestionItem
     */
    public void setQuestionItem(QuestionItem questionItem) {
        this.questionItem = questionItem;
        addGameItem(questionItem);
    }

    /**
     * Get the extended_classes.QuestionItem of the level
     * @return the extended_classes.QuestionItem
     */
    public QuestionItem getQuestionItem() {
        return questionItem;
    }

    /**
     * Add a base_classes.GameItem to the level
     * @param item the base_classes.GameItem
     */
    private void addGameItem(GameItem item) {
        items.add(item);
    }

    /**
     * Add an entity to the level
     * @param entity the entity
     */
    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    /**
     * Remove an entity from the level
     * @param id the ID of the entity
     */
    public void removeEntity(String id) {
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).getId().equals(id)) {
                entities.remove(i);
                break;
            }
        }
    }

    /**
     * Get the Walls of the level
     * @return the Walls
     */
    public ArrayList<Wall> getWalls() {
        return walls;
    }

    /**
     * Get the number of the level
     * @return the level number
     */
    public int getLevelNumber() {
        return levelNumber;
    }
}
