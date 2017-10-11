package levels;

import base_classes.Entity;
import base_classes.Game;
import base_classes.Level;
import helpers.Helpers;

import java.awt.*;

/**
 * Class levels.Menu - Extends base_classes.Level
 * A class for creating the menu
 */
public class Menu extends Level {

    // Font for titles
    final private Font TITLE_FONT = new Font("Lucida Blackletter", Font.BOLD, 28);
    // Font for basic text
    final private Font BASE_FONT = new Font("Lucida Blackletter", Font.BOLD, 20);
    // Font for character
    final private Font CHAR_FONT = new Font("Lucida Blackletter", Font.BOLD, 30);

    // Current character
    private int currentCharacter = 0;
    // All characters
    private String[] characters = {"Smiley", "Darth Vader", "Robert"};

    Entity zArrow = new Entity("zArrow.png", 350, 444, 64, 64);
    Entity xArrow = new Entity("xArrow.png", 674, 444, 64, 64);

    /**
     * Default constructor of the levels.Menu class
     * @param game the game to add the levels.Menu to
     */
    public Menu(Game game) {
        super(game, 200, 300);
    }

    /**
     * Draw the levels.Menu
     * @param g the game graphics
     */
    public void draw(Graphics g) {
        // Draw border
        g.setColor(Color.black);
        g.fillRect(100, 100, 824, 568);
        // Draw inner rectangle
        g.setColor(Color.white);
        g.fillRect(105, 105, 814, 558);
        // Draw title rectangle
        g.setColor(new Color(154, 246, 255));
        g.fillRect(120, 120, 784, 70);
        // Draw title
        g.setColor(Color.black);
        Helpers.drawCenteredString(120, 120, 784, 70, g, "Menu", TITLE_FONT);
        // Draw content rectangle
        g.setColor(new Color(212, 255, 207));
        g.fillRect(120, 205, 785, 443);
        // Draw content
        g.setColor(Color.black);
        Helpers.drawCenteredString(135, 215, 755, 40, g, "Welcome to the MathGame!", BASE_FONT);
        Helpers.drawCenteredString(135, 255, 755, 40, g, "Use WASD or the arrows to move around", BASE_FONT);
        Helpers.drawCenteredString(135, 285, 755, 40, g, "Use B to show your backpack", BASE_FONT);
        Helpers.drawCenteredString(135, 315, 755, 40, g, "Use G to show the grid", BASE_FONT);
        g.setColor(Color.blue);
        Helpers.drawCenteredString(135, 360, 755, 40, g, "Select your character", BASE_FONT);
        Helpers.drawCenteredString(135, 420, 755, 50, g, characters[currentCharacter], CHAR_FONT);
        zArrow.draw(g);
        xArrow.draw(g);
        g.setColor(Color.black);
        Helpers.drawCenteredString(135, 550, 755, 50, g, "Press ENTER", CHAR_FONT);
    }

    /**
     * Increment the current character number
     */
    public void incrementCurrentCharacter() {
        if (currentCharacter < characters.length - 1) {
            currentCharacter++;
        } else {
            currentCharacter = 0;
        }
        updateCharacter();
    }

    /**
     * Decrement the current character number
     */
    public void decrementCurrentCharacter() {
        if (currentCharacter > 0) {
            currentCharacter--;
        } else {
            currentCharacter = characters.length - 1;
        }
        updateCharacter();
    }

    /**
     * Update the character base_classes.Sprite
     */
    private void updateCharacter() {
        game.getPlayer().setRef(characters[currentCharacter].replaceAll(" ", "").toLowerCase() + ".png");
    }

}
