/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao;

import com.molorane.college.model.PaySchedulePayment;
import com.molorane.college.model.Tuition;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class PaySchedulePaymentDao {
    
    public abstract int AddPaySchedulePayment(PaySchedulePayment paySchedulePayment);
    public abstract int EditPaySchedulePayment(PaySchedulePayment paySchedulePayment);
    public abstract int RemovePaySchedulePayment(int pspId);    
    public abstract PaySchedulePayment GetPaySchedulePayment(int pspId);
    public abstract ArrayList<PaySchedulePayment> GetStudentPaySchedulePayments(int payscheduleId);
    public abstract int TransactionPaySchedule(PaySchedulePayment psp,Tuition t);
    public abstract List<HashMap<String,Object>> PreviousUnpaidPaySchedule(long personId, int termId, int payscheduleId);
    
    // PAYSCHEDULE PAYMENT CONVERSION METHODS
    protected PaySchedulePayment GetPaySchedulePaymentDetailsFromResultSet(ResultSet rs) throws SQLException{
        PaySchedulePayment paySchedulePayment = new PaySchedulePayment();
        paySchedulePayment.setPaySchedulePayment(rs.getInt("pspId"), 
                        rs.getInt("payscheduleId"),
                        rs.getDouble("amount"),
                        rs.getString("paymentdate"));
        return paySchedulePayment;
    }
    
    protected ArrayList<PaySchedulePayment> GetPaySchedulePaymentDetailsCollectionFromResultSet(ResultSet rs) throws SQLException{
       ArrayList<PaySchedulePayment> paySchedulePayments = new ArrayList<>();
        while (rs.next())
            paySchedulePayments.add(GetPaySchedulePaymentDetailsFromResultSet(rs));
        return paySchedulePayments;
    }
}
