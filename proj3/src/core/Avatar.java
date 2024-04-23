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
<<<<<<< HEAD
        TETile nextPos = world[posX + deltaX][posY + deltaY];
        if (nextPos == Tileset.FLOOR || nextPos == Tileset.TREE) {
            if (world[posX + deltaX][posY + deltaY] == Tileset.CAT_EYE) {
                return true;
            }
            if (world[posX + deltaX][posY + deltaY] == Tileset.FLOOR) {
                return true;
            }
=======
        if (world[posX + deltaX][posY + deltaY] == Tileset.FLOOR) {
            return true;
>>>>>>> parent of 690d366 (fixed footsteps and added ending screen)
        }
        return false;
    }

    public void move(int deltaX, int deltaY) {
        if (canMove(deltaX, deltaY)) {
<<<<<<< HEAD
            world[posX][posY] = Tileset.CAT_EYE;
            posX += deltaX;
            posY += deltaY;
            world[posX][posY] = Tileset.FLOWER;
            MusicPlayer.playSoundEffect("src/walking-96582.wav");
        }
    }

    public void movee(int deltaX, int deltaY) {
        TETile nextPos = world[posX + deltaX][posY + deltaY];
        if (canMove(deltaX, deltaY)) {
            world[posX][posY] = Tileset.CAT_EYE;
            posX += deltaX;
            posY += deltaY;
            world[posX][posY] = Tileset.FLOWER;
            if (nextPos == Tileset.TREE) {
                finishGame();
            }
=======
            world[posX][posY] = Tileset.FLOOR;
            posX += deltaX;
            posY += deltaY;
            world[posX][posY] = Tileset.FLOWER;
>>>>>>> parent of 690d366 (fixed footsteps and added ending screen)
            MusicPlayer.playSoundEffect("src/walking-96582.wav");
        }
    }

<<<<<<< HEAD
    private void finishGame() {
        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(0, 800);
        StdDraw.setYscale(0, 800);
        StdDraw.enableDoubleBuffering();

        while (true) {
            StdDraw.clear(StdDraw.BLACK);
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.text(400, 500, "CONGRATULATIONS! YOU'VE FINISHED THE GAME");
            StdDraw.text(400, 400, "PRESS Q TO QUIT");
            StdDraw.show();
            StdDraw.pause(100);

            if (StdDraw.isKeyPressed(KeyEvent.VK_Q)) {
                System.exit(0);
                break;
            }
        }
    }
=======
>>>>>>> parent of 690d366 (fixed footsteps and added ending screen)

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
