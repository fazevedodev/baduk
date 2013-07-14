/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import board.BoardPiece;
import gui.BoardFrame;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fabiano
 */
public class GameController extends BoardFrame {
    BoardPiece player;
    BoardPiece currentPlayerTurn;
    
    public GameController(BoardPiece p) {
        super();
        
        player = p;
        controlPanel.setEnabled(false);
        currentPlayerTurn = BoardPiece.BLACK_STONE;
        
        try {
            board.initTextures();
        } catch (Exception ex) {
            Logger.getLogger(ReplayController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        board.setShowPreviewStone(true);
        board.setPlayerTurn(player);
    }
    
    @Override
    public void onClick(MouseEvent e, int x, int y) {
        if(currentPlayerTurn == player) {
            int stat = board.makeMove(player, x, y);
            
            if(stat >= 0) {
                this.switchTurns();
            }
        }
    }
    
    public void switchTurns() {
        if(currentPlayerTurn == BoardPiece.BLACK_STONE) {
            currentPlayerTurn = BoardPiece.WHITE_STONE;
        }
        else if(currentPlayerTurn == BoardPiece.WHITE_STONE) {
            currentPlayerTurn = BoardPiece.BLACK_STONE;
        }
    }
    
    public void showPreviewStone(boolean show) {
        board.setShowPreviewStone(show);
    }
    
    public void loadSettings(Settings s) {
        board.setBoardTexture(s.boardTexture);
    }
}
