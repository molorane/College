/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao.impl;

import com.nanoware.bll.Functions;
import com.nanoware.dao.GradeDatelineDao;
import com.nanoware.model.GradeDateline;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public class GradeDatelineDaoImpl extends GradeDatelineDao{

    @Override
    public int AddGradeDateline(GradeDateline gradeDateline) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainGradeDateline(?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, gradeDateline.getTermId());
            cs.setString(2, gradeDateline.getDateline());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("MaintainGradeDateline error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int EditGradeDateline(GradeDateline gradeDateline) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainGradeDateline(?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, gradeDateline.getTermId());
            cs.setString(2, gradeDateline.getDateline());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("MaintainGradeDateline error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int RemoveGradeDateline(int termId) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "CALL RemoveGradeDateline(?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, termId);
            int count = pst.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("RemoveGradeDateline error: "+ e.getLocalizedMessage());
        }
        return 0;
    }

    @Override
    public GradeDateline GetGradeDateline(int termId) {
        try{
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetGradeDateline(?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, termId);
            rs = pst.executeQuery();
            if (rs.next()) {
                return GetGradeDatelineDetailsFromResultSet(rs);
            }
        } catch (SQLException e) {
            Functions.errorMessage("getTerm error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public boolean isSubmissionBeforeDateline(int termId) {
        try{
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL isSubmissionBeforeDateline(?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, termId);
            rs = pst.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            Functions.errorMessage("isSubmissionBeforeDateline error: "+ e.getLocalizedMessage());
        }    
        return false;
    }

    @Override
    public ArrayList<GradeDateline> GetAllGradeDatelines() {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetAllGradeDatelines()";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            return GetGradeDatelineDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getAllGradeDatelines error: "+ e.getLocalizedMessage());
        }
        return null;
    }
    
}
