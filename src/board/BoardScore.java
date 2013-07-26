/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package board;

/**
 *
 * @author Fabiano
 */
public class BoardScore {
    public int bTerritory;
    public int bCaptures;
    public int wTerritory;
    public int wCaptures;
    
    public BoardScore() {
        bTerritory = 0;
        bCaptures = 0;
        wTerritory = 0;
        wCaptures = 0;
    }
    
    public void addCapture(BoardPiece p, int value) {
        if(BoardPiece.BLACK_STONE == p) {
            bCaptures += value;
        }
        else if(BoardPiece.WHITE_STONE == p) {
            wCaptures += value;
        }
    }
    
    public void addTerritory(BoardPiece p, int value) {
        if(BoardPiece.BLACK_STONE == p) {
            bTerritory += value;
        }
        else if(BoardPiece.WHITE_STONE == p) {
            wTerritory += value;
        }
    }
}
