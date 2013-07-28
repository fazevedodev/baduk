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
import net.NetworkClientController;
import net.NetworkHostController;
import net.NetworkListener;
import sound.SoundManager;

/**
 *
 * @author Fabiano
 */
public class GameController extends BoardFrame
                            implements NetworkListener {
    BoardPiece player;
    BoardPiece currentPlayerTurn;
    
    NetworkHostController host;
    NetworkClientController client;
    
    boolean startCounting;
    
    
    public GameController(BoardPiece p) {
        super();
        
        player = p;
        controlPanel.setEnabled(false);
        currentPlayerTurn = BoardPiece.BLACK_STONE;
        boardTool = BoardTool.MAKE_MOVE;
        startCounting = false;
        
        try {
            board.initTextures();
        } catch (Exception ex) {
            Logger.getLogger(ReplayController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        board.setShowPreviewStone(true);
        board.setPlayerTurn(player);
    }
    
    public void host() {
        host = new NetworkHostController();
        client = null;
        
        host.setListener(this);
        
        Thread t = new Thread(host);
        t.start();
    }
    
    public void connect(String addr, int port) {
        client = new NetworkClientController(addr, port);
        host = null;
        
        client.setListener(this);
        
        Thread t = new Thread(client);
        t.start();
    }
    
    @Override
    public void onClick(MouseEvent e, int x, int y) {
        if(boardTool == BoardTool.MAKE_MOVE) {
            if(currentPlayerTurn == player) {
                int stat = board.makeMove(player, x, y);

                if(stat >= 0) {
                    SoundManager.playStoneSound();
                    
                    boardScore.addCapture(player, stat);
                    
                    this.switchTurns();

                    if(host != null) {
                        host.send("MAKEMOVE&"+x+"&"+y+"\n");
                    }
                    else if(client != null) {
                        client.send("MAKEMOVE&"+x+"&"+y+"\n");
                    }
                }
            }
        }
        else if(boardTool == BoardTool.REMOVE_STONE) {
            int wcaps = board.clearGroup(BoardPiece.BLACK_STONE, x, y);
            int bcaps = board.clearGroup(BoardPiece.WHITE_STONE, x, y);
            
            boardScore.addCapture(BoardPiece.BLACK_STONE, bcaps);
            boardScore.addCapture(BoardPiece.WHITE_STONE, wcaps);
            
            if(host != null) {
                host.send("REMOVE&"+x+"&"+y+"\n");
            }
            else if(client != null) {
                client.send("REMOVE&"+x+"&"+y+"\n");
            }
            
            this.onCountButtonClick();
        }
    }
    
    @Override
    public void onPassButtonClick() {
        startCounting = true;
        
        if(host != null) {
            host.send("PASS\n");
        }
        else if(client != null) {
            client.send("PASS\n");
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

    @Override
    public void onResponse(String response) {
        String data[] = response.split("&");
        
        if(data[0].equals("MAKEMOVE")) {
            startCounting = false;
            
            int x = Integer.parseInt(data[1]);
            int y = Integer.parseInt(data[2]);
            
            if(player == BoardPiece.BLACK_STONE)
                board.makeMove(BoardPiece.WHITE_STONE, x, y);
            else {
                board.makeMove(BoardPiece.BLACK_STONE, x, y);
            }
            
            this.switchTurns();
            
            board.repaint();
        }
        else if(data[0].equals("PASS")) {
            this.switchTurns();
            
            if(startCounting) {
                this.onCountButtonClick();
                
                if(host != null) {
                    host.send("SHOWTERRITORY\n");
                }
                else if(client != null) {
                    client.send("SHOWTERRITORY\n");
                }
            }
        }
        else if(data[0].equals("REMOVE")) {
            int x = Integer.parseInt(data[1]);
            int y = Integer.parseInt(data[2]);
            
            boardScore.addCapture(BoardPiece.BLACK_STONE, board.clearGroup(BoardPiece.WHITE_STONE, x, y));
            boardScore.addCapture(BoardPiece.WHITE_STONE, board.clearGroup(BoardPiece.BLACK_STONE, x, y));
            
            this.onCountButtonClick();
        }
        else if(data[0].equals("SHOWTERRITORY")) {
            this.onCountButtonClick();
        }
    }
}
