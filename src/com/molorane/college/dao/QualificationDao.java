/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao;

import com.molorane.college.model.Qualification;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class QualificationDao {
    
    public abstract int AddQualification(Qualification qualification);
    public abstract int EditQualification(Qualification qualification);
    public abstract int RemoveQualification(int qualificationId);    
    public abstract Qualification GetQualification(int qualificationId);    
    public abstract ArrayList<Qualification> GetAllQualifications();
    
    // QUALIFICATION CONVERSION METHODS
    protected Qualification GetQualificationDetailsFromResultSet(ResultSet rs) throws SQLException{
        Qualification qualification = new Qualification();
        qualification.setQualification(rs.getInt("qualificationId"), 
                        rs.getString("qualification"));
        return qualification;
    }
    
    protected ArrayList<Qualification> GetQualificationDetailsCollectionFromResultSet(ResultSet rs) throws SQLException{
       ArrayList<Qualification> qualifications = new ArrayList<>();
        while (rs.next())
            qualifications.add(GetQualificationDetailsFromResultSet(rs));
        return qualifications;
    }
}
