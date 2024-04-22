package core;

import tileengine.TETile;
import tileengine.Tileset;
//import core.World;
//import utils.FileUtils;

public class AutograderBuddy {

    /**
     * Simulates a game, but doesn't render anything or call any StdDraw
     * methods. Instead, returns the world that would result if the input string
     * had been typed on the keyboard.
     *
     * Recall that strings ending in ":q" should cause the game to quit and
     * save. To "quit" in this method, save the game to a file, then just return
     * the TETile[][]. Do not call System.exit(0) in this method.
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public static TETile[][] getWorldFromInput(String input) {
        // N#####S
        // N12345SW:Q
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input string is null or empty.");
        }
        // Case insensitivity
        String upperInput = input.toUpperCase();

        if (input.charAt(0) != 'L') {
            int startIndex = upperInput.indexOf('N') + 1;
            int endIndex = upperInput.indexOf('S', startIndex);

            String restInput = upperInput.substring(endIndex + 1);
            String seedString = upperInput.substring(startIndex, endIndex);
            World world = new World(seedString);
            world.runGameFromInput(restInput);
            return world.currentState;
        } else {
            String restInput = upperInput.substring(1);
            World world = new World();
            world.loadGamee(restInput);
            return world.currentState;
        }
    }




    public static void main(String[] args) {
        TETile[][] worldTiles = getWorldFromInput("N123456SASD:Q");
    }


    /**
     * Used to tell the autograder which tiles are the floor/ground (including
     * any lights/items resting on the ground). Change this
     * method if you add additional tiles.
     */
    public static boolean isGroundTile(TETile t) {
        return t.character() == Tileset.FLOOR.character()
                || t.character() == Tileset.AVATAR.character()
                || t.character() == Tileset.FLOWER.character();
    }

    /**
     * Used to tell the autograder while tiles are the walls/boundaries. Change
     * this method if you add additional tiles.
     */
    public static boolean isBoundaryTile(TETile t) {
        return t.character() == Tileset.WALL.character()
                || t.character() == Tileset.LOCKED_DOOR.character()
                || t.character() == Tileset.UNLOCKED_DOOR.character();
    }
}
