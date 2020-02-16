/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.custom;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Mothusi Molorane
 */
public class NotifyData {
    
    private JPanel jpn;
    private JLabel lblStatus;
    private JLabel imgStatus;
    private String msg;
    private int success;
    
    public void setNotifyData(JPanel jpn,JLabel lblStatus,JLabel imgStatus,String msg,int success){
        this.jpn = jpn;
        this.lblStatus = lblStatus;
        this.imgStatus = imgStatus;
        this.setMsg(msg);
        this.success = success;
    }
    
    public void init(){
        String img;
        Color background;
        Color border;
        Color lblColor;
        if(success == 1){
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
        imgStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/"+img)));
    }

    /**
     * @return the jpn
     */
    public JPanel getJpn() {
        return jpn;
    }

    /**
     * @param jpn the jpn to set
     */
    public void setJpn(JPanel jpn) {
        this.jpn = jpn;
    }

    /**
     * @return the lblStatus
     */
    public JLabel getLblStatus() {
        return lblStatus;
    }

    /**
     * @param lblStatus the lblStatus to set
     */
    public void setLblStatus(JLabel lblStatus) {
        this.lblStatus = lblStatus;
    }

    /**
     * @return the imgStatus
     */
    public JLabel getImgStatus() {
        return imgStatus;
    }

    /**
     * @param imgStatus the imgStatus to set
     */
    public void setImgStatus(JLabel imgStatus) {
        this.imgStatus = imgStatus;
    }

    /**
     * @return the success
     */
    public int isSuccess() {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(int success) {
        this.success = success;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    
    
}
