package core;
import edu.princeton.cs.algs4.StdDraw;
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

    Random random = new Random();

    World world;



    public Room(World world) {
        // make the shape
        this.world = world;
    }

    public void generateRoom() {
        // minimum dimensions : 4x4?
        // max dimension : 10x10?
        // also add minimum spacing parameter between rooms (?)
        double width = RandomUtils.uniform(random, minWidth, maxWidth);
        double height = RandomUtils.uniform(random, minHeight, maxHeight);
        double spacing = RandomUtils.uniform(random, minSpacing, maxSpacing);

        fillFloor(width, height);
        wrapWall(width, height);


        roomShape(random.nextInt(), random.nextInt(), width, height);

    }

    private void fillFloor(int width, int height) {

    }

    private void wrapWall(int width, int height) {

    }


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

    private boolean overlap() { //implement
        return false;
    }

}
