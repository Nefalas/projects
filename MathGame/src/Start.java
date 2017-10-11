import base_classes.Game;
import levels.LevelOne;
import levels.LevelTwo;
import levels.Menu;

/**
 * Class Start
 * This is the main class where the methods are called
 */
public class Start {

    /**
     * The Start function
     * @param args Start function arguments
     */
    public static void main(String args[]) {
        // Creating the game
        Game game = new Game();

        // Adding the levels
        game.addLevel(new Menu(game));
        game.addLevel(new LevelOne(game));
        game.addLevel(new LevelTwo(game));

        // Starting the game
        game.gameLoop();
    }
}

