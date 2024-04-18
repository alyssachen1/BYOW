package core;
import tileengine.TETile;
import tileengine.*;

import tileengine.TETile;

public class Avatar {

    private int WIDTH;
    private int posX;
    private int posY;

    private int GAME_HEIGHT;
    private TETile[][] world;

    private int posX;

    private int posY;

    World world;


    public Avatar(TETile[][] world, Room room) {
        this.world = world;
        this.posX = room.startX;
        this.posY = room.startY;
        world[posX][posY] = Tileset.AVATAR;

    }

    // has to be floor tile
    public boolean canMove(int deltaX, int deltaY) {
        TETile[][] t = world.currentState;
        for (int i = 0; i < t.length; i++) {

        }
        return false;
    }

    public void move(int deltaX, int deltaY) {
        TETile[][] t = world.currentState;
        if (canMove(deltaX, deltaY)) {
            posX += deltaX;
            posY += deltaY;
        }
    }


}
