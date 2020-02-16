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
public class Tshwane extends ICollege{

    public Tshwane() {
        centreNo = "0899998827";
        centreName = "TSHWANE";
    }

    @Override
    public Color pnlBackgroundSideMenu(){
        return new Color(222, 98, 95);
    }
    
    @Override
    public Color pnlBackgroundTop(){
        return new Color(222, 98, 95);
    }
    
    @Override
    public Color pnlBackgroundBottom(){
        return new Color(51,51,51);
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
       panel.setBackground(new Color(222, 98, 95)); 
    }
    
    @Override
    public String getCollegeName(){
        return "TSHWANE COLLEGE";
    }
    
}
