/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao.impl;

import com.molorane.college.db.DBConnection;
import com.molorane.college.custom.Functions;
import com.molorane.college.dao.TuitionDao;
import com.molorane.college.model.Tuition;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mothusi Molorane
 */
public class TuitionDaoImpl extends TuitionDao{

    @Override
    public int AddTuition(Tuition tuition) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainTuition(?,?,?,?,?,?,?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, -1);
            cs.setLong(2, tuition.getPersonId());
            cs.setInt(3, tuition.getTermId());
            cs.setString(4, "");
            cs.setInt(5, tuition.getCategoryId());
            cs.setInt(6, tuition.getMethodId());
            cs.setString(7, tuition.getRef_no());
            cs.setDouble(8, tuition.getDebit());
            cs.setDouble(9, tuition.getCredit());
            cs.setLong(10, tuition.getRecordedBy());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("MaintainTuition error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int EditTuition(Tuition tuition) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainTuition(?,?,?,?,?,?,?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, tuition.getReceipt_no());
            cs.setLong(2, tuition.getPersonId());
            cs.setInt(3, tuition.getTermId());
            cs.setString(4, tuition.getTransaction_date());
            cs.setInt(5, tuition.getCategoryId());
            cs.setInt(6, tuition.getMethodId());
            cs.setString(7, tuition.getRef_no());
            cs.setDouble(8, tuition.getDebit());
            cs.setDouble(9, tuition.getCredit());
            cs.setLong(10, tuition.getRecordedBy());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("MaintainTuition error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int RemoveTuition(int receipt_no) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL RemoveTuition(?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, receipt_no);
            cs.registerOutParameter(2, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(2);
        } catch (SQLException e) {
            Functions.errorMessage("RemoveTuition error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public Tuition GetTuition(int receipt_no) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetTuition(?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, receipt_no);
            rs = pst.executeQuery();
            if(rs.next())
                return GetTuitionDetailsFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getTuition error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Tuition> GetAllStudentTransactions(long personId) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetAllStudentTransactions(?)";
            pst = conn.prepareStatement(sql);
            pst.setLong(1, personId);
            rs = pst.executeQuery();
            return GetTuitionDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getAllStudentTransactions error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public List<HashMap<String, Object>> GetStudentTuitionAccount(long personId) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetStudentTuitionAccount(?)";
            pst = conn.prepareStatement(sql);
            pst.setLong(1, personId);
            rs = pst.executeQuery();
            return Functions.convertResultsetToList(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getStudentTuitionAccount error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public List<HashMap<String, Object>> GetStudentTermAccount(long personId, int termId) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetStudentTermAccount(?,?)";
            pst = conn.prepareStatement(sql);
            pst.setLong(1, personId);
            pst.setInt(2, termId);
            rs = pst.executeQuery();
            return Functions.convertResultsetToList(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getStudentTermAccount error: "+ e.getLocalizedMessage());
        }
        return null;
    }
    
}
