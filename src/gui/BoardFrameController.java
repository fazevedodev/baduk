/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.event.*;

/**
 *
 * @author Fabiano
 */
public abstract class BoardFrameController implements KeyListener,
                                                      MouseListener,
                                                      ComponentListener,
                                                      ControlPanelListener {
    protected BoardFrame frame;
    
    public void setBoardFrame(BoardFrame f) {
        frame = f;
        frame.addComponentListener(this);
        frame.setControlPanelListener(this);
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
    
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
    @Override
    public void componentResized(ComponentEvent e) {
        frame.rescaleBoard();
    }

    @Override
    public void componentMoved(ComponentEvent e) {}

    @Override
    public void componentShown(ComponentEvent e) {}

    @Override
    public void componentHidden(ComponentEvent e) {}
    
    @Override
    public void onBackAllButtonClick() {
        
    }
    
    @Override
    public void onBackButtonClick() {
        
    }
    
    @Override
    public void onForwardAllButtonClick() {
        
    }
    
    @Override
    public void onForwardButtonClick() {
        
    }
}
