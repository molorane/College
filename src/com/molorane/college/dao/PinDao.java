/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao;

import com.molorane.college.model.Pin;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class PinDao {
    
    public abstract int AddPin(Pin pin);
    public abstract int EditPin(Pin pin);
    public abstract int RemovePin(int personId);    
    public abstract Pin GetPin(int personId);    
    public abstract boolean validatePin(String pin);
    public abstract boolean IsAdminPinValid(int personId, String pin);  
    public abstract boolean updateAdminPin(int personId, String pin); 
    
    // PIN CONVERSION METHODS
    protected Pin GetPinDetailsFromResultSet(ResultSet rs) throws SQLException{
        Pin pin = new Pin();
        pin.setPin(Integer.parseInt(rs.getString("personId")),
                rs.getString("pin"));
        return pin;
    }
    
    protected ArrayList<Pin> GetPinDetailsCollectionFromResultSet(ResultSet rs) throws SQLException{
       ArrayList<Pin> pins = new ArrayList<>();
        while (rs.next())
            pins.add(GetPinDetailsFromResultSet(rs));
        return pins;
    }
}
