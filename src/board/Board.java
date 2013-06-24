/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package board;

/**
 *
 * @author Fabiano
 */
public class Board implements IBoard {
    BoardPiece territoryState[][];
    BoardPiece boardState[][];
    boolean libsChecked[][];
    int boardSize;
    
    public Board() {
        boardSize = 19;
        
        territoryState = new BoardPiece[boardSize][boardSize];
        boardState = new BoardPiece[boardSize][boardSize];
        libsChecked = new boolean[boardSize][boardSize];
        
        for(int i=0; i<boardSize; i++) {
            for(int j=0; j<boardSize; j++) {
                territoryState[i][j] = BoardPiece.EMPTY;
                boardState[i][j] = BoardPiece.EMPTY;
                libsChecked[i][j] = false;
            }
        }
    }
    
    public Board copy() {
        Board theCopy = new Board();
        
        theCopy.boardSize = this.boardSize;
        theCopy.boardState = this.boardState;
        theCopy.libsChecked = this.libsChecked;
        theCopy.territoryState = this.territoryState;
        
        return theCopy;
    }
    
    @Override
    public int getSize() {
        return boardSize;
    }
    
    @Override
    public BoardPiece getState(int x, int y) {
        return boardState[x][y];
    }
    
    @Override
    public BoardPiece[][] getStateMatrix() {
        return boardState;
    }
    
    @Override
    public void add(BoardPiece p, int x, int y) {
        if(x >= 0 && x < boardSize && y >= 0 && y < boardSize) {
            boardState[x][y] = p;
        }
        else {
            System.err.println("Adding piece out of the board bounds.");
        }
    }

    @Override
    public void clear(int x, int y) {
        if(x >= 0 && x < boardSize && y >= 0 && y < boardSize) {
            boardState[x][y] = BoardPiece.EMPTY;
        }
        else {
            System.err.println("Clearing piece out of the board bounds.");
        }
    }

    @Override
    public int clearGroup(BoardPiece p, int x, int y) {
        int captures = this.clearGroupInternally(p, x, y);
        
        this.clearLibertyCheckMatrix();
        
        return captures;
    }

    @Override
    public int countLiberties(BoardPiece p, int x, int y) {
        int liberties = this.countLibertiesInternally(p, x, y);
        
        this.clearLibertyCheckMatrix();
        
        return liberties;
    }
    
    @Override
    public int makeMove(BoardPiece p, int x, int y) {
        //Illegal move
        if(boardState[x][y] == BoardPiece.BLACK_STONE ||
           boardState[x][y] == BoardPiece.WHITE_STONE) {
            return -1;
        }
        
        BoardPiece p2;
        if(p == BoardPiece.BLACK_STONE) {
            p2 = BoardPiece.WHITE_STONE;
        }
        else {
            p2 = BoardPiece.BLACK_STONE;
        }
        
        this.add(p, x, y);
        
        int captures = 0;
        
        //Check for suicide piece
        if(this.countLiberties(p, x, y) == 0) {
            //If it captures an opponent group at west
            if(this.countLiberties(p2, x-1, y) == 0) {
                captures += this.clearGroup(p2, x-1, y);
            }
            //If it captures an opponent group at east
            if(this.countLiberties(p2, x+1, y) == 0) {
                captures += this.clearGroup(p2, x+1, y);
            }
            //If it captures an opponent group at north
            if(this.countLiberties(p2, x, y-1) == 0) {
                captures += this.clearGroup(p2, x, y-1);
            }
            //If it captures an opponent group at south
            if(this.countLiberties(p2, x, y+1) == 0) {
                captures += this.clearGroup(p2, x, y+1);
            }
            //If it captured at least something, the it's a legal move
            if(captures > 0) {
                return captures;
            }
            //if not, it's a suicide and therefore an illegal move
            else {
                this.clear(x, y);
                
                return -1;
            }
        }
        else {
            //If it captures an opponent group at west
            if(this.countLiberties(p2, x-1, y) == 0) {
                captures += this.clearGroup(p2, x-1, y);
            }
            //If it captures an opponent group at east
            if(this.countLiberties(p2, x+1, y) == 0) {
                captures += this.clearGroup(p2, x+1, y);
            }
            //If it captures an opponent group at north
            if(this.countLiberties(p2, x, y-1) == 0) {
                captures += this.clearGroup(p2, x, y-1);
            }
            //If it captures an opponent group at south
            if(this.countLiberties(p2, x, y+1) == 0) {
                captures += this.clearGroup(p2, x, y+1);
            }
        }
        
        return captures;
    }
    
    @Override
    public BoardPiece[][] getTerritoryMatrix() {
        return territoryState;
    }
    
    private void clearLibertyCheckMatrix() {
        for(int i=0; i<boardSize; i++) {
            for(int j=0; j<boardSize; j++) {
                libsChecked[i][j] = false;
            }
        }
    }
    
    private int clearGroupInternally(BoardPiece p, int x, int y) {
        int captures = 1;
        
        if(x >= 0 && x < boardSize && y >= 0 && y < boardSize && boardState[x][y] == p) {
            if(libsChecked[x][y]) {
                return 0;
            }
            
            boardState[x][y] = BoardPiece.EMPTY;
            libsChecked[x][y] = true;
            
            captures += this.clearGroupInternally(p, x+1, y);
            captures += this.clearGroupInternally(p, x-1, y);
            captures += this.clearGroupInternally(p, x, y+1);
            captures += this.clearGroupInternally(p, x, y-1);
        }
        else {
            //System.err.println("Clearing group out of the board bounds.");
            
            return 0;
        }
        
        return captures;
    }
    
    private int countLibertiesInternally(BoardPiece p, int x, int y) {
        int liberties = 0;
        
        if(x >= 0 && x < boardSize && y >= 0 && y < boardSize) {
            if(libsChecked[x][y]) {
                return 0;
            }
            
            libsChecked[x][y] = true;
            
            if(boardState[x][y] == BoardPiece.EMPTY) {
                liberties++;
            }
            else if(boardState[x][y] == p) {
                liberties += this.countLibertiesInternally(p, x+1, y);
                liberties += this.countLibertiesInternally(p, x-1, y);
                liberties += this.countLibertiesInternally(p, x, y+1);
                liberties += this.countLibertiesInternally(p, x, y-1);
            }
        }
        else {
            //System.err.println("Clearing piece out of the board bounds.");
        }
        
        return liberties;
    }
    
    @Override
    public int countTerritory(BoardPiece p) {
        int terr = 0;
        
        for(int i=0; i<boardSize; i++) {
            for(int j=0; j<boardSize; j++) {
                if(this.isTerritory(p, i, j) && boardState[i][j] == BoardPiece.EMPTY) {
                    territoryState[i][j] = p;
                    
                    terr++;
                }
            }
        }
        
        return terr;
    }
    
    private int getAdjacentStonesCount(BoardPiece p, int i, int j) {
        if(libsChecked[i][j]) {
            return 0;
        }
        
        libsChecked[i][j] = true;
        
        if(boardState[i][j] == p) {
            return 0;
        }
        else if(boardState[i][j] == BoardPiece.EMPTY) {
            int terr = 0;
            if(i-1>=0) terr += this.getAdjacentStonesCount(p, i-1, j);
            if(i+1<boardSize) terr += this.getAdjacentStonesCount(p, i+1, j);
            if(j+1<boardSize) terr += this.getAdjacentStonesCount(p, i, j+1);
            if(j-1>=0) terr += this.getAdjacentStonesCount(p, i, j-1);
            
            return terr;
        }
        else {
            return 1;
        }
    }
    
    private boolean isTerritory(BoardPiece p, int i, int j) {
        if(this.getAdjacentStonesCount(p, i, j) == 0) {
            this.clearLibertyCheckMatrix();
            return true;
        }
        this.clearLibertyCheckMatrix();
        return false;
    }
}
