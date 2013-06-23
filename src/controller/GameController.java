/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import board.BoardPiece;
import gui.BoardFrameController;
import sgf.GameInfo;

/**
 *
 * @author Fabiano
 */
public class GameController extends BoardFrameController {
    BoardPiece playerTurn;
    GameInfo   info;
    
    public GameController() {
        super();
        
        playerTurn = BoardPiece.BLACK_STONE;
    }
    
    public void setInfo(GameInfo i) {
        info = i;
    }
        
    private void switchTurn() {
        if(playerTurn == BoardPiece.BLACK_STONE) {
            playerTurn = BoardPiece.WHITE_STONE;
        }
        else {
            playerTurn = BoardPiece.BLACK_STONE;
        }
    }
}
