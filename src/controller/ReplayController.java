/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import board.Board;
import board.BoardPiece;
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
    int bCaps = 0;
    int wCaps = 0;
    List<ReplayStatus> previousBoards = new ArrayList<>();
    
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
            
            this.updatePlayerStatsPanel();
            
            board.repaint();
        }
        
        return false;
    }
    
    public ReplayController() {
        super();
        
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(this);
        
        board.setShowPreviewStone(false);
        System.out.println("asdf");
        
        controlPanel.setListener(this);
    }
    
    public void load(GameInfo i) {
        info = i;
        
        bPlayerPanel.setPlayerName(info.blackPlayerName);
        bPlayerPanel.setPlayerRank(info.blackPlayerRank);
        bPlayerPanel.setCaptureCount("0");
        
        wPlayerPanel.setPlayerName(info.whitePlayerName);
        wPlayerPanel.setPlayerRank(info.whitePlayerRank);
        wPlayerPanel.setCaptureCount("0");
    }
    
    private void forward() {
        if(moveCounter+1 < info.moves.size()) {
            previousBoards.add(new ReplayStatus(this.getPreviousBoard(), bCaps, wCaps));
            
            Move m = info.moves.get(moveCounter);
                        
            if(m.player.equalsIgnoreCase("B")) {
                bCaps += board.makeMove(BoardPiece.BLACK_STONE, m.x, m.y);
            }
            else if(m.player.equalsIgnoreCase("W")) {
                wCaps += board.makeMove(BoardPiece.WHITE_STONE, m.x, m.y);
            }
            
            moveCounter++;
        }
    }
    
    private void back() {
        if(moveCounter-1 >= 0) {
            board.setBoard(previousBoards.get(previousBoards.size()-1).board);
            
            bCaps = previousBoards.get(previousBoards.size()-1).bCaptures;
            wCaps = previousBoards.get(previousBoards.size()-1).wCaptures;
            
            previousBoards.remove(previousBoards.size()-1);
            
            moveCounter--;
            
            if(moveCounter-1 >= 0) {
                board.setLastMoveMark(info.moves.get(moveCounter-1).x, info.moves.get(moveCounter-1).y);
            }
            else {
                board.setLastMoveMark(-10, -10);
            }
        }
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
    
    private void updatePlayerStatsPanel() {
        bPlayerPanel.setCaptureCount(String.valueOf(bCaps));
        wPlayerPanel.setCaptureCount(String.valueOf(wCaps));
    }

    @Override
    public void onBackAllButtonClick() {
        this.backAll();
        this.updatePlayerStatsPanel();
        
        board.repaint();
    }

    @Override
    public void onBackButtonClick() {
        this.back();
        this.updatePlayerStatsPanel();
        
        board.repaint();
    }

    @Override
    public void onForwardAllButtonClick() {
        this.forwardAll();
        this.updatePlayerStatsPanel();
        
        board.repaint();
    }

    @Override
    public void onForwardButtonClick() {
        this.forward();
        this.updatePlayerStatsPanel();
        
        board.repaint();
    }
}
