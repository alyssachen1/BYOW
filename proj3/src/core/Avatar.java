package core;

public class Avatar {

    private int WIDTH;

    private int GAME_HEIGHT;

    World world;

    public Avatar(int width, int game_height, World world) {
        this.WIDTH = width;
        this.GAME_HEIGHT = game_height;
        this.world = world;
    }

    // has to be floor tile
    public boolean canMove() {
        return false;
    }

    public void tryMove() {

    }


}
