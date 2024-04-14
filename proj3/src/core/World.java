package core;

import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;
import utils.FileUtils;

import java.util.Random;

public class World {

    public static final int DEFAULT_WIDTH = 80;

    public static final int DEFAULT_HEIGHT = 40;

    private static final String SAVE_FILE = "src/save.txt";

    private static final int notFilled = 3200; //count amount of empty tiles; make sure less than 50% of the map filled with nothing

    //    private long prevFrameTimestep;
    private TERenderer ter;
    private Random random;
    private TETile[][] currentState;
    private int width;
    private int height;
    Room room;


    public World(long seed) {
        width = DEFAULT_WIDTH;
        height = DEFAULT_HEIGHT;
        ter = new TERenderer();
        ter.initialize(width, height);
        random = new Random(seed);
        TETile[][] currentState = new TETile[width][height];
        fillWithNothing(currentState); //just added this
        placeRoom();
//        fillWorld(currentState); //change this to fill with rooms or hallways, etc.

    }

    public void fillWorld(TETile[][] tiles) {
        int height = tiles[0].length;
        int width = tiles.length;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = randomTile();
            }
        }
    }

    private void placeRoom(Room room) {

    }

    private boolean isEnoughSpace() {
        return true;
    }


    private void generateHallway() {
    }
    // use Prim's algorithm to select hallways to connect two rooms


    //helper method for generateRoom


    //generate hallway that can connect to one room to another or only to one room
//    private void generateHallway(Room room, Room room) {
//    }


    //check if there is enough space to generate one more room


    public void fillWithNothing(TETile[][] tiles) {
        int height = tiles[0].length;
        int width = tiles.length;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = Tileset.NOTHING;
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


    public void saveBoard(TETile[][] tiles) {
//        TETile[][] transposeState = transpose(tiles);
//        this.currentState = flip(transposeState);
        this.width = tiles[0].length;
        this.height = tiles.length;
        saveBoard();
    }
//    public void saveBoard(TETile[][] tiles) {
//        TETile[][] transposeState = transpose(tiles);
//        this.currentState = flip(transposeState);
//        this.width = tiles[0].length;
//        this.height = tiles.length;
//        saveBoard();
//    }



    public void saveBoard() {
        StringBuilder builder = new StringBuilder();
        builder.append(width).append(" ").append(height).append("\n");

        for (int y = height - 1; y >= 0; y--) {
            for (int x = 0; x < width; x++) {
                if (currentState[x][y].equals(Tileset.CELL)) {
                    builder.append("1");
                } else {
                    builder.append("0");
                }
            }
            builder.append("\n");
        }

        FileUtils.writeFile(SAVE_FILE, builder.toString());
    }
//    public TETile[][] loadBoard(String filename) {
//            try {
//                String fileContents = FileUtils.readFile(filename);
//
//
//                String[] lines = fileContents.split("\n");
//                if (lines.length < 2) {
//                    throw new IllegalArgumentException("Invalid file format or empty file.");
//                }
//
//                String[] dimensions = lines[0].split(" ");
//                if (dimensions.length != 2) {
//                    throw new IllegalArgumentException("Invalid dimensions line in file.");
//                }
//                int width = Integer.parseInt(dimensions[0]);
//                int height = Integer.parseInt(dimensions[1]);
//
//                TETile[][] board = new TETile[width][height];
//                fillWithNothing(board);
//
//                for (int y = 0; y < height; y++) {
//                    String row = lines[y + 1];
//                    for (int x = 0; x < width; x++) {
//                        char tileChar = row.charAt(x);
//                        if (tileChar == '1') {
//                            board[x][height - y - 1] = Tileset.CELL;
//                        } else {
//                            throw new IllegalArgumentException("Invalid tile character in file: " + tileChar);
//                        }
//                    }
//                }
//
//                this.width = width;
//                this.height = height;
//                this.currentState = board;
//
//                return board;
//
//            } catch (Exception e) {
//
//                e.printStackTrace();
//                return null;
//            }
//    }

    public void placeRoom() {
        Room room = new Room(currentState, random);
    }

    public void runGame() {
        ter.renderFrame(currentState);

    }

}


