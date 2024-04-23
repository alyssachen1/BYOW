package core;
import tileengine.TETile;
import tileengine.Tileset;
import java.util.*;
import utils.RandomUtils;

public class Hallway {
    Random random = new Random();
    private TETile[][] world;
    private int room1X;
    private int room1Y;
    private int room2X;
    private int room2Y;



    public Hallway(TETile[][] world, Room room1, Room room2, Random random) {
        this.world = world;
        this.room1X = RandomUtils.uniform(random, room1.startX + 1, room1.startX + room1.width - 1);
        this.room1Y = RandomUtils.uniform(random, room1.startY + 1, room1.startY + room1.height - 1);
        this.room2X = RandomUtils.uniform(random, room2.startX + 1, room2.startX + room2.width - 1);
        this.room2Y = RandomUtils.uniform(random, room2.startY + 1, room2.startY + room2.height - 1);
        connectRooms();
    }


    public void connectRooms() {
        if (room1X != room2X && room1Y != room2Y) {
            lShapedHall();
        } else {
            if (room1X == room2X) {
                verticalFillFloor();
                verticalWrapWall();
            }
            if (room1Y == room2Y) {
                horizontalFillFloor();
                horizontalWrapWall();
            }
        }
    }

    private void verticalFillFloor() {
        int startY = Math.min(room1Y, room2Y);
        int endY = Math.max(room1Y, room2Y);

        for (int y = startY; y < endY; y++) {
            world[room1X][y] = Tileset.FLOOR;
        }
    }

    private void verticalWrapWall() {
        int startY = Math.min(room1Y, room2Y);
        int endY = Math.max(room1Y, room2Y);

        for (int y = startY; y < endY; y++) {
            if (world[room1X + 1][y] == Tileset.CAT_EYE || world[room1X + 1][y] == Tileset.PURPLE_WALL) {
                continue;
            }
            world[room1X + 1][y] = Tileset.PURPLE_WALL;
        }
        for (int y = startY; y < endY; y++) {
            if (world[room1X - 1][y] == Tileset.CAT_EYE || world[room1X - 1][y] == Tileset.PURPLE_WALL) {
                continue;
            }
            world[room1X - 1][y] = Tileset.PURPLE_WALL;
        }
    }

    private void horizontalFillFloor() {
        int startX = Math.min(room1X, room2X);
        int endX = Math.max(room1X, room2X);

        for (int x = startX; x < endX; x++) {
            world[x][room1Y] = Tileset.CAT_EYE;
        }

    }

    private void horizontalWrapWall() {
        int startX = Math.min(room1X, room2X);
        int endX = Math.max(room1X, room2X);
        for (int x = startX; x <= endX + 1; x++) {
            if (world[x][room1Y + 1] == Tileset.CAT_EYE) {
                continue;
            }
            world[x][room1Y + 1] = Tileset.PURPLE_WALL;
        }
        for (int x = startX; x <= endX + 1; x++) {
            if (world[x][room1Y - 1] == Tileset.CAT_EYE) {
                continue;
            }
            world[x][room1Y - 1] = Tileset.PURPLE_WALL;
        }
    }

    private void lShapedHall() {
        horizontalFillFloor();
        horizontalWrapWall();
        int turnX = room2X;
        int startY = Math.min(room1Y, room2Y);
        int endY = Math.max(room1Y, room2Y);


        //fill floor for vertical hallway

        for (int y = startY; y <= endY; y++) {
            world[turnX][y] = Tileset.CAT_EYE;
        }

        //wrap wall

        for (int y = startY; y <= endY; y++) {
            if (world[turnX + 1][y] == Tileset.CAT_EYE) {
                continue;
            }
            world[turnX + 1][y] = Tileset.PURPLE_WALL;
        }
        for (int y = startY; y <= endY; y++) {
            if (world[turnX - 1][y] == Tileset.FLOOR) {
                continue;
            }
            world[turnX - 1][y] = Tileset.PURPLE_WALL;
        }



    }



}
