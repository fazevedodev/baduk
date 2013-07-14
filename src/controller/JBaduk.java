/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import board.BoardPiece;
import gui.MainMenu;
import gui.MainMenuListener;
import gui.SettingsFrame;
import gui.SettingsFrameListener;
import java.awt.Image;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
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
    GameController gameController;
    ReplayController replayController;
    SettingsFrame settingsFrame;
    Image boardTexture;
        
    Settings settings;
    
    public JBaduk() {
        mainMenu = new MainMenu();
        mainMenu.setTitle("JBaduk");
        mainMenu.setLocationRelativeTo(null);
        mainMenu.setListener(this);
        
        try {
            this.loadSettings();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JBaduk.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JBaduk.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        gameController = new GameController(BoardPiece.BLACK_STONE);
        gameController.setExtendedState(JFrame.MAXIMIZED_BOTH);
        gameController.loadSettings(settings);
        gameController.setVisible(true);
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
        try {
            this.loadSettings();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JBaduk.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JBaduk.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        settingsFrame = new SettingsFrame(settings);
        settingsFrame.setListener(this);
        settingsFrame.setLocationRelativeTo(mainMenu);
        settingsFrame.setVisible(true);
    }

    @Override
    public void onSettingsSaved(Settings s) {
        boardTexture = s.boardTexture;
        
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("settings.txt"));
            
            writer.write("boardTexture="+s.boardTextureIndex+"\r\n");
            writer.write("useTextures="+(s.useTextures ? "1":"0")+"\r\n");
            writer.write("useCoordinates="+(s.useCoordinates ? "1":"0")+"\r\n");
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(JBaduk.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadSettings() throws FileNotFoundException, IOException {
        settings = Settings.load("settings.txt");
        
        /*BufferedReader reader = new BufferedReader(new FileReader("settings.txt"));
        
        while(reader.ready()) {
            String line = reader.readLine();
            String[] settings = line.split("=");
            
            if(settings.length==2) {
                if(settings[0].equalsIgnoreCase("boardTexture")) {
                    boardTextureIndex = Integer.parseInt(settings[1]);
                    boardTexture = ImageIO.read(new File("resources/board"+(boardTextureIndex+1)+".png"));
                }
                else if(settings[0].equalsIgnoreCase("useTextures")) {
                    if(settings[1].equalsIgnoreCase("1")) {
                        useTextures = true;
                    }
                    else {
                        useTextures = false;
                    }
                }
                else if(settings[0].equalsIgnoreCase("useCoordinates")) {
                    if(settings[1].equalsIgnoreCase("1")) {
                        useCoordinates = true;
                    }
                    else {
                        useCoordinates = false;
                    }
                }
            }
        }
        
        reader.close();*/
    }
}
