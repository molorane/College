/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao.impl;

import com.nanoware.bll.Functions;
import com.nanoware.dao.PayScheduleDao;
import com.nanoware.model.PaySchedule;
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
public class PayScheduleDaoImpl extends PayScheduleDao{

    @Override
    public int AddPaySchedule(PaySchedule paySchedule) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainPaySchedule(?,?,?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, -1);
            cs.setLong(2, paySchedule.getPersonId());
            cs.setInt(3, paySchedule.getTermId());
            cs.setString(4, paySchedule.getScheduledate());
            cs.setDouble(5, paySchedule.getAmount());
            cs.setLong(6, paySchedule.getRecordedBy());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("MaintainPaySchedule error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int EditPaySchedule(PaySchedule paySchedule) {
       try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainPaySchedule(?,?,?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, paySchedule.getPayscheduleId());
            cs.setLong(2, paySchedule.getPersonId());
            cs.setInt(3, paySchedule.getTermId());
            cs.setString(4, paySchedule.getScheduledate());
            cs.setDouble(5, paySchedule.getAmount());
            cs.setLong(6, paySchedule.getRecordedBy());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("MaintainPaySchedule error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int RemovePaySchedule(int payscheduleId) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL RemovePaySchedule(?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, payscheduleId);
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("RemovePaySchedule error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public PaySchedule GetPaySchedule(int payscheduleId) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetPaySchedule(?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, payscheduleId);
            if (rs.next()) {
                return GetPayScheduleDetailsFromResultSet(rs);
            }
        } catch (SQLException e) {
            Functions.errorMessage("getPaySchedule error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<PaySchedule> GetStudentPaySchedule(long personId, String term) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetStudentPaySchedule(?,?)";
            pst = conn.prepareStatement(sql);
            pst.setLong(1, personId);
            pst.setString(2, term);
            return GetPayScheduleDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getStudentPaySchedule error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public List<HashMap<String, Object>> GetStudentPayScheduleWithBalance(long personId, int termId) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetStudentPayScheduleWithBalance(?,?)";
            pst = conn.prepareStatement(sql);
            pst.setLong(1, personId);
            pst.setInt(2, termId);
            rs = pst.executeQuery();
            return Functions.convertResultsetToList(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getStudentPayScheduleWithBalance error: "+ e.getLocalizedMessage());
        }
        return null;
    }
}
