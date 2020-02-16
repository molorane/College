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
public abstract class ICollege {
    
    public abstract Color pnlBackgroundSideMenu();
    public abstract Color pnlBackgroundTop();
    public abstract Color pnlBackgroundBottom();
    public abstract Color activeMenu();
    public abstract Color setFontColor();
    public abstract void setColor(JPanel panel);
    public abstract void resetColor(JPanel panel);
    public abstract String getCollegeName();
    
    protected String centreNo;
    protected String centreName;
    
    public String appName(){
        return "The Student Portal 1.0";
    }
    
    public String designedDetails(){
        return "Copyright 2019 design for the purpose of education.";
    }
    
    public String dashboardTitle(){
        return "THE COLLEGE PORTAL";
    }
    
    public String getCentreNo(){
        return centreNo;
    }
    
    public String getCentreName(){
        return centreName;
    }
}
