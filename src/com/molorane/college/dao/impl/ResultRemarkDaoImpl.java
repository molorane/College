/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao.impl;

import com.molorane.college.db.DBConnection;
import com.molorane.college.custom.Functions;
import com.molorane.college.dao.ResultRemarkDao;
import com.molorane.college.model.ResultRemark;
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
public class ResultRemarkDaoImpl extends ResultRemarkDao{

    @Override
    public int AddResultRemark(ResultRemark resultremark) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainResultRemark(?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, -1);
            cs.setString(2, resultremark.getResultRemark());
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(3);
        } catch (SQLException e) {
            Functions.errorMessage("MaintainResultRemark error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int EditResultRemark(ResultRemark resultremark) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainResultRemark(?,?)";
            cs = conn.prepareCall(sql);
            cs.setString(1, resultremark.getResultRemarkId());
            cs.setString(2, resultremark.getResultRemark());
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(3);
        } catch (SQLException e) {
            Functions.errorMessage("MaintainResultRemark error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int RemoveResultRemark(String resultremarkId) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "CALL RemoveResultRemark(?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, resultremarkId);
            // execute the java preparedstatement
            int count = pst.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("RemoveResultRemark error: "+ e.getLocalizedMessage());
        }
        return 0;
    }

    @Override
    public ResultRemark GetResultRemark(String resultremarkId) {
        try{
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetResultRemark(?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, resultremarkId);
            rs = pst.executeQuery();
            if(rs.next())
                return GetResultRemarkDetailsFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getResultRemark error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<ResultRemark> GetAllResultRemarks() {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetAllResultRemarks()";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            return GetResultRemarkDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getAllResultRemarks error: "+ e.getLocalizedMessage());
        }
        return null;
    }
    
}
