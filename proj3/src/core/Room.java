package core;
import edu.princeton.cs.algs4.StdDraw;
import utils.RandomUtils;
import utils.FileUtils;
import java.util.*;

public class Room {
    private static final double minWidth = 4.0;

    private static final double minHeight = 4.0;

    private static final double maxWidth = 10.0;

    private static final double maxHeight = 10.0;

    private static final double minSpacing = 5.0;

    private static final double maxSpacing = 8.0;

    Random random = new Random();

    World world;


    public Room(World world) {
        // make the shape
        this.world = world;
    }

    public void generateRoom() {
        double width = RandomUtils.uniform(random, minWidth, maxWidth);
        double height = RandomUtils.uniform(random, minHeight, maxHeight);
        double spacing = RandomUtils.uniform(random, minSpacing, maxSpacing);
        roomShape(random.nextInt(world.DEFAULT_WIDTH), world.DEFAULT_HEIGHT, width, height);
    }

    private void fillFloor() {

    }

    private void wrapWall() {

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
