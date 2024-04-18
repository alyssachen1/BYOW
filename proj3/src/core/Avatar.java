package core;

import tileengine.TETile;

public class Avatar {

    private int WIDTH;

    private int GAME_HEIGHT;

    private int posX;

    private int posY;

    World world;

    public Avatar(int width, int game_height, World world) {
        this.WIDTH = width;
        this.GAME_HEIGHT = game_height;
        this.world = world;
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
