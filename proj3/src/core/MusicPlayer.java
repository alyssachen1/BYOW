package core;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer {

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
}
