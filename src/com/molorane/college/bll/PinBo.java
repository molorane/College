/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll;

import com.molorane.college.model.Pin;

/**
 *
 * @author Mothusi Molorane
 */
public interface PinBo{
    
    public abstract int AddPin(Pin pin);
    public abstract int EditPin(Pin pin); 
    public abstract boolean validatePin(String pin);
    public abstract boolean IsAdminPinValid(int personId, String pin);  
    public abstract boolean updateAdminPin(int personId, String pin); 
}
