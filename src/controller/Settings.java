/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Image;
import java.io.*;
import javax.imageio.ImageIO;

/**
 *
 * @author Fabiano
 */
public class Settings {
    public boolean useTextures;
    public boolean useCoordinates;
    public int boardTextureIndex;
    public Image boardTexture;
    
    public static Settings load(String filename) throws FileNotFoundException, IOException {
        Settings loadedSettings = new Settings();
        
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        
        while(reader.ready()) {
            String line = reader.readLine();
            String[] settings = line.split("=");
            
            if(settings.length==2) {
                if(settings[0].equalsIgnoreCase("boardTexture")) {
                    loadedSettings.boardTextureIndex = Integer.parseInt(settings[1]);
                    loadedSettings.boardTexture = ImageIO.read(new File("resources/board"+(loadedSettings.boardTextureIndex+1)+".png"));
                }
                else if(settings[0].equalsIgnoreCase("useTextures")) {
                    if(settings[1].equalsIgnoreCase("1")) {
                        loadedSettings.useTextures = true;
                    }
                    else {
                        loadedSettings.useTextures = false;
                    }
                }
                else if(settings[0].equalsIgnoreCase("useCoordinates")) {
                    if(settings[1].equalsIgnoreCase("1")) {
                        loadedSettings.useCoordinates = true;
                    }
                    else {
                        loadedSettings.useCoordinates = false;
                    }
                }
            }
        }
        
        reader.close();
        
        return loadedSettings;
    }
}
