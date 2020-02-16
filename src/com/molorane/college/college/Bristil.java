/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.college;

import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author Mothusi Molorane
 */
public class Bristil extends ICollege{

    public Bristil() {
        centreNo = "0899992885";
        centreName = "BRISTIL";
    }
    
    @Override
    public Color pnlBackgroundSideMenu(){
        return new Color(54, 33, 89);
    }
    
    @Override
    public Color pnlBackgroundTop(){
        return new Color(122, 72, 221);
    }
    
    @Override
    public Color pnlBackgroundBottom(){
        return new Color(122, 72, 221);
    }
    
    @Override
    public Color activeMenu(){
        return new Color(64,43,100); 
    }
    
    @Override
    public Color setFontColor(){
        return new Color(204,204,204);
    }
    
    @Override
    public void setColor(JPanel panel){
        panel.setBackground(new Color(64,43,100));
    }
    
    @Override
    public void resetColor(JPanel panel){
       panel.setBackground(new Color(54,33,89)); 
    }
    
    @Override
    public String getCollegeName(){
        return "BRISTIL COLLEGE";
    }
    
}
