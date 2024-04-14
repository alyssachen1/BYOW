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
    private static final int minWidth = 4;
    private static final int minHeight = 4;
    private static final int maxWidth = 10;
    private static final int maxHeight = 10;
    private static final int minSpacing = 5;
    private static final int maxSpacing = 8;

    private int startX;
    private int startY;
    private int width;
    private int height;

    private TERenderer ter;

    Random random = new Random();

    private TETile[][] world;



    public Room(TETile[][] world, Random random) {
        // make the shape
        this.world = world;
        ter = new TERenderer();
        generateRoom();
    }

    public void generateRoom() {
        // minimum dimensions : 4x4?
        // max dimension : 10x10?
        // also add minimum spacing parameter between rooms (?)
        this.width = RandomUtils.uniform(random, minWidth, maxWidth);
        this.height = RandomUtils.uniform(random, minHeight, maxHeight);
        int spacing = RandomUtils.uniform(random, minSpacing, maxSpacing);


        startX = RandomUtils.uniform(random, 0, world.length - width);
        startY = RandomUtils.uniform(random, 0, world[0].length - width);

        fillFloor();
//        wrapWall();

        roomShape(random.nextInt(), random.nextInt(), width, height);

    }

    private void fillFloor() {
        for (int x = startX; x < startX + width; x++) {
            for (int y = startY; y < startY + height; y++){
                world[x][y] = Tileset.FLOOR;
            }
        }
    }

//    private void wrapWall() {
//        for (int x = x - 1; x <= startX + width; x++){
//            for (int x )
//        }
//    }


    private boolean isSpace() {
        return true;
    }

    private void roomShape(double x, double y, double width, double height) {
        int methodNumber = random.nextInt(3) + 1; // Generates 1, 2, or 3

        switch (methodNumber) {
            case 1:
                StdDraw.filledSquare(x, y, width / 2);
                break;
            case 2:
                StdDraw.filledRectangle(x, y, width / 2, height / 2);
                break;
            case 3:
                StdDraw.filledCircle(x, y, height / 2);
                break;
            default:
                System.out.println("Invalid method number");
        }
    }

    private boolean isValidLocation() {
        for (int x = startX; x < startX + width; x++) {
            for (int y = startY; y < startY + height; y++) {
                if (world[x][y] != Tileset.NOTHING) {
                    return false;
                }
            }
        }
        return true;
    }


}