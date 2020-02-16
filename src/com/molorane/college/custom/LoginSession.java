/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.custom;

import com.molorane.college.model.User;
import javax.swing.JFrame;

/**
 * @author Mothusi Molorane
 */

public class LoginSession {
    
    private static volatile User _instance;
    private static JFrame dashboard;
    
    private LoginSession(){}
    
    public static void startSession(User user) {
        if (_instance == null) {
            synchronized (LoginSession.class) {
                if (_instance == null) {
                    _instance = user;
                }
            }
        }
    }
    
    public static User GetSessionUser(){
        //_instance = Functions.GetUser("admin");
        return _instance;
    }
    
    public static void destoySession(){
        _instance = null;
        closeDashboard();
    }
    
    public static void setDashboard(JFrame frm){
        dashboard = frm;
    }
    
    public static void closeDashboard(){
        if(dashboard != null){
            dashboard.dispose();
            dashboard = null;
        }
    }
}
