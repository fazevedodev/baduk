/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sound;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.*;

/**
 *
 * @author Fabiano
 */
public class SoundManager {
    public static void playStoneSound() {
        clip.setFramePosition(0);
        clip.start();
    }
    
    public static void init() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        SoundManager.initStoneSounds();
    }
    
    private static void initStoneSounds() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        clip = AudioSystem.getClip();
        clip.open(AudioSystem.getAudioInputStream(new File("resources/snd001.wav")));
    }
    
    private static Clip clip;
}
