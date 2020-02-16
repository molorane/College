/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.view.Controls;

import com.molorane.college.custom.Functions;
import com.molorane.college.custom.NotifyData;
import com.molorane.college.custom.PanelNotify;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Mothusi Molorane
 */
public class Alert extends javax.swing.JPanel {

    /**
     * Creates new form Alert
     */
    
    private boolean isVisible;
    
    public Alert() {
        initComponents();
        close();
    }
    
    public void notify(String msg,int state){
        if(!isVisible){
            isVisible = true;
            setVisible(isVisible);
        }
        NotifyData nt = new NotifyData();
        nt.setNotifyData(pnlNotify, lblMSGNotify, lblIMGNotify, msg, state);
        jpnNotifySlider.notify(pnlNotify, nt); 
    }
    
    public final void close(){
        isVisible = false;
        setVisible(isVisible);
    }
    
    public PanelNotify getJPanelSlider(){
        return jpnNotifySlider;
    }
    
    public JPanel getpnlNotify(){
        return pnlNotify;
    }
    
    public JLabel getlblMSGNotify(){
        return lblMSGNotify;
    }
    
    public JLabel getlblIMGNotify(){
        return lblIMGNotify;
    }
    
    public void setNoDisplay(){
        lblMSGNotify.setText("");
        jpnNotifySlider.noDisplay(pnlNoDisplay);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnNotifySlider = new com.molorane.college.custom.PanelNotify();
        pnlNoDisplay = new javax.swing.JPanel();
        pnlNotify = new javax.swing.JPanel();
        lblMSGNotify = new javax.swing.JLabel();
        lblIMGNotify = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setBackground(Functions.pnlBackgroundSideMenu());
        setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jpnNotifySlider.setBackground(Functions.pnlBackgroundSideMenu());
        jpnNotifySlider.setBorder(null);

        pnlNoDisplay.setBackground(Functions.pnlBackgroundSideMenu());

        javax.swing.GroupLayout pnlNoDisplayLayout = new javax.swing.GroupLayout(pnlNoDisplay);
        pnlNoDisplay.setLayout(pnlNoDisplayLayout);
        pnlNoDisplayLayout.setHorizontalGroup(
            pnlNoDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 461, Short.MAX_VALUE)
        );
        pnlNoDisplayLayout.setVerticalGroup(
            pnlNoDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 41, Short.MAX_VALUE)
        );

        jpnNotifySlider.add(pnlNoDisplay, "card2");

        pnlNotify.setBackground(Functions.pnlBackgroundBottom());
        pnlNotify.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlNotifyMouseClicked(evt);
            }
        });

        lblMSGNotify.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        lblIMGNotify.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout pnlNotifyLayout = new javax.swing.GroupLayout(pnlNotify);
        pnlNotify.setLayout(pnlNotifyLayout);
        pnlNotifyLayout.setHorizontalGroup(
            pnlNotifyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNotifyLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lblMSGNotify, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblIMGNotify, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        pnlNotifyLayout.setVerticalGroup(
            pnlNotifyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNotifyLayout.createSequentialGroup()
                .addGroup(pnlNotifyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblIMGNotify, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(lblMSGNotify, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jpnNotifySlider.add(pnlNotify, "card2");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/close.png"))); // NOI18N
        jButton1.setToolTipText("Close panel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jpnNotifySlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jpnNotifySlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(2, 2, 2))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void pnlNotifyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlNotifyMouseClicked
        // TODO add your handling code here:
        setNoDisplay();
    }//GEN-LAST:event_pnlNotifyMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        setNoDisplay();
        close();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private com.molorane.college.custom.PanelNotify jpnNotifySlider;
    private javax.swing.JLabel lblIMGNotify;
    private javax.swing.JLabel lblMSGNotify;
    private javax.swing.JPanel pnlNoDisplay;
    private javax.swing.JPanel pnlNotify;
    // End of variables declaration//GEN-END:variables
}
