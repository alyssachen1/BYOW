package core;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.event.KeyEvent;
import utils.*;

public class UI {
    private boolean customAvatar = false;

    public UI() {
        setUpCanvas();
        mainMenu();
    }

    private void mainMenu() {
        MusicPlayer.playMusic("src/Dark Ambient - Horror Background Music No Copyright (1).wav");
        StdDraw.enableDoubleBuffering();
        while (true) {
            StdDraw.clear(StdDraw.BLACK);

            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.text(400, 500, "THE HORROR GAME");
            StdDraw.text(400, 400, "New Game (N)");
            StdDraw.text(400, 350, "Load Game (L)");
            StdDraw.text(400, 300, "Quit (Q)");
            StdDraw.show();
            if (StdDraw.isKeyPressed(KeyEvent.VK_Q)) {
                System.exit(0);
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_N)) {
                StdDraw.pause(100);
                promptSeed();
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_L)) {
                runLoad();
            }
        }
    }

    private void setUpCanvas() {
        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(0, 800);
        StdDraw.setYscale(0, 800);
    }

    private void promptSeed() {
        StringBuilder seed = new StringBuilder("N");
        boolean seedEntered = false;
        while (!seedEntered) {
            StdDraw.clear(StdDraw.BLACK);
            StdDraw.text(400, 400, "Enter Seed: " + seed + "_");
            StdDraw.show();

            while (!StdDraw.hasNextKeyTyped()) {
                StdDraw.pause(50);
            }

            char key = StdDraw.nextKeyTyped();
            if (Character.isDigit(key)) {
                seed.append(key);
            } else if ((key == 'S' || key == 's') && seed.length() > 1) {
                seed.append('S');
                StdDraw.text(405, 400, "Enter Seed: " + seed + "_");
                StdDraw.show();
                StdDraw.pause(200);
                seedEntered = true;
                runGame(seed.toString());
            }
        }

    }

    private void runGame(String seedValue) {
        World world = new World(seedValue);
        world.runGame();
    }

    private void runLoad() {
        String content = FileUtils.readFile(World.SAVE_FILE);
        if (content.trim().isEmpty()) {
            System.exit(0); // Exit if the save file is empty
        } else {
            World world = new World();
            world.loadGame();
        }
    }

}
