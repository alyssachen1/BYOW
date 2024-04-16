package core;
import edu.princeton.cs.algs4.StdDraw;
import utils.RandomUtils;
import utils.FileUtils;
import java.util.*;
import core.World;
import tileengine.TETile;
import tileengine.TERenderer;
import tileengine.Tileset;

public class Room {
    private static final int minWidth = 5;
    private static final int minHeight = 5;
    private static final int maxWidth = 10;
    private static final int maxHeight = 10;


    public int startX;
    public int startY;
    public int width;
    public int height;

    Random random = new Random();

    private TETile[][] world;

    public Room(TETile[][] world, Random random) {
        this.world = world;
        // make the shape
        this.width = RandomUtils.uniform(random, minWidth, maxWidth);
        this.height = RandomUtils.uniform(random, minHeight, maxHeight);
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
                if ((x == startX - 1 || x == startX + width - 1 || y == startY - 1 || y == startY + height- 1) ) {
                    world[x][y] = Tileset.WALL;
                }
            }
        }
    }




//    private void roomShape(double x, double y, double width, double height) {
//        int methodNumber = random.nextInt(3) + 1; // Generates 1, 2, or 3
//
//        switch (methodNumber) {
//            case 1:
//                StdDraw.filledSquare(x, y, width / 2);
//                break;
//            case 2:
//                StdDraw.filledRectangle(x, y, width / 2, height / 2);
//                break;
//            case 3:
//                StdDraw.filledCircle(x, y, height / 2);
//                break;
//            default:
//                System.out.println("Invalid method number");
//        }
//    }

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



