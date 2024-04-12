package core;
import utils.RandomUtils;
import utils.FileUtils;
import java.util.*;
import core.World;
import tileengine.TETile;

public class Room {
    private static final double minWidth = 4.0;

    private static final double minHeight = 4.0;

    private static final double maxWidth = 10.0;

    private static final double maxHeight = 10.0;

    private static final double minSpacing = 5.0;

    private static final double maxSpacing = 8.0;

    public TETile[][] world;

    public Room() {

    }

    public void generateRoom() {
        // minimum dimensions : 4x4?
        // max dimension : 10x10?
        // also add minimum spacing parameter between rooms (?)
        Random random = new Random();
        double width = RandomUtils.uniform(random, minWidth, maxHeight);
        double height = RandomUtils.uniform(random, minHeight, maxHeight);
        double spacing = RandomUtils.uniform(random, minSpacing, maxSpacing);
        fillFloor(width, height);
        wrapWall(width, height);

    }

    private void fillFloor(int width, int height) {

    }

    private void wrapWall(int width, int height) {

    }

    private boolean isSpace() {
        return true;
    }
}
