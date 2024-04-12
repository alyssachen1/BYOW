package core;
import core.World;
import tileengine.TERenderer;

public class Main {
    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        World world = new World(12345);
        world.runGame();

    }
}
