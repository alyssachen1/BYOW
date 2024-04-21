package core;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.event.KeyEvent;


public class UI {
    private boolean running = true;

    public UI() {
        setUpCanvas();
        mainMenu();
    }

    private void mainMenu() {
        StdDraw.enableDoubleBuffering();
        World world;
        while (running) {
            // Clear the screen
            StdDraw.clear(StdDraw.BLACK);

            // Draw the menu
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.text(400, 500, "THE HORROR GAME");
            StdDraw.text(400, 400, "New Game (N)");
            StdDraw.text(400, 350, "Load Game (L)");
            StdDraw.text(400, 300, "Quit (Q)");
            StdDraw.show();
            if (StdDraw.isKeyPressed(KeyEvent.VK_Q)) {
                running = false;
                System.exit(0);
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_N)) {
                // new game
                promptSeed();
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_L)) {
                // load game
                world.loadGame();
            }
        }
        StdDraw.pause(100);
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
                StdDraw.pause(50);  // Wait for a key
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
                return;
            }
        }

    }

    private void runGame(String seedValue) {
        World world = new World(seedValue);
        while (running) {
            world.runGame();
            if (StdDraw.isKeyPressed(KeyEvent.VK_Q)) {
                running = false;
                world.saveGame();
                System.exit(0);
            }

        }
    }
}
