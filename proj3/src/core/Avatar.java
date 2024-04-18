package core;
import tileengine.TETile;
import tileengine.*;

import tileengine.TETile;

public class Avatar {
    private int posX;
    private int posY;
    private TETile[][] world;



    public Avatar(TETile[][] world, Room room) {
        this.world = world;
        this.posX = room.startX;
        this.posY = room.startY;
        world[posX][posY] = Tileset.AVATAR;

    }

    public boolean canMove(int deltaX, int deltaY) {
        if (world[deltaX][deltaY] == Tileset.FLOOR) {
            return true;
        }
        return false;
    }

    public void move(int deltaX, int deltaY) {
        if (canMove(deltaX, deltaY)) {
            posX += deltaX;
            posY += deltaY;
        }
    }


    public void updateBoard() {


    }


}
