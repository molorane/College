/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao;

import com.nanoware.model.Gender;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class GenderDao {
    
    public abstract int AddGender(Gender gender);
    public abstract int EditGender(Gender gender);
    public abstract int RemoveGender(int genderId);    
    public abstract Gender GetGender(int genderId);    
    public abstract ArrayList<Gender> GetAllGenders();
    
    // RACE CONVERSION METHODS
    protected Gender GetGenderDetailsFromResultSet(ResultSet rs) throws SQLException{
        Gender gender = new Gender();
        gender.setGender(rs.getInt("genderId"), 
                        rs.getString("gender"));
        return gender;
    }
    
    protected ArrayList<Gender> GetGenderDetailsCollectionFromResultSet(ResultSet rs) throws SQLException{
       ArrayList<Gender> genders = new ArrayList<>();
        while (rs.next())
            genders.add(GetGenderDetailsFromResultSet(rs));
        return genders;
    }
}
