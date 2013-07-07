/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import board.BoardPiece;
import gui.BoardFrame;

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
    }
}
