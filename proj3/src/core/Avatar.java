package core;
import tileengine.TETile;
import tileengine.*;

public class Avatar {

    private int WIDTH;
    private int posX;
    private int posY;

    private int GAME_HEIGHT;
    private TETile[][] world;

    public Avatar(TETile[][] world, Room room) {
        this.world = world;
        this.posX = room.startX;
        this.posY = room.startY;
        world[posX][posY] = Tileset.AVATAR;

    }

    // has to be floor tile
    public boolean canMove() {
        return false;
    }

    public void tryMove() {

    }


}
