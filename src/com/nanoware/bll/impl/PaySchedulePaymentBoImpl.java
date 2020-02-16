/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll.impl;

import com.nanoware.bll.PaySchedulePaymentBo;
import com.nanoware.dao.PaySchedulePaymentDao;
import com.nanoware.dao.impl.PaySchedulePaymentDaoImpl;
import com.nanoware.model.PaySchedulePayment;
import com.nanoware.model.Tuition;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mothusi Molorane
 */
public class PaySchedulePaymentBoImpl implements PaySchedulePaymentBo{
    
    private final PaySchedulePaymentDao dao;

    public PaySchedulePaymentBoImpl() {
        this.dao = new PaySchedulePaymentDaoImpl();
    }
    
    @Override
    public int AddPaySchedulePayment(PaySchedulePayment paySchedulePayment) {
        return dao.AddPaySchedulePayment(paySchedulePayment);
    }

    @Override
    public int EditPaySchedulePayment(PaySchedulePayment paySchedulePayment) {
        return dao.EditPaySchedulePayment(paySchedulePayment);
    }

    @Override
    public int RemovePaySchedulePayment(int pspId) {
        return dao.RemovePaySchedulePayment(pspId);
    }

    @Override
    public PaySchedulePayment GetPaySchedulePayment(int pspId) {
        return dao.GetPaySchedulePayment(pspId);
    }

    @Override
    public ArrayList<PaySchedulePayment> GetStudentPaySchedulePayments(int payscheduleId) {
        return dao.GetStudentPaySchedulePayments(payscheduleId);
    }

    @Override
    public int TransactionPaySchedule(PaySchedulePayment psp, Tuition t) {
        return dao.TransactionPaySchedule(psp, t);
    }

    @Override
    public List<HashMap<String, Object>> PreviousUnpaidPaySchedule(long personId, int termId, int payscheduleId) {
        return dao.PreviousUnpaidPaySchedule(personId, termId, payscheduleId);
    }
}
