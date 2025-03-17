import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

public class SoundPlayer {
    private static Clip currentClip;

    public static void playSound(String soundFileName) {
        try {
            File soundFile = new File(Objects.requireNonNull(SoundPlayer.class.getResource("/" + soundFileName)).toURI());
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            currentClip = AudioSystem.getClip();

            currentClip.open(audioInputStream);
            currentClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | URISyntaxException e) {
            System.err.println("Error playing sound: " + e.getMessage());
        }
    }

    public static void stopSound() {
        if (currentClip != null && currentClip.isRunning()) {
            currentClip.stop();
            currentClip.close();
            currentClip = null;
        }
    }
}
