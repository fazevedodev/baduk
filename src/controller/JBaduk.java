/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gui.MainMenu;
import gui.MainMenuListener;
import gui.SettingsFrame;
import gui.SettingsFrameListener;
import java.awt.Image;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import sgf.GameInfo;
import sgf.SgfReader;

/**
 *
 * @author Fabiano
 */
public class JBaduk implements MainMenuListener,
                               SettingsFrameListener {
    MainMenu mainMenu;
    ReplayController replayController;
    SettingsFrame settingsFrame;
    Image boardTexture;
    
    public JBaduk() {
        mainMenu = new MainMenu();
        mainMenu.setTitle("JBaduk");
        mainMenu.setLocationRelativeTo(null);
        mainMenu.setListener(this);
    }
    
    public void showMainMenu(boolean show) {
        mainMenu.setVisible(show);
    }
    
    public static void main(String args[]) {
        JBaduk jBaduk = new JBaduk();
        
        jBaduk.showMainMenu(true);
    }

    @Override
    public void onPlayOnlineButtonClick() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onPlayLanButtonClick() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onCreateButtonClick() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onLoadButtonClick() {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("sgf", "sgf");
        
        JFileChooser fileChooser = new JFileChooser(".");
        fileChooser.setFileFilter(filter);
        fileChooser.setAcceptAllFileFilterUsed(false);
        
        String filePath;
        int fileChooserReturn = fileChooser.showOpenDialog(mainMenu);
        
        if(fileChooserReturn == JFileChooser.APPROVE_OPTION) {
            filePath = fileChooser.getSelectedFile().getAbsolutePath();
            
            GameInfo gameInfo = null;
            
            try {
                gameInfo = SgfReader.getGameInfo(filePath);
            } catch (IOException ex) {
                Logger.getLogger(JBaduk.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            replayController = new ReplayController();
            replayController.load(gameInfo);
            replayController.setBoardTexture(boardTexture);
            replayController.setVisible(true);
        }
    }
    
    @Override
    public void onSettingsButtonClick() {
        settingsFrame = new SettingsFrame();
        settingsFrame.setListener(this);
        settingsFrame.setLocationRelativeTo(mainMenu);
        settingsFrame.setVisible(true);
    }

    @Override
    public void onSettingsSaved(Image bTexture, int tabIndex, boolean useTextures, boolean useCoordinates) {
        boardTexture = bTexture;
        
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("settings.txt"));
            
            writer.write("boardTexture="+tabIndex+"\r\n");
            writer.write("useTextures="+useTextures+"\r\n");
            writer.write("useCoordinates="+useCoordinates+"\r\n");
            writer.write("defaultDir=");
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(JBaduk.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
