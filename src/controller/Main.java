/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
            gameInfo = sgf.SgfReader.getGameInfo("igs.sgf");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        board = new ReplayController();
        board.load(gameInfo);
        board.setVisible(true);
    }
    /*
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        /*if(ke.getKeyCode() == KeyEvent.VK_LEFT) {
            board.countTerritory(BoardPiece.BLACK_STONE);
            board.countTerritory(BoardPiece.WHITE_STONE);
            board.setShowTerritory(true);
        }
        
        if(curr >= gameInfo.moves.size()) {
            board.setCounting(true);
        }
        
        if(ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            if(curr < gameInfo.moves.size()) {
                if(gameInfo.moves.get(curr).player.equalsIgnoreCase("B")) {
                    if(gameInfo.moves.get(curr).x >= 0)
                        bcaps += board.makeMove(BoardPiece.BLACK_STONE, gameInfo.moves.get(curr).x, gameInfo.moves.get(curr).y);
                }
                else if(gameInfo.moves.get(curr).player.equalsIgnoreCase("W")) {
                    if(gameInfo.moves.get(curr).x >= 0)
                        wcaps += board.makeMove(BoardPiece.WHITE_STONE, gameInfo.moves.get(curr).x, gameInfo.moves.get(curr).y);
                }

                curr++;
            }
            
            board.setBPlayerCaptureCount(String.valueOf(bcaps));
            board.setWPlayerCaptureCount(String.valueOf(wcaps));
        }
        
        board.repaint();*/
    //}

    /*@Override
    public void keyReleased(KeyEvent ke) {
    }*/
    
    
    
    public static void main(String args[]) {
        Main m = new Main();
    }
}
