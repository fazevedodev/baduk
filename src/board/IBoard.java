/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package board;

/**
 *
 * @author Fabiano
 */
public interface IBoard {
    /**
     * Adds a board piece (i.e., BLACK_STONE or WHITE_STONE) to the board on (x, y).
     * @param p The piece to be added.
     * @param x The x coordinate of the board.
     * @param y The y coordinate of the board.
     */
    public void add(BoardPiece p, int x, int y);
    
    /**
     * Sets the board coordinate (x, y) to EMPTY.
     * @param x
     * @param y 
     */
    public void clear(int x, int y);
    
    /**
     * Clears a group of pieces of the p type.
     * @param p The type of pieces to be cleared.
     * @param x The x coordinate of one of the pieces of the group.
     * @param y The y coordinate of one of the pieces of the group.
     * @return The count of pieces cleared.
     */
    public int clearGroup(BoardPiece p, int x, int y);
    
    /**
     * Returns the liberty count of the group of p pieces in the (x, y) coordinates.
     * @param p The type of pieces of the group.
     * @param x The x coordinate of one of the pieces of the group.
     * @param y The y coordinate of one of the pieces of the group.
     * @return The count of liberties of the group.
     */
    public int countLiberties(BoardPiece p, int x, int y);
    
    /**
     * Makes a move, taking in consideration the rules of the game.
     * @param p The piece of the current player playing.
     * @param x The x coordinate of the move.
     * @param y The y coordinate of the move.
     * @return Returns the count of pieces captured, if any, due to this move.
     */
    public int makeMove(BoardPiece p, int x, int y);
    
    public int countTerritory(BoardPiece p);
    
    public BoardPiece[][] getTerritoryMatrix();
    
    public int getSize();
    
    public BoardPiece getState(int x, int y);
    
    public BoardPiece[][] getStateMatrix();
}
