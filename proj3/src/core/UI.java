package core;
import edu.princeton.cs.algs4.StdDraw;
import tileengine.TETile;
import tileengine.Tileset;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.event.KeyEvent;


public class UI {
    private boolean customAvatar = false;

    public UI() {
        setUpCanvas();
        mainMenu();
    }

    private void mainMenu() {
        StdDraw.enableDoubleBuffering();
        while (true) {
            StdDraw.clear(StdDraw.BLACK);

            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.text(400, 500, "THE HORROR GAME");
            StdDraw.text(400, 400, "New Game (N)");
            StdDraw.text(400, 350, "Load Game (L)");
            StdDraw.text(400, 300, "Quit (Q)");
//            StdDraw.text(400, 250, "Custom Avatar (C)");
            StdDraw.show();
            if (StdDraw.isKeyPressed(KeyEvent.VK_Q)) {
                System.exit(0);
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_N)) {
                promptSeed();
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_L)) {
                runLoad();
            }
//            if (StdDraw.isKeyPressed(KeyEvent.VK_C)) {
//                customAvatar();
//                customAvatar = true;
//            }
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

//    public void customAvatar() {
//        JFileChooser fileChooser = new JFileChooser();
//        fileChooser.setDialogTitle("Select an avatar image");
//        fileChooser.setAcceptAllFileFilterUsed(false);
//        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "png", "gif");
//        fileChooser.addChoosableFileFilter(filter);
//
//        int result = fileChooser.showOpenDialog(null);
//        if (result == JFileChooser.APPROVE_OPTION) {
//            File selectedFile = fileChooser.getSelectedFile();
//            try {
//                BufferedImage avatarImage = ImageIO.read(selectedFile);
//                StdDraw.picture(400, 400, "your_avatar.png", 100, 100);
//                saveAvatarImage(avatarImage);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

//    private void saveAvatarImage(BufferedImage image) {
//        try {
//            File outputfile = new File("resources/avatars/custom_avatar.png");
//            ImageIO.write(image, "png", outputfile);
//
//            TETile customAvatar = new TETile('@', Color.white, Color.black, "Custom Avatar", "resources/avatars/custom_avatar.png", generateUniqueID());
//            Tileset.CUSTOM_AVATAR = customAvatar;
//        } catch (IOException e) {
//            System.err.println("Error saving the avatar image: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }

//    private int generateUniqueID() {
//        // Generate a unique ID for the tile
//        return Tileset.CUSTOM_AVATAR != null ? Tileset.CUSTOM_AVATAR.id() + 1 : 100; // Start custom IDs from 100 for example
//    }

    private void runGame(String seedValue) {
        World world = new World(seedValue);
        world.runGame();
    }

    private void runLoad() {
        World world = new World();
        world.loadGame();
    }

}
