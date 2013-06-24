/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import board.JBoard;
import board.JBoardListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author Fabiano
 */
public class BoardFrame extends javax.swing.JFrame implements JBoardListener,
                                                              ComponentListener {
    protected JBoard board;
    
    public BoardFrame() {
        initComponents();
        
        bPlayerPanel.setTextColor(Color.WHITE);
        
        board = new JBoard();
        board.addListener(this);
        
        boardContainerPanel.add(board);
        
        this.rescaleBoard();
        this.addComponentListener(this);
        this.pack();
    }
    
    public void setControlPanelListener(ControlPanelListener l) {
        controlPanel.setListener(l);
    }
    
    private void rescaleBoard() {
        int w = boardContainerPanel.getWidth();
        int h = boardContainerPanel.getHeight();
        Dimension b = board.getBoardDimension();
        
        board.setSize(w, h);
        board.setLocation((w-b.width)/2, (h-b.width)/2);
        
        this.pack();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        boardContainerPanel = new javax.swing.JPanel();
        menuContainerPanel = new javax.swing.JPanel();
        wPlayerPanel = new gui.PlayerPanel();
        controlPanel = new gui.ControlPanel();
        bPlayerPanel = new gui.PlayerPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        boardContainerPanel.setBackground(new java.awt.Color(0, 26, 0));
        boardContainerPanel.setLayout(null);

        wPlayerPanel.setBackground(new java.awt.Color(255, 255, 255));
        wPlayerPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        bPlayerPanel.setBackground(new java.awt.Color(32, 32, 32));
        bPlayerPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        bPlayerPanel.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout menuContainerPanelLayout = new javax.swing.GroupLayout(menuContainerPanel);
        menuContainerPanel.setLayout(menuContainerPanelLayout);
        menuContainerPanelLayout.setHorizontalGroup(
            menuContainerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuContainerPanelLayout.createSequentialGroup()
                .addComponent(wPlayerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(controlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bPlayerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        menuContainerPanelLayout.setVerticalGroup(
            menuContainerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(wPlayerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
            .addComponent(controlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bPlayerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(boardContainerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 853, Short.MAX_VALUE)
            .addComponent(menuContainerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(boardContainerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuContainerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.PlayerPanel bPlayerPanel;
    private javax.swing.JPanel boardContainerPanel;
    private gui.ControlPanel controlPanel;
    private javax.swing.JPanel menuContainerPanel;
    private gui.PlayerPanel wPlayerPanel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onClick(MouseEvent e, int x, int y) {
    }

    @Override
    public void componentResized(ComponentEvent e) {
        this.rescaleBoard();
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        this.rescaleBoard();
    }

    @Override
    public void componentShown(ComponentEvent e) {
        this.rescaleBoard();
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }
}
