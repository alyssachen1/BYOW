import core.AutograderBuddy;
import core.Room;
import core.World;
import edu.princeton.cs.algs4.StdDraw;
import org.junit.jupiter.api.Test;
import tileengine.TERenderer;
import tileengine.TETile;

public class WorldGenTests {
    @Test
    public void basicTest() {
        // put different seeds here to test different worlds
        TETile[][] tiles = AutograderBuddy.getWorldFromInput("N1234567890123456789S");

        TERenderer ter = new TERenderer();
        ter.initialize(tiles.length, tiles[0].length);
        ter.renderFrame(tiles);
//        StdDraw.pause(5000); // pause for 5 seconds so you can see the output
    }

    @Test
    public void basicInteractivityTest() {
        // TODO: write a test that uses an input like "n123swasdwasd"
        TETile[][] tiles = AutograderBuddy.getWorldFromInput("N12359572S");
        TERenderer ter = new TERenderer();
        ter.initialize(tiles.length, tiles[0].length);
        ter.renderFrame(tiles);
//        StdDraw.pause(5000);
    }

    @Test
    public void basicSaveTest() {
        // TODO: write a test that calls getWorldFromInput twice, with "n123swasd:q" and with "lwasd"
        TETile[][] tiles1 = AutograderBuddy.getWorldFromInput("n12345swasd:q");
        TERenderer ter = new TERenderer();
        ter.initialize(tiles1.length, tiles1[0].length);
        ter.renderFrame(tiles1);
        StdDraw.pause(1000);

        TETile[][] tiles2 = AutograderBuddy.getWorldFromInput("lww:q");
        TERenderer ter2 = new TERenderer();
        ter2.initialize(tiles2.length, tiles2[0].length);
        ter2.renderFrame(tiles2);
        StdDraw.pause(1000);
    }
}
