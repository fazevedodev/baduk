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
import gui.lan.*;
import java.awt.Image;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.GameHost;
import sgf.GameInfo;
import sgf.SgfReader;

/**
 *
 * @author Fabiano
 */
public class JBaduk implements MainMenuListener,
                               SettingsFrameListener,
                               JoinLanFrameListener,
                               CreateLanFrameListener {
    MainMenu mainMenu;
    GameController gameController;
    ReplayController replayController;
    SettingsFrame settingsFrame;
    JoinLanFrame joinLanFrame;
    CreateLanFrame createLanFrame;
    Image boardTexture;
    
    Settings settings;
    
    //Network
    GameHost gameHost;
    
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
        joinLanFrame = new JoinLanFrame();
        joinLanFrame.setListener(this);
        joinLanFrame.setTitle("Join LAN game");
        joinLanFrame.setLocationRelativeTo(mainMenu);
        joinLanFrame.setVisible(true);
    }

    @Override
    public void onCreateButtonClick() {
        createLanFrame = new CreateLanFrame();
        createLanFrame.setListener(this);
        createLanFrame.setLocationRelativeTo(mainMenu);
        createLanFrame.setTitle("Create game");
        createLanFrame.setVisible(true);
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
        
        boardTexture = settings.boardTexture;
    }

    @Override
    public void onConnectButtonClick(String ip, int port) {
        gameController = new GameController(BoardPiece.BLACK_STONE);
        gameController.setExtendedState(JFrame.MAXIMIZED_BOTH);
        gameController.loadSettings(settings);
        gameController.setVisible(true);
        gameController.connect(ip, port);
    }

    @Override
    public void onCreateButtonClick(CreateLanSettings s) {
        gameController = new GameController(BoardPiece.WHITE_STONE);
        gameController.setExtendedState(JFrame.MAXIMIZED_BOTH);
        gameController.loadSettings(settings);
        gameController.setVisible(true);
        gameController.host();
    }
}
