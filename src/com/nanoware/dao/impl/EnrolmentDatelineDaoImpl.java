/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao.impl;

import com.nanoware.bll.Functions;
import com.nanoware.dao.EnrolmentDatelineDao;
import com.nanoware.model.EnrolmentDateline;
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
public class EnrolmentDatelineDaoImpl extends EnrolmentDatelineDao{

    @Override
    public int AddEnrolmentDateline(EnrolmentDateline enrolmentDateline) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainEnrolmentDateline(?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, enrolmentDateline.getTermId());
            cs.setString(2, enrolmentDateline.getDateline());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("MaintainEnrolmentDateline error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int EditEnrolmentDateline(EnrolmentDateline enrolmentDateline) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainEnrolmentDateline(?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, enrolmentDateline.getTermId());
            cs.setString(2, enrolmentDateline.getDateline());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("MaintainEnrolmentDateline error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int RemoveEnrolmentDateline(int termId) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "CALL RemoveEnrolmentDateline(?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, termId);
            int count = pst.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("RemoveEnrolmentDateline error: "+ e.getLocalizedMessage());
        }
        return 0;
    }

    @Override
    public EnrolmentDateline GetEnrolmentDateline(int termId) {
        try{
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetEnrolmentDateline(?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, termId);
            rs = pst.executeQuery();
            if (rs.next()) {
                return GetEnrolmentDatelineDetailsFromResultSet(rs);
            }
        } catch (SQLException e) {
            Functions.errorMessage("getEnrolmentDateline error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public boolean isEnrolmentBeforeDateline(int termId) {
        try{
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL isEnrolmentBeforeDateline(?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, termId);
            rs = pst.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            Functions.errorMessage("isEnrolmentBeforeDateline error: "+ e.getLocalizedMessage());
        }    
        return false;
    }
    
    @Override
    public boolean isEnrolmentBeforeDateline2(String term) {
        try{
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL isEnrolmentBeforeDateline2(?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, term);
            rs = pst.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            Functions.errorMessage("isEnrolmentBeforeDateline2 error: "+ e.getLocalizedMessage());
        }    
        return false;
    }

    @Override
    public ArrayList<EnrolmentDateline> GetAllEnrolmentDatelines() {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetAllEnrolmentDatelines()";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            return GetEnrolmentDatelineDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getAllEnrolmentDatelines error: "+ e.getLocalizedMessage());
        }
        return null;
    }
    
}
