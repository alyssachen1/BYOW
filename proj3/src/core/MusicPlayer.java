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
    // @source https://stackoverflow.com/questions/953598/audio-volume-control-increase-or-decrease-in-java -> used to increase volume of audioclip
    // @source https://stackoverflow.com/questions/29115634/how-to-use-thread-sleep-properly-in-java -> used for thread.sleep() to shorten audio length
    private static void playSound(String file, int loopCount) {
        try {
            File audioFile = new File(file);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float increase = 6.0206f;
            gainControl.setValue(increase);

            clip.loop(loopCount);
            clip.start();

            new Thread(() -> {
                try {
                    Thread.sleep(500);
                    clip.stop();
                    clip.close();
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    System.out.println("Audio playing thread was interrupted");
                }
            }).start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
