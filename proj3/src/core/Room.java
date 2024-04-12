package core;
import utils.RandomUtils;
import utils.FileUtils;
import java.util.*;

public class Room {
    private static final int minWidth = 4;

    private static final int minHeight = 4;

    private static final int maxWidth = 10;

    private static final int maxHeight = 10;

    private static final int minSpacing = 5;

    private static final int maxSpacing = 8;


    public void generateRoom() {
        // minimum dimensions : 4x4?
        // max dimension : 10x10?
        // also add minimum spacing parameter between rooms (?)
        Random random = new Random();
        int width = random.nextInt(maxWidth - minWidth + 1) + minWidth;
        int height = random.nextInt(maxHeight - minHeight + 1) + minHeight;

    }

    private void fillFloor() {
    }

    private void wrapWall() {

    }

}
