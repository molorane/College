/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao;

import com.nanoware.model.Education;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class EducationDao {
    
    public abstract int AddEducation(Education education);
    public abstract int EditEducation(Education education);
    public abstract int RemoveEducation(int eduId);    
    public abstract Education GetEducation(int eduId);
    public abstract ArrayList<Education> GetPersonEducation(long personId);
    
    // COURSE CONVERSION METHODS
    protected Education GetEducationDetailsFromResultSet(ResultSet rs) throws SQLException{
        Education education = new Education();
        education.setEducation(rs.getInt("eduId"), 
                        rs.getLong("personId"),
                        rs.getInt("institutionId"),
                        rs.getInt("qualificationId"),
                        rs.getInt("yearObtained"),
                        rs.getString("skills"), 
                        rs.getString("details"),
                        rs.getString("institution"),
                        rs.getString("qualification"));
        return education;
    }
    
    protected ArrayList<Education> GetEducationDetailsCollectionFromResultSet(ResultSet rs) throws SQLException{
       ArrayList<Education> educations = new ArrayList<>();
        while (rs.next())
            educations.add(GetEducationDetailsFromResultSet(rs));
        return educations;
    }
}
