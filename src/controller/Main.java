/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gui.MainMenu;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fabiano
 */
public class Main {
    sgf.GameInfo gameInfo;
    int curr=0;
    ReplayController board;
    int wcaps = 0;
    int bcaps = 0;
    
    public Main() {
        try {
            gameInfo = sgf.SgfReader.getGameInfo("keiko.sgf");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        board = new ReplayController();
        board.load(gameInfo);
        board.setVisible(true);
        
        /*MainMenu mainMenu = new MainMenu();
        mainMenu.setTitle("JBaduk");
        mainMenu.setVisible(true);
        mainMenu.setLocationRelativeTo(null);*/
    }    
    
    /*public static void main(String args[]) {
        Main m = new Main();
    }*/
}
