/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package board;

import com.mortennobel.imagescaling.ResampleOp;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 *
 * @author Fabiano
 */
public class JBoard extends javax.swing.JPanel
                    implements IJBoard,
                               MouseListener,
                               MouseMotionListener,
                               ComponentListener {
    //Textures
    Image boardTexture;
    Image blackStoneTexture;
    Image whiteStoneTexture;
    Image originalBlackStoneTexture;
    Image originalWhiteStoneTexture;
    
    //Attributes
    int lastX;
    int lastY;
    IBoard board;
    Point mouseCoords;
    BoardPiece playerTurn;
    BoardPiece deadGroups[][];
    
    //Flags
    boolean showTerritoryCount;
    boolean showPreviewStone;
    boolean showTextures;
    boolean isCounting;
    boolean isPlaying;
    
    //Listeners
    List<JBoardListener> listeners;
    
    public JBoard() {
        super();
        
        listeners = new ArrayList<>();
        
        lastX = -1;
        lastY = -1;
        
        showTerritoryCount = false;
        showPreviewStone = false;
        showTextures = true;
        isCounting = false;
        isPlaying = true;
        
        playerTurn = BoardPiece.BLACK_STONE;
        
        board = new Board();
        
        deadGroups = new BoardPiece[board.getSize()][board.getSize()];
        
        for(int i=0; i<board.getSize(); i++) {
            for(int j=0; j<board.getSize(); j++) {
                deadGroups[i][j] = BoardPiece.EMPTY;
            }
        }
        
        mouseCoords = new Point(0, 0);
        
        try {
            this.initTextures();
        }
        catch(Exception e) {
        }
        
        this.setLayout(null);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addComponentListener(this);
        this.setOpaque(false);
    }
    
    public void addListener(JBoardListener l) {
        listeners.add(l);
    }
    
    public Dimension getBoardDimension() {
        return new Dimension((board.getSize()+1)*this.getBoardGap(), (board.getSize()+1)*this.getBoardGap());
    }
    
    public void setCounting(boolean counting) {
        isCounting = counting;
    }
    
    public boolean isCounting() {
        return isCounting;
    }
    
    public void setShowPreviewStone(boolean b) {
        showPreviewStone = b;
    }
    
    public void setPlayerTurn(BoardPiece p) {
        playerTurn = p;
    }
    
    public BoardPiece getPlayerTurn() {
        return playerTurn;
    }
    
    public int makeMove(BoardPiece p, int x, int y) {
        int stat = board.makeMove(p, x, y);
        
        if(stat != -1) {
            lastX = x;
            lastY = y;
        }
        
        return stat;
    }
    
    public void addPiece(BoardPiece p, int x, int y) {
        board.add(p, x, y);
    }
    
    public void clear(int x, int y) {
        board.clear(x, y);
    }
    
    public int clearGroup(BoardPiece p, int x, int y) {
        return board.clearGroup(p, x, y);
    }
    
    public int countTerritory(BoardPiece p) {
        return board.countTerritory(p);
    }
    
    public void setShowTerritory(boolean show) {
        showTerritoryCount = show;
    }
    
    public void setLastMoveMark(int x, int y) {
        lastX = x;
        lastY = y;
    }
        
    @Override
    public void mouseClicked(MouseEvent e) {
        Point p = this.mouseToBoardCoords(e.getX(), e.getY());
        
        if(!(p.x >= 0 && p.x < board.getSize() && p.y >= 0 && p.y < board.getSize() && !isCounting)) {
            p.x = -1;
            p.y = -1;
        }
        
        for(JBoardListener l:listeners) {
            l.onClick(e, p.x, p.y);
        }
        
        this.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseCoords = this.mouseToBoardCoords(e.getPoint().x, e.getPoint().y);
        
        this.repaint();
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        this.drawBoard(g2d, 0, 0, this.getWidth(), this.getHeight());
        this.drawStars(g2d);
        
        if(showTextures) {
            this.drawStoneShadows(g2d);
        }
        
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        for(int i=0; i<board.getSize(); i++) {
            for(int j=0; j<board.getSize(); j++) {
                if(board.getState(i, j) == BoardPiece.BLACK_STONE || board.getState(i, j) == BoardPiece.WHITE_STONE) {
                    this.drawPiece(g2d, board.getState(i, j), i, j, this.getWidth(), this.getHeight());
                }
            }
        }
        
        this.drawLastMoveMark(g2d, lastX, lastY, this.getWidth(), this.getHeight());
        
        if(showPreviewStone  && !isCounting &&
           board.getState(mouseCoords.x, mouseCoords.y) != BoardPiece.BLACK_STONE &&
           board.getState(mouseCoords.x, mouseCoords.y) != BoardPiece.WHITE_STONE) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .75f));
            this.drawPiece(g2d, playerTurn, mouseCoords.x, mouseCoords.y, this.getWidth(), this.getHeight());
        }
        
        if(showTerritoryCount) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            this.drawTerritory(g2d);
        }
    }
    
    @Override
    public void setBoard(IBoard b) {
        board = b;
    }
    
    @Override
    public IBoard getBoard() {
        return board;
    }

    @Override
    public void drawBoard(Graphics2D g, int x, int y, int w, int h) {
        int gap = this.getBoardGap();
        
        if(showTextures) {
            g.drawImage(boardTexture, 0, 0, (board.getSize()+1)*gap, (board.getSize()+1)*gap, this);
        }
        
        g.setColor(Color.BLACK);
        
        for(int i=0; i<board.getSize(); i++) {
            g.drawLine(gap+i*gap, gap, gap+i*gap, gap+(board.getSize()-1)*gap);
            g.drawLine(gap, gap+i*gap, gap+(board.getSize()-1)*gap, gap+i*gap);
        }
    }

    @Override
    public void drawPiece(Graphics2D g, BoardPiece p, int x, int y, int w, int h) {
        int gap = this.getBoardGap();
        int off = gap/2;
        
        if(showTextures) {
            switch(p) {
            case BLACK_STONE:
                g.drawImage(blackStoneTexture, off+x*gap, off+y*gap, this);
                break;
            case WHITE_STONE:
                g.drawImage(whiteStoneTexture, off+x*gap, off+y*gap, this);
                break;
            }
        }
        else {
            g.setStroke(new BasicStroke(gap/15));

            switch(p) {
            case BLACK_STONE:
                g.setColor(Color.BLACK);
                break;
            case WHITE_STONE:
                g.setColor(Color.WHITE);
                break;
            }

            g.fillOval(off+x*gap, off+y*gap, gap, gap);

            g.setColor(Color.BLACK);
            g.drawOval(off+x*gap, off+y*gap, gap, gap);
        }
    }
    
    @Override
    public void drawLastMoveMark(Graphics2D g, int x, int y, int w, int h) {
        if(x >= 0 && y >= 0) {
            if(board.getState(x, y) == BoardPiece.BLACK_STONE) {
                g.setColor(Color.WHITE);
            }
            else if(board.getState(x, y) == BoardPiece.WHITE_STONE) {
                g.setColor(Color.BLACK);
            }
            
            g.setStroke(new BasicStroke(this.getBoardGap()/8));
            g.drawOval(x*this.getBoardGap()+(int)(0.75f*this.getBoardGap()), y*this.getBoardGap()+(int)(0.75f*this.getBoardGap()), this.getBoardGap()/2, this.getBoardGap()/2);
        }
    }
    
    @Override
    public void drawTerritory(Graphics2D g) {
        int gap = this.getBoardGap();
        
        for(int i=0; i<board.getSize(); i++) {
            for(int j=0; j<board.getSize(); j++) {
                if(board.getTerritoryMatrix()[i][j] == BoardPiece.BLACK_STONE) {
                    g.setColor(Color.BLACK);
                    g.fillOval(i*gap+gap/2+gap/4, j*gap+gap/2+gap/4, gap/2, gap/2);
                }
                else if(board.getTerritoryMatrix()[i][j] == BoardPiece.WHITE_STONE) {
                    g.setColor(Color.WHITE);
                    g.fillOval(i*gap+gap/2+gap/4, j*gap+gap/2+gap/4, gap/2, gap/2);
                }
            }
        }
    }

    @Override
    public void componentResized(ComponentEvent e) {
        this.setSize(e.getComponent().getWidth(), e.getComponent().getHeight());
        this.resizeTextures(this.getBoardGap(), this.getBoardGap());
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }
    
    private void drawStoneShadows(Graphics2D g) {
        int gap = this.getBoardGap();
        int off = gap/2;
        int shadowsOff = gap/15;
        
        for(int i=0; i<board.getSize(); i++) {
            for(int j=0; j<board.getSize(); j++) {
                switch(board.getState(i,j)) {
                case BLACK_STONE:
                case WHITE_STONE:
                    g.setColor(Color.BLACK);
                    g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .25f));
                    g.fillOval(off-shadowsOff+i*gap, off+shadowsOff+j*gap, gap, gap);
                    break;
                }
            }
        }
    }
    
    private void drawStars(Graphics2D g) {
        int m = (board.getSize()+1)/2;
        int offset = (int)Math.ceil((board.getSize()-m)/2.0);
        int s = this.getBoardGap()/5;
        
        if(m%2== 0) {
            offset++;
        }
        
        int l = m-offset;
        int r = m+offset;
        
        l = l*this.getBoardGap()-s/2;
        m = m*this.getBoardGap()-s/2;
        r = r*this.getBoardGap()-s/2;
                
        g.fillOval(l, l, s, s);
        g.fillOval(m, l, s, s);
        g.fillOval(r, l, s, s);
        g.fillOval(l, m, s, s);
        g.fillOval(m, m, s, s);
        g.fillOval(r, m, s, s);
        g.fillOval(l, r, s, s);
        g.fillOval(m, r, s, s);
        g.fillOval(r, r, s, s);
    }
    
    private int getBoardGap() {
        int w = this.getWidth();
        int h = this.getHeight();
        
        if(h <= w) {
            return h/(board.getSize()+1);
        }
        else {
            return w/(board.getSize()+1);
        }
    }
     
    private void initTextures() throws Exception {
        boardTexture = ImageIO.read(new java.net.URL(getClass().getResource("resource/board2.png"), "board2.png"));
        blackStoneTexture = ImageIO.read(new java.net.URL(getClass().getResource("resource/bstone.png"), "bstone.png"));
        whiteStoneTexture = ImageIO.read(new java.net.URL(getClass().getResource("resource/wstone.png"), "wstone.png"));
        originalBlackStoneTexture = ImageIO.read(new java.net.URL(getClass().getResource("resource/bstone.png"), "bstone.png"));
        originalWhiteStoneTexture = ImageIO.read(new java.net.URL(getClass().getResource("resource/wstone.png"), "wstone.png"));
        
        ResampleOp resampler = new ResampleOp(this.getBoardGap(), this.getBoardGap());
        blackStoneTexture = resampler.filter((BufferedImage)blackStoneTexture, null);
        whiteStoneTexture = resampler.filter((BufferedImage)whiteStoneTexture, null);
        
        resampler = new ResampleOp(this.getWidth(), this.getHeight());
        boardTexture = resampler.filter((BufferedImage)boardTexture, null);
    }
    
    private void resizeTextures(int w, int h) {
        ResampleOp resampler = new ResampleOp(this.getBoardGap(), this.getBoardGap());
        blackStoneTexture = resampler.filter((BufferedImage)originalBlackStoneTexture, null);
        whiteStoneTexture = resampler.filter((BufferedImage)originalWhiteStoneTexture, null);
    }
    
    private Point mouseToBoardCoords(int x, int y) {
        int gap = this.getBoardGap();
        
        double dist = Integer.MAX_VALUE;
        Point p = new Point();
        
        for(int i=0; i<=board.getSize(); i++) {
            for(int j=0; j<=board.getSize(); j++) {
                Point tmp = new Point(i*gap, j*gap);
                if(tmp.distance(x, y) <= dist && j-1>=0 && i-1>=0) {
                    dist = tmp.distance(x, y);
                    p.x = i-1;
                    p.y = j-1;
                }
            }
        }
        
        return p;
    }

    private void iterateTurns() {
        if(playerTurn == BoardPiece.BLACK_STONE) {
            playerTurn = BoardPiece.WHITE_STONE;
        }
        else {
            playerTurn = BoardPiece.BLACK_STONE;
        }
    }
}
