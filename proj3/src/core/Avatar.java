package core;
import edu.princeton.cs.algs4.StdDraw;
import tileengine.TETile;
import tileengine.*;

import java.awt.event.KeyEvent;

public class Avatar {
    private int posX;
    private int posY;
    private TETile[][] world;

    public Avatar(TETile[][] world, int startX, int startY) {
        this.world = world;
        this.posX = startX;
        this.posY = startY;
        world[posX][posY] = Tileset.AVATAR;
    }

//    public Avatar(TETile[][] world, Room room) {
//        this.world = world;
//        this.posX = room.startX;
//        this.posY = room.startY;
//        Avatar avatar = new Avatar(world, posX, posY);
////        world[posX][posY] = Tileset.AVATAR;
//    }

    public boolean canMove(int deltaX, int deltaY) {
        if (world[posX + deltaX][posY + deltaY] == Tileset.FLOOR) {
            return true;
        }
        return false;
    }

    public void move(int deltaX, int deltaY) {
        if (canMove(deltaX, deltaY)) {
            world[posX][posY] = Tileset.FLOOR;
            posX += deltaX;
            posY += deltaY;
            world[posX][posY] = Tileset.AVATAR;
        }
    }


    public void updateBoard(char key) {
        if (key == 'a' || key == 'A') {
            move(-1, 0);
        }
        if (key == 'd' || key == 'D') {
            move(1, 0);
        }
        if (key == 's' || key == 'S') {
            move(0, -1);
        }
        if (key == 'w' || key == 'W') {
            move(0, 1);
        }
    }
}