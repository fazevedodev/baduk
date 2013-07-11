/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Fabiano
 */
public class SettingsFrame extends javax.swing.JFrame {
    Image boardTextures[];
    SettingsFrameListener listener;
    
    /**
     * Creates new form SettingsFrame
     */
    public SettingsFrame() {
        initComponents();
        
        boardTextures = new Image[5];
        
        try {
            this.initTextures();
        }
        catch(Exception e) {
            
        }
    }
    
    public void setListener(SettingsFrameListener l) {
        listener = l;
    }
    
    private void initTextures() throws IOException {
        boardTextures[0] = ImageIO.read(new File("resources/goban7.png"));
        boardTextures[1] = ImageIO.read(new File("resources/goban9.png"));
        boardTextures[2] = ImageIO.read(new File("resources/goban11.png"));
        boardTextures[3] = ImageIO.read(new File("resources/goban15.png"));
        boardTextures[4] = ImageIO.read(new File("resources/goban16.png"));
        
        boardTex1.setImage(boardTextures[0]);
        boardTex2.setImage(boardTextures[1]);
        boardTex3.setImage(boardTextures[2]);
        boardTex4.setImage(boardTextures[3]);
        boardTex5.setImage(boardTextures[4]);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        useTexturesCheckBox = new javax.swing.JCheckBox();
        showCoordinatesCheckBox = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        boardTexturesTabbedPane = new javax.swing.JTabbedPane();
        boardTex1 = new gui.ImagePanel();
        boardTex2 = new gui.ImagePanel();
        boardTex3 = new gui.ImagePanel();
        boardTex4 = new gui.ImagePanel();
        boardTex5 = new gui.ImagePanel();
        saveButon = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Settings");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Graphics"));

        useTexturesCheckBox.setText("Use textures");

        showCoordinatesCheckBox.setText("Show coordinates");

        jLabel1.setText("Select board texture:");

        javax.swing.GroupLayout boardTex1Layout = new javax.swing.GroupLayout(boardTex1);
        boardTex1.setLayout(boardTex1Layout);
        boardTex1Layout.setHorizontalGroup(
            boardTex1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 419, Short.MAX_VALUE)
        );
        boardTex1Layout.setVerticalGroup(
            boardTex1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 404, Short.MAX_VALUE)
        );

        boardTexturesTabbedPane.addTab("Board 1", boardTex1);

        javax.swing.GroupLayout boardTex2Layout = new javax.swing.GroupLayout(boardTex2);
        boardTex2.setLayout(boardTex2Layout);
        boardTex2Layout.setHorizontalGroup(
            boardTex2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 419, Short.MAX_VALUE)
        );
        boardTex2Layout.setVerticalGroup(
            boardTex2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 404, Short.MAX_VALUE)
        );

        boardTexturesTabbedPane.addTab("Board 2", boardTex2);

        javax.swing.GroupLayout boardTex3Layout = new javax.swing.GroupLayout(boardTex3);
        boardTex3.setLayout(boardTex3Layout);
        boardTex3Layout.setHorizontalGroup(
            boardTex3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 419, Short.MAX_VALUE)
        );
        boardTex3Layout.setVerticalGroup(
            boardTex3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 404, Short.MAX_VALUE)
        );

        boardTexturesTabbedPane.addTab("Board 3", boardTex3);

        javax.swing.GroupLayout boardTex4Layout = new javax.swing.GroupLayout(boardTex4);
        boardTex4.setLayout(boardTex4Layout);
        boardTex4Layout.setHorizontalGroup(
            boardTex4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 419, Short.MAX_VALUE)
        );
        boardTex4Layout.setVerticalGroup(
            boardTex4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 404, Short.MAX_VALUE)
        );

        boardTexturesTabbedPane.addTab("Board 4", boardTex4);

        javax.swing.GroupLayout boardTex5Layout = new javax.swing.GroupLayout(boardTex5);
        boardTex5.setLayout(boardTex5Layout);
        boardTex5Layout.setHorizontalGroup(
            boardTex5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 419, Short.MAX_VALUE)
        );
        boardTex5Layout.setVerticalGroup(
            boardTex5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 404, Short.MAX_VALUE)
        );

        boardTexturesTabbedPane.addTab("Board 5", boardTex5);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(boardTexturesTabbedPane)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(useTexturesCheckBox)
                            .addComponent(showCoordinatesCheckBox)
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(useTexturesCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showCoordinatesCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boardTexturesTabbedPane)
                .addContainerGap())
        );

        saveButon.setText("Save");
        saveButon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cancelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveButon)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButon)
                    .addComponent(cancelButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void saveButonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButonActionPerformed
        ImagePanel ip = (ImagePanel)boardTexturesTabbedPane.getSelectedComponent();
        
        listener.onSettingsSaved(ip.getImage(), boardTexturesTabbedPane.getSelectedIndex(), useTexturesCheckBox.isSelected(), showCoordinatesCheckBox.isSelected());
        
        this.dispose();
    }//GEN-LAST:event_saveButonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.ImagePanel boardTex1;
    private gui.ImagePanel boardTex2;
    private gui.ImagePanel boardTex3;
    private gui.ImagePanel boardTex4;
    private gui.ImagePanel boardTex5;
    private javax.swing.JTabbedPane boardTexturesTabbedPane;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton saveButon;
    private javax.swing.JCheckBox showCoordinatesCheckBox;
    private javax.swing.JCheckBox useTexturesCheckBox;
    // End of variables declaration//GEN-END:variables
}
