package core;
import edu.princeton.cs.algs4.StdDraw;
import tileengine.TETile;
import tileengine.*;

import java.awt.event.KeyEvent;

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
        if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
            move(-1, 0);
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
            move(1, 0);
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
            move(0, -1);
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_W)) {
            move(0, 1);
        }
        world.draw((double) posX, (double) posY);
    }


}
