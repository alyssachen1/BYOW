package core;

import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import edu.princeton.cs.algs4.StdDraw;

import java.util.Random;
import utils.RandomUtils;
import utils.*;

public class World {

    public static final int DEFAULT_WIDTH = 80;
    public static final int DEFAULT_HEIGHT = 40;
    private static final String SAVE_FILE = "save.txt";
    private TERenderer ter;
    private Random random;
    TETile[][] currentState = new TETile[DEFAULT_WIDTH][DEFAULT_HEIGHT];
    private boolean[][] visibility;
    private boolean applyLOS;
    private int numRooms;
    Avatar avatar;

    private ArrayList<Room> rooms;
    private static final int MIN_ROOMS = 10;
    private static final int MAX_ROOMS = 14;

    String seed;

    private boolean running = true;


    public World() {
        ter = new TERenderer();
        this.visibility = new boolean[DEFAULT_WIDTH][DEFAULT_HEIGHT];
    }

    public World(String seed) {
        ter = new TERenderer();
        this.applyLOS = false;
        this.visibility = new boolean[DEFAULT_WIDTH][DEFAULT_HEIGHT];
        this.random = new Random(convertString(seed));
        this.rooms = new ArrayList<>();
        this.seed = seed;
        fillWithNothing();
        generateRooms();
        generateHallways();
        Room startRoom = rooms.get(0);
        this.avatar = new Avatar(currentState, startRoom.startX, startRoom.startY);
    }

    private void generateRooms() {
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
                visibility[x][y] = false;
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
        StdDraw.enableDoubleBuffering();
        ter.initialize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        running = true;
        boolean prev = false;

        while (running) {
            //process input
            handleInput();

            //update state
            updateVisibility();
            //clear draw
            StdDraw.clear();
            //render all game elements
            ter.renderFrame(currentState, applyLOS, visibility);

            //draw hud
            double mouseX = StdDraw.mouseX();
            double mouseY = StdDraw.mouseY();
            int x = (int) mouseX;
            int y = (int) mouseY;

            if (x >= 0 && x < DEFAULT_WIDTH && y >= 0 && y < DEFAULT_HEIGHT) {
                TETile mouseTile = currentState[x][y];
                hud(mouseTile);
            }

            //show std draw
            StdDraw.show();


            //post frame processing
            if (StdDraw.isKeyPressed(KeyEvent.VK_SHIFT) && StdDraw.isKeyPressed(KeyEvent.VK_SEMICOLON)) {
                prev = true;
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_Q) && prev) {
                running = false;
                saveGame();
                System.exit(0);
            }
        }
    }

    private void handleInput() {
        if (StdDraw.hasNextKeyTyped()) {
            char nextKey = StdDraw.nextKeyTyped();
            if (nextKey == 'p' || nextKey == 'P') {
                applyLOS = !applyLOS;
            }
            avatar.updateBoard(nextKey);
        }
    }


    private void hud(TETile tile) {
        StdDraw.enableDoubleBuffering();
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.textLeft(1, 39, "Tile: " + tile.description());
        StdDraw.text(73, 39, "Press P to toggle light");
    }

    public void runGameFromInput(String input) {
        boolean prev = false;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ':') {
                prev = true;
            } else if (input.charAt(i) == 'Q' && prev) {
                saveGame();
                return;
            } else if (i == 0 && input.charAt(i) == 'Q') {
                return;
            } else if (input.charAt(i) == 'W') {
                avatar.move(0, 1);
            } else if (input.charAt(i) == 'A') {
                avatar.move(-1, 0);
            } else if (input.charAt(i) == 'S') {
                avatar.move(0, -1);
            } else if (input.charAt(i) == 'D') {
                avatar.move(1, 0);
            }
        }
    }


    public void saveGame() {
        StringBuilder sb = new StringBuilder();
        sb.append(seed).append("\n");
        for (int y = DEFAULT_HEIGHT - 1; y >= 0; y--) {
            for (int x = 0; x < DEFAULT_WIDTH; x++) {
                sb.append(currentState[x][y].character());
            }
            sb.append("\n");
        }
        FileUtils.writeFile(SAVE_FILE, sb.toString());
    }

    public void loadGame() {
        int startX = 0;
        int startY = 0;
        String fileContents = FileUtils.readFile(SAVE_FILE);
        String[] lines = fileContents.split("\n");
        this.seed = lines[0];
        this.random = new Random(convertString(this.seed));

        int height = DEFAULT_HEIGHT;
        int width = DEFAULT_WIDTH;

        TETile[][] board = new TETile[width][height];
        fillWithNothing();

        for (int y = 0; y < height; y++) {
            String row = lines[y + 1];
            for (int x = 0; x < width; x++) {
                char tileChar = row.charAt(x);
                if (tileChar == Tileset.NOTHING.character()) {
                    board[x][height - y - 1] = Tileset.NOTHING;
                } else if (tileChar == Tileset.FLOOR.character()) {
                    board[x][height - y - 1] = Tileset.FLOOR;
                } else if (tileChar == Tileset.WALL.character()) {
                    board[x][height - y - 1] = Tileset.WALL;
                } else if (tileChar == Tileset.FLOWER.character()) {
                    board[x][height - y - 1] = Tileset.FLOWER;
                } else if (tileChar == Tileset.AVATAR.character()) {
                    board[x][height - y - 1] = Tileset.AVATAR;
                    startX = x;
                    startY = height - y - 1;
                } else {
                    throw new IllegalArgumentException("Invalid tile character in file: " + tileChar);
                }
            }
        }
        ter.initialize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.currentState = board;
        this.avatar = new Avatar(currentState, startX, startY);
        ter.renderFrame(currentState, applyLOS, visibility);
        runGame();
    }

    public void loadGamee(String input) {
        int startX = 0;
        int startY = 0;
        String fileContents = FileUtils.readFile(SAVE_FILE);
        String[] lines = fileContents.split("\n");
        this.seed = lines[0];
        this.random = new Random(convertString(this.seed));

        int height = DEFAULT_HEIGHT;
        int width = DEFAULT_WIDTH;

        TETile[][] board = new TETile[width][height];
        fillWithNothing();

        for (int y = 0; y < height; y++) {
            String row = lines[y + 1];
            for (int x = 0; x < width; x++) {
                char tileChar = row.charAt(x);
                if (tileChar == Tileset.NOTHING.character()) {
                    board[x][height - y - 1] = Tileset.NOTHING;
                } else if (tileChar == Tileset.FLOOR.character()) {
                    board[x][height - y - 1] = Tileset.FLOOR;
                } else if (tileChar == Tileset.WALL.character()) {
                    board[x][height - y - 1] = Tileset.WALL;
                } else if (tileChar == Tileset.FLOWER.character()) {
                    board[x][height - y - 1] = Tileset.FLOWER;
                } else if (tileChar == Tileset.AVATAR.character()) {
                    board[x][height - y - 1] = Tileset.AVATAR;
                    startX = x;
                    startY = height - y - 1;
                } else {
                    throw new IllegalArgumentException("Invalid tile character in file: " + tileChar);
                }
            }
        }
        this.currentState = board;
        this.avatar = new Avatar(currentState, startX, startY);
        runGameFromInput(input);
    }


    private void updateVisibility() {
        for (int x = 0; x < DEFAULT_WIDTH; x++) {
            for (int y = 0; y < DEFAULT_HEIGHT; y++) {
                visibility[x][y] = false;
            }
        }
        for (int deltaX = -4; deltaX <= 4; deltaX++) {
            for (int deltaY = -4; deltaY <= 4; deltaY++) {
                int losX = avatar.posX + deltaX;
                int losY = avatar.posY + deltaY;
                if (losX >= 0 && losY >= 0 && losX < DEFAULT_WIDTH && losY < DEFAULT_HEIGHT) {
                    if (Math.sqrt((deltaX * deltaX) + (deltaY * deltaY)) <= 4) {
                        visibility[losX][losY] = true;
                    }
                }
            }
        }
    }
}

