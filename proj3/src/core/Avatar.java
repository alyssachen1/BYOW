package core;
import tileengine.TETile;
import tileengine.*;


public class Avatar {
    public int posX;
    public int posY;
    private TETile[][] world;

    public Avatar(TETile[][] world, int startX, int startY) {
        this.world = world;
        this.posX = startX;
        this.posY = startY;
        world[posX][posY] = Tileset.FLOWER;
    }

    public boolean canMove(int deltaX, int deltaY) {
        if (world[posX + deltaX][posY + deltaY] == Tileset.CAT_EYE) {
            return true;
        }
        return false;
    }

    public void move(int deltaX, int deltaY) {
        if (canMove(deltaX, deltaY)) {
            world[posX][posY] = Tileset.CAT_EYE;
            posX += deltaX;
            posY += deltaY;
            world[posX][posY] = Tileset.FLOWER;
            MusicPlayer.playSoundEffect("src/walking-96582.wav");
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
