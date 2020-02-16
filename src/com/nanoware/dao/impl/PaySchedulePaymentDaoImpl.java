/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao.impl;

import com.nanoware.bll.Functions;
import com.nanoware.dao.PaySchedulePaymentDao;
import com.nanoware.model.PaySchedulePayment;
import com.nanoware.model.Tuition;
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
public class PaySchedulePaymentDaoImpl extends PaySchedulePaymentDao{

    @Override
    public int AddPaySchedulePayment(PaySchedulePayment paySchedulePayment) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainPaySchedulePayment(?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, -1);
            cs.setInt(2, paySchedulePayment.getPayscheduleId());
            cs.setDouble(3, paySchedulePayment.getAmount());
            cs.setString(4, "");
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("MaintainPaySchedulePayment error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int EditPaySchedulePayment(PaySchedulePayment paySchedulePayment) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainPaySchedulePayment(?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, paySchedulePayment.getPspId());
            cs.setInt(2, paySchedulePayment.getPayscheduleId());
            cs.setDouble(3, paySchedulePayment.getAmount());
            cs.setString(4, "");
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("MaintainPaySchedulePayment error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int RemovePaySchedulePayment(int pspId) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL RemovePaySchedulePayment(?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, pspId);
            cs.registerOutParameter(2, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(2);
        } catch (SQLException e) {
            Functions.errorMessage("RemovePaySchedulePayment error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public PaySchedulePayment GetPaySchedulePayment(int pspId) {
         try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetPaySchedulePayment(?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, pspId);
            rs = pst.executeQuery();
            if (rs.next()) {
                return GetPaySchedulePaymentDetailsFromResultSet(rs);
            }
        } catch (SQLException e) {
            Functions.errorMessage("getPaySchedulePayment error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<PaySchedulePayment> GetStudentPaySchedulePayments(int payscheduleId) {
        try{
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetStudentPaySchedulePayments(?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, payscheduleId);
            rs = pst.executeQuery();
            return GetPaySchedulePaymentDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getStudentPaySchedulePayments error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public int TransactionPaySchedule(PaySchedulePayment psp, Tuition t) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL TransactionPaySchedule(?,?,?,?,?,?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setLong(1, t.getPersonId());
            cs.setInt(2, t.getTermId());
            cs.setInt(3, t.getCategoryId());
            cs.setInt(4, t.getMethodId());
            cs.setString(5, t.getRef_no());
            cs.setLong(6, t.getRecordedBy());
            cs.setInt(7, psp.getPayscheduleId());
            cs.setDouble(8, psp.getAmount());
            cs.registerOutParameter(9, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(9);
        } catch (SQLException e) {
            Functions.errorMessage("TransactionPaySchedule error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public List<HashMap<String,Object>> PreviousUnpaidPaySchedule(long personId, int termId, int payscheduleId) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL PreviousUnpaidPaySchedule(?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setLong(1, personId);
            pst.setInt(2, termId);
            pst.setInt(3, payscheduleId);
            rs = pst.executeQuery();
            return Functions.convertResultsetToList(rs);
        } catch (SQLException e) {
            Functions.errorMessage("PreviousUnpaidPaySchedule error: "+ e.getLocalizedMessage());
        }
        return null;
    }
}
