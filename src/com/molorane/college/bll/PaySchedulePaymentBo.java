/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll;

import com.molorane.college.model.PaySchedulePayment;
import com.molorane.college.model.Tuition;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mothusi Molorane
 */
public interface PaySchedulePaymentBo {
    
    public abstract int AddPaySchedulePayment(PaySchedulePayment paySchedulePayment);
    public abstract int EditPaySchedulePayment(PaySchedulePayment paySchedulePayment);
    public abstract int RemovePaySchedulePayment(int pspId);    
    public abstract PaySchedulePayment GetPaySchedulePayment(int pspId);
    public abstract ArrayList<PaySchedulePayment> GetStudentPaySchedulePayments(int payscheduleId);
    public abstract int TransactionPaySchedule(PaySchedulePayment psp,Tuition t);
    public abstract List<HashMap<String,Object>> PreviousUnpaidPaySchedule(long personId, int termId, int payscheduleId);
}
