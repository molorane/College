/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.view;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Mothusi Molorane
 */
public class GUI {
    
    public void setNotification(JPanel jpn,JLabel lblStatus,JLabel imgStatus,boolean success){
        String img;
        Color background;
        Color border;
        Color lblColor;
        if(success){
            border = new Color(0,153,51);
            background = new Color(204,255,204);
            lblColor = new Color(51,51,51);
            img = "success.png";
        }else{
            border = new Color(255,0,0);
            background = new Color(255,204,204);
            lblColor = new Color(51,51,51);
            img = "error_delete.png";
        }
        jpn.setBorder(new javax.swing.border.LineBorder(border, 2, true));
        jpn.setBackground(background);
        lblStatus.setForeground(lblColor);
        imgStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/32x32/"+img)));
    }
}
