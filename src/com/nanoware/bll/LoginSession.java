/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll;

import com.nanoware.model.User;

/**
 *
 * @author Mothusi Molorane
 */
public class LoginSession {
    
    private static volatile User _instance;
    
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
        return _instance;
    }
}
