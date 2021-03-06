/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author Fabiano
 */
public class ControlPanel extends javax.swing.JPanel {
    ControlPanelListener listener;
    
    /**
     * Creates new form ControlPanel
     */
    public ControlPanel() {
        initComponents();
    }
    
    public void setListener(ControlPanelListener l) {
        listener = l;
    }
    
    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        
        backAllButton.setEnabled(enabled);
        forwardAllButton.setEnabled(enabled);
        backOnceButton.setEnabled(enabled);
        forwardOnceButton.setEnabled(enabled);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        backAllButton = new javax.swing.JButton();
        backOnceButton = new javax.swing.JButton();
        forwardOnceButton = new javax.swing.JButton();
        forwardAllButton = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        backAllButton.setText("<<");
        backAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backAllButtonActionPerformed(evt);
            }
        });
        add(backAllButton, new java.awt.GridBagConstraints());

        backOnceButton.setText("<");
        backOnceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backOnceButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 0);
        add(backOnceButton, gridBagConstraints);

        forwardOnceButton.setText(">");
        forwardOnceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forwardOnceButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 0);
        add(forwardOnceButton, gridBagConstraints);

        forwardAllButton.setText(">>");
        forwardAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forwardAllButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 0);
        add(forwardAllButton, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void backAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backAllButtonActionPerformed
        listener.onBackAllButtonClick();
    }//GEN-LAST:event_backAllButtonActionPerformed

    private void backOnceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backOnceButtonActionPerformed
        listener.onBackButtonClick();
    }//GEN-LAST:event_backOnceButtonActionPerformed

    private void forwardOnceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forwardOnceButtonActionPerformed
        listener.onForwardButtonClick();
    }//GEN-LAST:event_forwardOnceButtonActionPerformed

    private void forwardAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forwardAllButtonActionPerformed
        listener.onForwardAllButtonClick();
    }//GEN-LAST:event_forwardAllButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backAllButton;
    private javax.swing.JButton backOnceButton;
    private javax.swing.JButton forwardAllButton;
    private javax.swing.JButton forwardOnceButton;
    // End of variables declaration//GEN-END:variables
}
