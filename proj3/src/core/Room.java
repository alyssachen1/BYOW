package core;
//import edu.princeton.cs.algs4.StdDraw;
import utils.RandomUtils;
import java.util.*;
import tileengine.TETile;
import tileengine.Tileset;

public class Room {
    private static final int MINWIDTH = 5;
    private static final int MINHEIGHT = 5;
    private static final int MAXWIDTH = 10;
    private static final int MAXHEIGHT = 10;
    int startY;
    int startX;
    int width;
    int height;

    Random random = new Random();

    private TETile[][] world;

    public Room(TETile[][] world, Random random) {
        this.world = world;
        // make the shape
        this.width = RandomUtils.uniform(random, MINWIDTH, MAXWIDTH);
        this.height = RandomUtils.uniform(random, MINHEIGHT, MAXHEIGHT);
        this.startX = RandomUtils.uniform(random, 1, world.length - width - 1);
        this.startY = RandomUtils.uniform(random, 1, world[0].length - height - 1);

    }

    public void placeRoom() {
        fillFloor();
        wrapWall();
    }

    private void fillFloor() {
        for (int x = startX; x < startX + width; x++) {
            for (int y = startY; y < startY + height; y++) {
                world[x][y] = Tileset.FLOOR;
            }
        }
    }

    private void wrapWall() {
        for (int x = startX - 1; x < startX + width; x++) {
            for (int y = startY - 1; y < startY + height; y++) {
                if ((x == startX - 1 || x == startX + width - 1 || y == startY - 1 || y == startY + height - 1)) {
                    world[x][y] = Tileset.PURPLE_WALL;
                }
            }
        }
    }

    public boolean isValidLocation() {
        for (int x = startX - 1; x <= startX + width + 1; x++) {
            for (int y = startY - 1; y <= startY + height + 1; y++) {
                if (world[x][y] != Tileset.NOTHING) {
                    return false;
                }
            }
        }
        return true;
    }
}



