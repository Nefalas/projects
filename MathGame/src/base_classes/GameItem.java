package base_classes;

import helpers.Helpers;

import java.awt.*;
import java.util.ArrayList;

/**
 * Class base_classes.GameItem
 * A class for creating stationary items that the player can interact width
 */
public class GameItem {

    // base_classes.Game item position
    private int x, y;
    // base_classes.Entity of the game item
    private Entity entity;
    // The game to add the game item to
    private Game game;

    // Font for titles
    final private Font TITLE_FONT = new Font("Lucida Blackletter", Font.BOLD, 28);
    // Font for basic text
    final private Font BASE_FONT = new Font("Lucida Blackletter", Font.BOLD, 14);

    // Name of the game item
    private String itemName;
    // Content of the left panel
    private String content = "";
    // Content of the right panel
    private String actionContent = "";

    // Interactive entities
    private ArrayList<Entity> actionEntities = new ArrayList<>();

    // Single strings
    private ArrayList<String[]> singleStrings = new ArrayList<>();

    /**
     * Default constructor for the base_classes.GameItem object
     * @param itemName the name of the base_classes.GameItem
     * @param x base_classes.GameItem X position
     * @param y base_classes.GameItem Y position
     * @param ref URL/filepath to the base_classes.GameItem image
     * @param width the width of the base_classes.GameItem image
     * @param height the height of the base_classes.GameItem image
     * @param game game to add the base_classes.GameItem to
     */
    public GameItem(String itemName, int x, int y, String ref, int width, int height, Game game) {
        this.itemName = itemName;
        this.x = x;
        this.y = y;
        entity = new Entity(ref, x, y, width, height);
        this.game = game;
    }

    /**
     * Set the content of the left panel
     * @param content the content of the left panel
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Set the content of the right panel
     * @param actionContent the content of the right panel
     */
    public void setActionContent(String actionContent) {
        this.actionContent = actionContent;
    }

    /**
     * Draw the base_classes.GameItem
     * @param g the game graphics
     */
    public void draw(Graphics g) {
        entity.draw(g);
    }

    /**
     * Draw the info box of the base_classes.GameItem
     * @param g the game graphics
     */
    public void drawItemInfo(Graphics g) {
        // Draw border
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
        Helpers.drawCenteredString(120, 120, 784, 70, g, itemName, TITLE_FONT);
        // Draw content rectangle
        g.setColor(new Color(212, 255, 207));
        g.fillRect(120, 205, 385, 443);
        // Draw example/task rectangle
        g.setColor(new Color(255, 226, 212));
        g.fillRect(520, 205, 385, 443);
        // Draw content
        g.setColor(Color.black);
        g.setFont(BASE_FONT);
        Helpers.drawStringWidth(g, content, 135, 220, 355);
        // Draw action content
        Helpers.drawStringWidth(g, actionContent, 535, 220, 355);
        g.setColor(Color.WHITE);
        for (Entity actionEntity : actionEntities) {
            g.fillRect(actionEntity.getXPos() - 35, actionEntity.getYPos() - 35, 70, 70);
            actionEntity.draw(g);
        }
        // Draw single strings
        for (String[] stringItem : singleStrings) {
            int R = Integer.parseInt(stringItem[4]);
            int G = Integer.parseInt(stringItem[5]);
            int B = Integer.parseInt(stringItem[6]);
            g.setColor(new Color(R, G, B));
            g.setFont(new Font("Lucida Blackletter", Font.BOLD, Integer.parseInt(stringItem[3])));
            int x = Integer.parseInt(stringItem[1]);
            int y = Integer.parseInt(stringItem[2]);
            g.drawString(stringItem[0], x, y);
        }
    }

    /**
     * Add an interactive entity to the base_classes.GameItem
     * @param actionEntity the interactive entity
     */
    public void addActionEntity(Entity actionEntity) {
        actionEntities.add(actionEntity);
    }

    /**
     * Add a single string to the base_classes.GameItem info box
     * @param text the single string text
     * @param x the X position
     * @param y the Y position
     * @param fontSize the font size
     * @param r the colorR
     * @param g the colorG
     * @param b the colorB
     */
    public void addSingleString(String text, int x, int y, int fontSize, int r, int g, int b) {
        singleStrings.add(new String[] {text, ""+x, ""+y, ""+fontSize, ""+r, ""+g, ""+b});
    }

    /**
     * Returns true if the player is closer than 70 pixels
     * @param playerX the player X position
     * @param playerY the player Y position
     * @return true if the player is close
     */
    public boolean playerIsClose(int playerX, int playerY) {
        return Math.sqrt(Math.pow(x - playerX, 2) + Math.pow(y - playerY, 2)) < 70;
    }

}
