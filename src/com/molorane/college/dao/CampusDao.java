/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao;

import com.molorane.college.model.Campus;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class CampusDao {
    
    public abstract int AddCampus(Campus campus);
    public abstract int EditCampus(Campus campus);
    public abstract int RemoveCampus(int campusCode);    
    public abstract Campus GetCampus(int campusCode);    
    public abstract ArrayList<Campus> GetAllCampuses();
    
    // CAMPUS CONVERSION METHODS
    public Campus GetCampusDetailsFromResultSet(ResultSet rs) throws SQLException{
        Campus campus = new Campus();
        campus.setCampus(rs.getInt("campusCode"), 
                        rs.getString("campusName"), 
                        rs.getString("campusABR"), 
                        rs.getInt("isHQ"),
                        rs.getString("telephone"),
                        rs.getString("fax"),
                        rs.getString("address"),
                        rs.getString("email"));
        return campus;
    }
    
    public ArrayList<Campus> GetCampusDetailsCollectionFromResultSet(ResultSet rs) throws SQLException{
       ArrayList<Campus> campuses = new ArrayList<>();
        while (rs.next())
            campuses.add(GetCampusDetailsFromResultSet(rs));
        return campuses;
    }
}
