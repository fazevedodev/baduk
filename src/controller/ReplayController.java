/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import board.Board;
import board.BoardPiece;
import board.IBoard;
import com.rits.cloning.Cloner;
import gui.BoardFrame;
import gui.ControlPanelListener;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import sgf.GameInfo;
import sgf.Move;

/**
 *
 * @author Fabiano
 */
public class ReplayController extends BoardFrame implements KeyEventDispatcher,
                                                            ControlPanelListener {
    GameInfo info;
    int moveCounter = 0;
    List<IBoard> previousBoards = new ArrayList<>();
    
    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.getID() == KeyEvent.KEY_PRESSED) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_RIGHT:
                    this.forward();
                    break;
                case KeyEvent.VK_LEFT:
                    this.back();
                    break;
            }
        }
        
        return false;
    }
    
    public ReplayController() {
        super();
        
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(this);
        
        controlPanel.setListener(this);
    }
    
    public void load(GameInfo i) {
        info = i;
    }
    
    private void forward() {
        if(moveCounter+1 < info.moves.size()) {
            previousBoards.add(this.getPreviousBoard());
            
            Move m = info.moves.get(moveCounter);
            
            if(m.player.equalsIgnoreCase("B")) {
                board.makeMove(BoardPiece.BLACK_STONE, m.x, m.y);
            }
            else if(m.player.equalsIgnoreCase("W")) {
                board.makeMove(BoardPiece.WHITE_STONE, m.x, m.y);
            }
            
            moveCounter++;
        }
        
        board.repaint();
    }
    
    private void back() {
        if(moveCounter-1 >= 0) {
            board.setBoard(previousBoards.get(previousBoards.size()-1));
            previousBoards.remove(previousBoards.size()-1);
            
            moveCounter--;
            
            if(moveCounter-1 >= 0) {
                board.setLastMoveMark(info.moves.get(moveCounter-1).x, info.moves.get(moveCounter-1).y);
            }
            else {
                board.setLastMoveMark(-10, -10);
            }
        }
        
        board.repaint();
    }
    
    private void forwardAll() {
        for(int i=moveCounter; i<info.moves.size(); i++) {
            this.forward();
        }
        
        board.repaint();
    }
    
    private void backAll() {
        previousBoards.clear();
        
        moveCounter = 0;
        
        board.setBoard(new Board());
        board.setLastMoveMark(-10, -10);
        board.repaint();
    }
    
    private Board getPreviousBoard() {
        Cloner cloner = new Cloner();
        Board clone = cloner.deepClone((Board)board.getBoard());
        
        return clone;
    }

    @Override
    public void onBackAllButtonClick() {
        this.backAll();
    }

    @Override
    public void onBackButtonClick() {
        this.back();
    }

    @Override
    public void onForwardAllButtonClick() {
        this.forwardAll();
    }

    @Override
    public void onForwardButtonClick() {
        this.forward();
    }
}
