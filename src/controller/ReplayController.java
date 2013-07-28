/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import board.Board;
import board.BoardPiece;
import board.BoardScore;
import com.rits.cloning.Cloner;
import gui.BoardFrame;
import gui.ControlPanelListener;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import sgf.GameInfo;
import sgf.Move;
import sound.SoundManager;

/**
 *
 * @author Fabiano
 */
public class ReplayController extends BoardFrame implements KeyEventDispatcher,
                                                            ControlPanelListener {
    GameInfo info;
    
    int moveCounter = 0;
    
    List<ReplayStatus> previousBoards = new ArrayList<>();
    
    BoardScore boardScore;
    
    public ReplayController() {
        super();
        
        boardScore = new BoardScore();
        
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(this);
        
        try {
            board.initTextures();
        } catch (Exception ex) {
            Logger.getLogger(ReplayController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        board.setShowPreviewStone(false);
        
        controlPanel.setListener(this);
        
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    public void setBoardTexture(Image i) {
        board.setBoardTexture(i);
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
    
    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.getID() == KeyEvent.KEY_PRESSED) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_RIGHT:
                    this.forward(true);
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
        this.forward(true);
        this.updatePlayerStatsPanel();
        
        board.repaint();
    }
    
    private void forward(boolean soundOn) {
        if(moveCounter+1 < info.moves.size()) {
            if(soundOn) {
                SoundManager.playStoneSound();
            }
            
            previousBoards.add(new ReplayStatus(this.getPreviousBoard(), boardScore.bCaptures, boardScore.wCaptures));
            
            Move m = info.moves.get(moveCounter);
                        
            if(m.player.equalsIgnoreCase("B")) {
                boardScore.bCaptures += board.makeMove(BoardPiece.BLACK_STONE, m.x, m.y);
            }
            else if(m.player.equalsIgnoreCase("W")) {
                boardScore.wCaptures += board.makeMove(BoardPiece.WHITE_STONE, m.x, m.y);
            }
            
            moveCounter++;
        }
    }
    
    private void back() {
        if(moveCounter-1 >= 0) {
            board.setBoard(previousBoards.get(previousBoards.size()-1).board);
            
            boardScore.bCaptures = previousBoards.get(previousBoards.size()-1).bCaptures;
            boardScore.wCaptures = previousBoards.get(previousBoards.size()-1).wCaptures;
            
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
        if(moveCounter < 0) {
            moveCounter = 0;
        }
        
        for(int i=moveCounter; i<info.moves.size(); i++) {
            this.forward(false);
        }
        
        board.repaint();
    }
    
    private void backAll() {
        previousBoards.clear();
        
        moveCounter = 0;
        
        boardScore.bCaptures = 0;
        boardScore.wCaptures = 0;
        
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
        bPlayerPanel.setCaptureCount(String.valueOf(boardScore.bCaptures));
        wPlayerPanel.setCaptureCount(String.valueOf(boardScore.wCaptures));
    }
}
