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
    
    
    public GameController(BoardPiece p) {
        super();
        
        player = p;
        controlPanel.setEnabled(false);
        currentPlayerTurn = BoardPiece.BLACK_STONE;
        boardTool = BoardTool.MAKE_MOVE;
        
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
                    this.switchTurns();

                    if(host != null) {
                        System.out.println("AS HOST: "+"MAKEMOVE&"+x+"&"+y);
                        host.send("MAKEMOVE&"+x+"&"+y+"\n");
                    }
                    else if(client != null) {
                        System.out.println("AS CLIENT: "+"MAKEMOVE&"+x+"&"+y);
                        client.send("MAKEMOVE&"+x+"&"+y+"\n");
                    }
                }
            }
        }
        else if(boardTool == BoardTool.REMOVE_STONE) {
            int caps = board.clearGroup(player, x, y);
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
        System.out.println("RECEIVED: '"+response+"'");
        String data[] = response.split("&");
        
        if(data[0].equals("MAKEMOVE")) {
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
    }
}
