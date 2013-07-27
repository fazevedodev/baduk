/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sound;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

/**
 *
 * @author Fabiano
 */
public class SoundManager {
    public static void playStoneSound() {
        stoneSound.loop(0);
    }
    
    public static void init() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        SoundManager.initStoneSounds();
    }
    
    private static void initStoneSounds() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        AudioInputStream ais = AudioSystem.getAudioInputStream(new File("resources/snd001.wav"));

        stoneSound = AudioSystem.getClip();
        stoneSound.open(ais);
    }
    
    private static Clip stoneSound;
}
