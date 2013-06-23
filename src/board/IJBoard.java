/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package board;

import java.awt.Graphics2D;

/**
 *
 * @author Fabiano
 */
public interface IJBoard {
    public void setBoard(IBoard b);
    public IBoard getBoard();
    
    public void drawTerritory(Graphics2D g);
    public void drawBoard(Graphics2D g, int x, int y, int w, int h);
    public void drawPiece(Graphics2D g, BoardPiece p, int x, int y, int w, int h);
    public void drawLastMoveMark(Graphics2D g, int x, int y, int w, int h);
}
