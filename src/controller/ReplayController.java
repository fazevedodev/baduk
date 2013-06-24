/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import board.BoardPiece;
import gui.BoardFrame;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import sgf.GameInfo;
import sgf.Move;

/**
 *
 * @author Fabiano
 */
public class ReplayController extends BoardFrame {
    GameInfo info;
    int moveCounter = 0;
    
    private class MyDispatcher implements KeyEventDispatcher {
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                Move m = info.moves.get(moveCounter++);

                if(m.player.equalsIgnoreCase("B")) {
                    board.makeMove(BoardPiece.BLACK_STONE, m.x, m.y);
                }
                else if(m.player.equalsIgnoreCase("W")) {
                    board.makeMove(BoardPiece.WHITE_STONE, m.x, m.y);
                }
                
                board.repaint();
            }
            else if (e.getID() == KeyEvent.KEY_RELEASED) {
            }
            else if (e.getID() == KeyEvent.KEY_TYPED) {
            }
            
            return false;
        }
    }
    
    public ReplayController() {
        super();
        
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new MyDispatcher());
    }
    
    public void load(GameInfo i) {
        info = i;
    }
}
