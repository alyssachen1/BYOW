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
//                running = false;

            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_N)) {
                // new game
                promptSeed();
                break;
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_L)) {
                // load game
            }
        }
        StdDraw.pause(20);
    }

    private void setUpCanvas() {
        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(0, 800);
        StdDraw.setYscale(0, 800);
    }

    private void promptSeed() {
        StdDraw.enableDoubleBuffering();
        StringBuilder seed = new StringBuilder("N");

            while (true) {
                StdDraw.clear(StdDraw.BLACK);

                StdDraw.text(400, 400, "Enter Seed: " + seed.toString() + "_");
                StdDraw.show();

                if (StdDraw.hasNextKeyTyped()) {
                    char key = StdDraw.nextKeyTyped();
                    if (Character.isDigit(key)) {
                        seed.append(key);
                    } else if ((key == 'S' || key == 's') && seed.length() > 1) {
                        seed.append("S");
                        StdDraw.text(400, 400, "Enter Seed: " + seed.toString() + "_");
                        StdDraw.show();
                        StdDraw.pause(500);
                        break;
                    }
                    StdDraw.text(400, 400, "Enter Seed: " + seed.toString() + "_");
                    StdDraw.show();
                }
                StdDraw.pause(50);
            }
            String seedValue = seed.substring(1, seed.length() - 1);
            // generate the world with the given seed
            World world = new World(seedValue);
            world.runGame();
            // ok pls do this
            }
    }
