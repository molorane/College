/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao;

import com.molorane.college.model.Institution;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Mothusi Molorane
 */
public abstract class InstitutionDao {
    
    public abstract int AddInstitution(String institution);
    public abstract int EditInstitution(Institution institution);
    public abstract int RemoveInstitution(int institutionId);    
    public abstract Institution GetInstitution(int institutionId);    
    public abstract ArrayList<Institution> GetAllInstitutions();
    
    // INSTITUTION CONVERSION METHODS
    protected Institution GetInstitutionDetailsFromResultSet(ResultSet rs) throws SQLException{
        Institution institution = new Institution();
        institution.setInstitution(rs.getInt("institutionId"), 
                        rs.getString("institution"));
        return institution;
    }
    
    protected ArrayList<Institution> GetInstitutionDetailsCollectionFromResultSet(ResultSet rs) throws SQLException{
       ArrayList<Institution> institutions = new ArrayList<>();
        while (rs.next())
            institutions.add(GetInstitutionDetailsFromResultSet(rs));
        return institutions;
    }
}
