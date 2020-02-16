/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll;

import com.molorane.college.model.PaySchedule;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mothusi Molorane
 */
public interface PayScheduleBo {
    
    public abstract int AddPaySchedule(PaySchedule paySchedule);
    public abstract int EditPaySchedule(PaySchedule paySchedule);
    public abstract int RemovePaySchedule(int payscheduleId);    
    public abstract PaySchedule GetPaySchedule(int payscheduleId);
    public abstract ArrayList<PaySchedule> GetStudentPaySchedule(long personId, String term);
    public abstract List<HashMap<String,Object>> GetStudentPayScheduleWithBalance(long personId, int termId);
}
