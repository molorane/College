/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao;

import com.nanoware.model.Religion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class ReligionDao {
    
    public abstract int AddReligion(Religion religion);
    public abstract int EditReligion(Religion religion);
    public abstract int RemoveReligion(int religionId);    
    public abstract Religion GetReligion(int religionId);    
    public abstract ArrayList<Religion> GetAllReligions();
    
    // RELIGION CONVERSION METHODS
    protected Religion GetReligionDetailsFromResultSet(ResultSet rs) throws SQLException{
        Religion religion = new Religion();
        religion.setReligion(rs.getInt("religionId"), 
                        rs.getString("religion"));
        return religion;
    }
    
    protected ArrayList<Religion> GetReligionDetailsCollectionFromResultSet(ResultSet rs) throws SQLException{
       ArrayList<Religion> religions = new ArrayList<>();
        while (rs.next())
            religions.add(GetReligionDetailsFromResultSet(rs));
        return religions;
    }
}
