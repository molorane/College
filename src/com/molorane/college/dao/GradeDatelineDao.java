/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao;

import com.molorane.college.model.GradeDateline;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class GradeDatelineDao {
    
    public abstract int AddGradeDateline(GradeDateline gradeDateline);
    public abstract int EditGradeDateline(GradeDateline gradeDateline);
    public abstract int RemoveGradeDateline(int termId);    
    public abstract GradeDateline GetGradeDateline(int termId); 
    public abstract boolean isSubmissionBeforeDateline(int termId);
    public abstract ArrayList<GradeDateline> GetAllGradeDatelines();
    
    // GradeDateline CONVERSION METHODS
    public GradeDateline GetGradeDatelineDetailsFromResultSet(ResultSet rs) throws SQLException{
        GradeDateline gradeDateline = new GradeDateline();
        gradeDateline.setGradeDateline(rs.getInt("termId"), 
                        rs.getString("dateline"),
                        rs.getString("term"));
        return gradeDateline;
    }
    
    public ArrayList<GradeDateline> GetGradeDatelineDetailsCollectionFromResultSet(ResultSet rs) throws SQLException{
       ArrayList<GradeDateline> gradeDatelines = new ArrayList<>();
        while (rs.next())
            gradeDatelines.add(GetGradeDatelineDetailsFromResultSet(rs));
        return gradeDatelines;
    }
}
