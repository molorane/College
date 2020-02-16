/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao;

import com.molorane.college.model.PaySchedule;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class PayScheduleDao {
    
    public abstract int AddPaySchedule(PaySchedule paySchedule);
    public abstract int EditPaySchedule(PaySchedule paySchedule);
    public abstract int RemovePaySchedule(int payscheduleId);    
    public abstract PaySchedule GetPaySchedule(int payscheduleId);
    public abstract ArrayList<PaySchedule> GetStudentPaySchedule(long personId, String term);
    public abstract List<HashMap<String,Object>> GetStudentPayScheduleWithBalance(long personId, int termId);
    
    // PAYSCHEDULE CONVERSION METHODS
    protected PaySchedule GetPayScheduleDetailsFromResultSet(ResultSet rs) throws SQLException{
        PaySchedule paySchedule = new PaySchedule();
        paySchedule.setPaySchedule(rs.getInt("payscheduleId"), 
                        rs.getLong("personId"),
                        rs.getInt("termId"),
                        rs.getString("scheduledate"),
                        rs.getDouble("amount"),
                        rs.getLong("recordedBy"),
                        rs.getString("term"));
        return paySchedule;
    }
    
    protected ArrayList<PaySchedule> GetPayScheduleDetailsCollectionFromResultSet(ResultSet rs) throws SQLException{
       ArrayList<PaySchedule> records = new ArrayList<>();
        while (rs.next())
            records.add(GetPayScheduleDetailsFromResultSet(rs));
        return records;
    }
}
