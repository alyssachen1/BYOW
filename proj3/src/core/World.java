package core;

import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;
import java.util.ArrayList;

import java.util.Random;
import utils.RandomUtils;

public class World {

    public static final int DEFAULT_WIDTH = 80;
    public static final int DEFAULT_HEIGHT = 40;
    private static final String SAVE_FILE = "src/save.txt";
    private TERenderer ter;
    private Random random;
    TETile[][] currentState = new TETile[DEFAULT_WIDTH][DEFAULT_HEIGHT];
    private int numRooms;

    private ArrayList<Room> rooms;
    private static final int MIN_ROOMS = 10;
    private static final int MAX_ROOMS = 14;

    public World(String seed) {
        ter = new TERenderer();
        this.random = new Random(convertString(seed));
        this.rooms = new ArrayList<>();
        fillWithNothing(); //just added this
        //        fillWorld(currentState); //change this to fill with rooms or hallways, etc.
        generateRooms();
        generateHallways();
        Room startRoom = rooms.get(0);
        Avatar avatar = new Avatar(currentState, startRoom);
    }



    private void generateRooms() {
        // minimum dimensions : 4x4?
        // max dimension : 10x10?
        // also add minimum spacing parameter between rooms (?)

        numRooms = RandomUtils.uniform(random, MIN_ROOMS, MAX_ROOMS);
        int attempts = 0;
        while (attempts < numRooms) {
            Room room = new Room(currentState, random);
            if (room.isValidLocation()) {
                room.placeRoom();
                rooms.add(room);
                attempts++;
            }
        }
    }

    private void generateHallways() {
        for (int i = 0; i < rooms.size() - 1; i++) {
            Room room1 = rooms.get(i);
            Room room2 = rooms.get(i + 1);
            new Hallway(currentState, room1, room2, random);
        }
    }
    // use Prim's algorithm to select hallways to connect two rooms


    //helper method for generateRoom


    //generate hallway that can connect to one room to another or only to one room
    //    private void generateHallway(Room room, Room room) {
    //    }


    //check if there is enough space to generate one more room


    public void fillWithNothing() {
        for (int x = 0; x < DEFAULT_WIDTH; x++) {
            for (int y = 0; y < DEFAULT_HEIGHT; y++) {
                currentState[x][y] = Tileset.NOTHING;
            }
        }
    }

    private TETile randomTile() {
        int tileNum = random.nextInt(2);
        return switch (tileNum) {
            case 0 -> Tileset.CELL;
            default -> Tileset.NOTHING;
        };
    }

    private long convertString(String string) {
        StringBuilder seedBuilder = new StringBuilder();
        for (char ch : string.toCharArray()) {
            if (Character.isDigit(ch)) {
                seedBuilder.append(ch);
            }
        }
        return Long.parseLong(seedBuilder.toString());
    }

    public void runGame() {
        ter.initialize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        ter.renderFrame(currentState);
    }

}


