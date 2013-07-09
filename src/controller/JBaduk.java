/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gui.MainMenu;
import gui.MainMenuListener;

/**
 *
 * @author Fabiano
 */
public class JBaduk implements MainMenuListener {
    MainMenu mainMenu;
    
    public JBaduk() {
        mainMenu = new MainMenu();
        mainMenu.setTitle("JBaduk");
        mainMenu.setLocationRelativeTo(null);
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
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void onSettingsButtonClick() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
