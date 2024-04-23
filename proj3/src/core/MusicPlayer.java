package core;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer {

    // @source https://www.tabnine.com/code/java/methods/javax.sound.sampled.Clip/open
    public static void playMusic(String file) {
        try {
            File audioFile = new File(file);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void playSoundEffect(String file) {
        playSound(file, 0); // Play only once
    }

    // @source https://www.tabnine.com/code/java/methods/javax.sound.sampled.Clip/open
    private static void playSound(String file, int loopCount) {
        try {
            File audioFile = new File(file);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            clip.loop(loopCount);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
