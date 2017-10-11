package base_classes;

import extended_classes.PlayerEntity;

import levels.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;


/**
 * Class base_classes.Game - Extends Canvas
 * This is the entry point of the Math base_classes.Game. It creates a canvas, listens for input,
 * initializes the moving entities and starts the game loop
 *
 * @author Alexis Lindgren
 *
 * @version 1.0
 */

public class Game extends Canvas {

    // base_classes.Game graphics
    Graphics2D g;
    // For accelerated page flipping
    private BufferStrategy strategy;

    // Control game execution
    private boolean running = true;

    // base_classes.Entity movement speed
    private final double MOVE_SPEED = 300;
    // Width
    public final int WINDOW_WIDTH = 1024;
    // Height
    public final int WINDOW_HEIGHT = 768;
    // Background color
    public final Color BACKGROUND_COLOR = Color.lightGray;

    // Current level being played
    private int currentLevelNumber = 0;
    // Levels of the game
    private ArrayList<Level> levels = new ArrayList<>();
    // levels.Menu of the game
    private Menu menu = null;

    // Left key pressed (A or LEFT)
    private boolean leftPressed = false;
    // Right key pressed (D or RIGHT)
    private boolean rightPressed = false;
    // Up key pressed (W or UP)
    private boolean upPressed = false;
    // Down key pressed (S or DOWN)
    private boolean downPressed = false;

    // Visibility state of the backpack
    private boolean showBackpack = false;
    // Visibility state of the grid
    private boolean showGrid = false;

    // Moving entities of the game
    private ArrayList<Entity> movingEntities = new ArrayList<>();
    // Player entity
    private PlayerEntity player;

    /**
     * This is the default constructor for the base_classes.Game class.
     * It creates the frame, the canvas and the buffer strategy (for accelerated graphics with page flipping)
     */
    public Game() {
        // Frame containing the game
        JFrame container = new JFrame("Math Game");
        container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Get hold of the panel and set resolution
        JPanel panel = (JPanel) container.getContentPane();
        panel.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        panel.setLayout(null);

        // Set canvas size and add it to the frame
        setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        panel.add(this);

        // Ignoring repaint since it's done in accelerated mode
        setIgnoreRepaint(true);

        // Make the window visible
        container.setResizable(false);
        container.setVisible(true);
        container.pack();

        // Add key listeners
        addKeyListener(new KeyInputHandler());
        addMouseListener(new MouseInputHandler());

        // Get focus on the canvas for input
        requestFocus(true);

        // Create buffering strategy to manage accelerated graphics
        createBufferStrategy(3);
        strategy = getBufferStrategy();
    }

    /**
     * Starts the game loop. Draws everything on the canvas and performs actions on input
     */
    public void gameLoop() {

        // Initialize the entities
        initEntities();

        // Total frames
        long frames = 0;
        // Elapsed time since the start
        long elapsedTime = 0;
        // Last loop time of the game loop
        long lastLoopTime = System.currentTimeMillis();

        // While game is running
        while (running) {
            // Time since last loop time
            long delta = System.currentTimeMillis() - lastLoopTime;
            int FPS = (elapsedTime > 0)? (int)((frames*1000)/elapsedTime) : 0;

            if (elapsedTime >= 1000) {
                // System.out.println("FPS = " + FPS);
                elapsedTime = 0;
                frames = 0;
            }

            if (delta >= 1000/60) {
                // Reset the last loop time
                lastLoopTime = System.currentTimeMillis();

                elapsedTime += delta;

                // Get hold of the graphics
                g = (Graphics2D) strategy.getDrawGraphics();

                // Empty the window
                g.setColor(BACKGROUND_COLOR);
                g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

                // Draw the level
                getCurrentLevel().draw(g);

                // Move every moving entity
                for (Entity entity : movingEntities) {
                    entity.move(delta);
                }

                // Draw every entity
                for (Entity entity : movingEntities) {
                    entity.draw(g);
                }

                // Draw game item info if player is close
                getCurrentLevel().drawInfo(player, g);

                // Pick up item if player is close
                getCurrentLevel().pickUp(player);

                // Show backpack
                if (showBackpack) {
                    player.showBackpack(g);
                }

                // Draw level pass message if level is passed
                getCurrentLevel().drawPassLevel(g);

                // Clear up the graphics and flip over the buffer
                g.dispose();

                // Show flipped page
                strategy.show();

                // Set player speed to 0
                player.setHorizontalMovement(0);
                player.setVerticalMovement(0);

                // Going left
                if (leftPressed && !rightPressed) {
                    player.setHorizontalMovement(-MOVE_SPEED);
                    // Going right
                } else if (rightPressed && !leftPressed) {
                    player.setHorizontalMovement(+MOVE_SPEED);
                }
                // Going up
                if (upPressed && !downPressed) {
                    player.setVerticalMovement(-MOVE_SPEED);
                    // Going down
                } else if (downPressed && !upPressed) {
                    player.setVerticalMovement(MOVE_SPEED);
                }

                frames++;
            }
            // Pause for 2 milliseconds
            try {
                Thread.sleep(2);
            } catch (Exception ex) {
                // Ignore
            }
        }
    }

    /**
     * Initializes the moving entities and adds them to the game.
     */
    private void initEntities() {
        // Get player position from level or set to middle point
        int x = (levels.size() > 0)? getCurrentLevel().startPosX : WINDOW_WIDTH /2;
        int y = (levels.size() > 0)? getCurrentLevel().startPosY : WINDOW_HEIGHT /2;
        // Create player entity
        player = new PlayerEntity(this, "smiley.png", x, y);
        // Add player entity to moving entities
        movingEntities.add(player);
    }

    /**
     * Get the player entity
     * @return the player entity
     */
    public PlayerEntity getPlayer() {
        return player;
    }

    /**
     * Get the current level being played
     * @return the current level
     */
    public Level getCurrentLevel() {
        return levels.get(currentLevelNumber);
    }

    /**
     * Adds a level to the game
     * @param level the level to be added
     */
    public void addLevel(Level level) {
        levels.add(level);
        level.setLevelNumber(levels.size() - 1);
        if (levels.size() == 1) {
            menu = (Menu) level;
        }
    }

    /**
     * Show or hide the backpack
     * @param showBackpack the visibility of the backpack
     */
    public void setShowBackpack(boolean showBackpack) {
        this.showBackpack = showBackpack;
    }

    /**
     * Show or hide the grid
     * @param showGrid the visibility of the grid
     */
    public void setShowGrid(boolean showGrid) {
        this.showGrid = showGrid;
    }

    public boolean showGrid() {
        return showGrid;
    }

    /**
     * Class KeyInputHandler
     * This class extends the KeyAdapter class and adds action listeners to certain keys of the keyboard
     */
    private class KeyInputHandler extends KeyAdapter {

        /**
         * Actions listeners for a key press
         * @param e the key event
         */
        public void keyPressed(KeyEvent e) {
            // Moving left (A or left arrow)
            if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                leftPressed = true;
            }
            // Moving right(D or right arrow)
            if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
                rightPressed = true;
            }
            // Moving up (W or up arrow)
            if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
                upPressed = true;
            }
            // Moving down (S or down arrow)
            if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
                downPressed = true;
            }
            // Set waiting for item to false if player gets out of reach
            try {
                for (ActionBox actionBox : getCurrentLevel().getQuestionItem().getActionBoxes()) {
                    if (actionBox.isWaitingForItem()) {
                        if (!getCurrentLevel().getQuestionItem().playerIsClose(player.getXPos(), player.getYPos())) {
                            actionBox.setWaitingForItem(false);
                            showBackpack = false;
                        }
                    }
                }
            } catch (Exception ex) {
                // Ignore
            }
        }

        /**
         * Action listeners for a key release
         * @param e the key event
         */
        public void keyReleased(KeyEvent e) {
            // Moving left (A or left arrow)
            if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                leftPressed = false;
            }
            // Moving right(D or right arrow)
            if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
                rightPressed = false;
            }
            // Moving up (W or up arrow)
            if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
                upPressed = false;
            }
            // Moving down (S or down arrow)
            if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
                downPressed = false;
            }
        }

        /**
         * Action listeners for a key type
         * @param e the key event
         */
        public void keyTyped(KeyEvent e) {
            // Exit the game (esc)
            if (e.getKeyChar() == 27) {
                System.exit(0);
            }
            // levels.Menu specific
            if (currentLevelNumber == 0 && menu != null) {
                if (e.getKeyChar() == 'z' || e.getKeyChar() == 'Z') {
                    menu.decrementCurrentCharacter();
                }
                if (e.getKeyChar() == 'x' || e.getKeyChar() == 'X') {
                    menu.incrementCurrentCharacter();
                }
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    currentLevelNumber++;
                    player.setXPos(getCurrentLevel().startPosX);
                    player.setYPos(getCurrentLevel().startPosY);
                }
            }
            // Show backpack (B)
            if (e.getKeyChar() == 'b' || e.getKeyChar() == 'B') {
                showBackpack = !showBackpack;
                if (!showBackpack) {
                    try {
                        for (ActionBox actionBox : getCurrentLevel().getQuestionItem().getActionBoxes()) {
                            if (actionBox.isWaitingForItem()) {
                                actionBox.setWaitingForItem(false);
                            }
                        }
                    } catch (Exception ex) {
                        // Ignore
                    }
                }
            }
            // Show grid (G)
            if (e.getKeyChar() == 'g' || e.getKeyChar() == 'G') {
                showGrid = ! showGrid;
            }
            // Next level (L)
            if (e.getKeyChar() == 'l' || e.getKeyChar() == 'L' || getCurrentLevel().getLevelPass()) {
                if (currentLevelNumber + 1 < levels.size()) {
                    System.out.println("Next level");
                    currentLevelNumber++;
                    player.setXPos(getCurrentLevel().startPosX);
                    player.setYPos(getCurrentLevel().startPosY);
                } else {
                    //System.out.println("No more levels");
                }
            }
            // Previous level (P)
            if (e.getKeyChar() == 'p' || e.getKeyChar() == 'P') {
                if (currentLevelNumber > 0) {
                    currentLevelNumber--;
                    player.setXPos(getCurrentLevel().startPosX);
                    player.setYPos(getCurrentLevel().startPosY);
                } else {
                    //System.out.println("First level");
                }
            }
        }
    }

    /**
     * Class MouseInputHandler
     * This class extends MouseAdapter and listens for mouse events
     */
    private class MouseInputHandler extends MouseAdapter {

        /**
         * Listen for mouse click
         * @param e mouse event
         */
        public void mouseClicked(MouseEvent e) {
            //System.out.println("x = " + e.getX() + ", y = " + e.getY());
            // Check if clicked on action box
            getCurrentLevel().actionBoxClick(e.getX(), e.getY());
        }

    }

}
