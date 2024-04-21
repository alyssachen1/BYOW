package core;

import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;
import java.util.ArrayList;
import edu.princeton.cs.algs4.StdDraw;

import java.util.Random;
import utils.RandomUtils;
import utils.*;

public class World {

    public static final int DEFAULT_WIDTH = 80;
    public static final int DEFAULT_HEIGHT = 40;
    private static final String SAVE_FILE = "src/save.txt";
    private TERenderer ter;
    private Random random;
    TETile[][] currentState = new TETile[DEFAULT_WIDTH][DEFAULT_HEIGHT];
    private int numRooms;
    private Avatar avatar;

    private ArrayList<Room> rooms;
    private static final int MIN_ROOMS = 10;
    private static final int MAX_ROOMS = 14;

    public String seed;

    public World() {
        ter = new TERenderer();
        this.random = new Random(convertString(seed));

    }

    public World(String seed) {
        ter = new TERenderer();
        this.random = new Random(convertString(seed));
        this.rooms = new ArrayList<>();
        this.seed = seed;
        fillWithNothing(); //just added this
        //        fillWorld(currentState); //change this to fill with rooms or hallways, etc.
        generateRooms();
        generateHallways();
        Room startRoom = rooms.get(0);
        this.avatar = new Avatar(currentState, startRoom);
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

    public void fillWithNothing() {
        for (int x = 0; x < DEFAULT_WIDTH; x++) {
            for (int y = 0; y < DEFAULT_HEIGHT; y++) {
                currentState[x][y] = Tileset.NOTHING;
            }
        }
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

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char nextKey = StdDraw.nextKeyTyped();
                avatar.updateBoard(nextKey);
            }

            double mouseX = StdDraw.mouseX();
            double mouseY = StdDraw.mouseY();
            int x = (int) mouseX;
            int y = (int) mouseY;

            if (x >= 0 && x < DEFAULT_WIDTH && y >= 0 && y < DEFAULT_HEIGHT) {
                TETile mouseTile = currentState[x][y];
                hud(mouseTile);
            }


            ter.renderFrame(currentState);
        }

    }

    private void hud(TETile tile) {
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.textLeft(1, 39, "Tile: " + tile.description());
        StdDraw.show();
    }

    public void saveGame() {
        StringBuilder sb = new StringBuilder();
        sb.append(seed).append("\n");
        for (int y = 0; y < DEFAULT_HEIGHT; y++) {
            for (int x = 0; x < DEFAULT_WIDTH; x++) {
                sb.append(currentState[x][y].character());
            }
            sb.append("\n");
        }
        FileUtils.writeFile(SAVE_FILE, sb.toString());
                if (currentState[x][y] == Tileset.NOTHING) {
                    sb.append("0");
                } else if (currentState[x][y] == Tileset.FLOOR) {
                    sb.append("1");
                } else if (currentState[x][y] == Tileset.WALL) {
                    sb.append("2");
                } else if (currentState[x][y] == Tileset.AVATAR) {
                    sb.append("3");
                }
                sb.append("\n");
            }
            FileUtils.writeFile(SAVE_FILE, sb.toString());
        }
    }

    public void loadGame() {
//        if (FileUtils.fileExists(SAVE_FILE)) {
//            String data = FileUtils.readFile(SAVE_FILE);
//            String[] lines = data.split("\n");
//            seed = lines[0]; // Assuming the seed is stored in the first line
//            random = new Random(Long.parseLong(seed));
//            currentState = new TETile[DEFAULT_WIDTH][DEFAULT_HEIGHT];
//            fillWithNothing();
//
//            for (int y = 1; y < lines.length; y++) {
//                String[] tiles = lines[y].trim().split(" ");
//                for (int x = 0; x < DEFAULT_WIDTH && x < tiles.length; x++) {
//                    currentState[x][y - 1] = Tileset.getTile(Integer.parseInt(tiles[x]));
//                }
//            }
//            runGame();
//        }
        try {
            String fileContents = FileUtils.readFile(SAVE_FILE);


            String[] lines = fileContents.split("\n");
            int height = DEFAULT_HEIGHT;
            int width = DEFAULT_WIDTH;

            TETile[][] board = new TETile[width][height];
            fillWithNothing();

            for (int y = 0; y < height; y++) {
                String row = lines[y + 1];
                for (int x = 0; x < width; x++) {
                    char tileChar = row.charAt(x);
                    if (tileChar == '0') {
                        board[x][height - y - 1] = Tileset.NOTHING;
                    }
                    else if (tileChar == '1') {
                        board[x][height - y - 1] = Tileset.FLOOR;
                    }
                    else if (tileChar == '2') {
                        board[x][height - y - 1] = Tileset.WALL;
                    }
                    else if (tileChar == '3') {
                        board[x][height - y - 1] = Tileset.AVATAR;
                    } else {
                        throw new IllegalArgumentException("Invalid tile character in file: " + tileChar);
                    }
                }
            }
            this.currentState = board;
            ter.renderFrame(currentState);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}

