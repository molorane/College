/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao;

import com.nanoware.model.EnrolmentDateline;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class EnrolmentDatelineDao {
    
    public abstract int AddEnrolmentDateline(EnrolmentDateline enrolmentDateline);
    public abstract int EditEnrolmentDateline(EnrolmentDateline enrolmentDateline);
    public abstract int RemoveEnrolmentDateline(int termId);    
    public abstract EnrolmentDateline GetEnrolmentDateline(int termId); 
    public abstract boolean isEnrolmentBeforeDateline(int termId);
    public abstract boolean isEnrolmentBeforeDateline2(String term);
    public abstract ArrayList<EnrolmentDateline> GetAllEnrolmentDatelines();
    
    // DURATION CONVERSION METHODS
    public EnrolmentDateline GetEnrolmentDatelineDetailsFromResultSet(ResultSet rs) throws SQLException{
        EnrolmentDateline enrolmentDateline = new EnrolmentDateline();
        enrolmentDateline.setEnrolmentDateline(rs.getInt("termId"), 
                        rs.getString("dateline"),
                        rs.getString("term"));
        return enrolmentDateline;
    }
    
    public ArrayList<EnrolmentDateline> GetEnrolmentDatelineDetailsCollectionFromResultSet(ResultSet rs) throws SQLException{
       ArrayList<EnrolmentDateline> enrolmentDatelines = new ArrayList<>();
        while (rs.next())
            enrolmentDatelines.add(GetEnrolmentDatelineDetailsFromResultSet(rs));
        return enrolmentDatelines;
    }
}
