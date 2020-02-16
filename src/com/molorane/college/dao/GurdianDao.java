/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao;

import com.molorane.college.model.Gurdian;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class GurdianDao {
    
    public abstract int AddGurdian(Gurdian gurdian);
    public abstract int EditGurdian(Gurdian gurdian);
    public abstract int RemoveGurdian(long personId);    
    public abstract Gurdian GetGurdian(long personId);   
    
    // GURDIAN CONVERSION METHODS
    protected Gurdian GetGurdianDetailsFromResultSet(ResultSet rs) throws SQLException{
        Gurdian gurdian = new Gurdian();
        gurdian.setGurdian(rs.getLong("personId"), 
                        rs.getString("lastName"), 
                        rs.getString("firstName"), 
                        rs.getInt("relationshipId"),
                        rs.getString("telephone"),
                        rs.getString("cellphone"),
                        rs.getString("address"),
                        rs.getString("postalCode"),
                        rs.getString("relationship"));
        return gurdian;
    }
}
