/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package board;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author Fabiano
 */
public interface JBoardListener {
    public void onClick(MouseEvent e);
    public void onKeyPress(KeyEvent e);
    public void onKeyRelease(KeyEvent e);
}
