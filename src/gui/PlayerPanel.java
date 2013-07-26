/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;

/**
 *
 * @author Fabiano
 */
public class PlayerPanel extends javax.swing.JPanel {

    /**
     * Creates new form PlayerPanel
     */
    public PlayerPanel() {
        initComponents();
    }
    
    public void setTextColor(Color c) {
        nameLabel.setForeground(c);
        capturesLabel.setForeground(c);
        rankLabel.setForeground(c);
    }
    
    public void setPlayerName(String s) {
        nameLabel.setText(s);
    }
    
    public void setPlayerRank(String s) {
        rankLabel.setText(s);
    }
    
    public void setCaptureCount(String s) {
        capturesLabel.setText(s);
    }
    
    public void setTime(String s) {
        timePanel.setText(s);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameLabel = new javax.swing.JLabel();
        capturesLabel = new javax.swing.JLabel();
        rankLabel = new javax.swing.JLabel();
        timePanel = new gui.TimePanel();

        setAlignmentX(0.0F);
        setAlignmentY(0.0F);
        setRequestFocusEnabled(false);

        nameLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        nameLabel.setText("Player Name");

        capturesLabel.setText("Captures");

        rankLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rankLabel.setText("Rank");

        timePanel.setAlignmentX(0.0F);
        timePanel.setAlignmentY(0.0F);
        timePanel.setMinimumSize(new java.awt.Dimension(20, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(timePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addComponent(rankLabel))
                    .addComponent(capturesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(rankLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(capturesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(timePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel capturesLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel rankLabel;
    private gui.TimePanel timePanel;
    // End of variables declaration//GEN-END:variables
}
