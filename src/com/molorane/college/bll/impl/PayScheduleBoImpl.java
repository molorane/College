/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll.impl;

import com.molorane.college.bll.PayScheduleBo;
import com.molorane.college.dao.PayScheduleDao;
import com.molorane.college.dao.impl.PayScheduleDaoImpl;
import com.molorane.college.model.PaySchedule;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mothusi Molorane
 */
public class PayScheduleBoImpl implements PayScheduleBo{
    
    private final PayScheduleDao dao;

    public PayScheduleBoImpl() {
        this.dao = new PayScheduleDaoImpl();
    }

    @Override
    public int AddPaySchedule(PaySchedule paySchedule) {
        return dao.AddPaySchedule(paySchedule);
    }

    @Override
    public int EditPaySchedule(PaySchedule paySchedule) {
        return dao.EditPaySchedule(paySchedule);
    }

    @Override
    public int RemovePaySchedule(int payscheduleId) {
        return dao.RemovePaySchedule(payscheduleId);
    }

    @Override
    public PaySchedule GetPaySchedule(int payscheduleId) {
        return dao.GetPaySchedule(payscheduleId);
    }

    @Override
    public ArrayList<PaySchedule> GetStudentPaySchedule(long personId, String term) {
        return dao.GetStudentPaySchedule(personId, term);
    }

    @Override
    public List<HashMap<String, Object>> GetStudentPayScheduleWithBalance(long personId, int termId) {
        return dao.GetStudentPayScheduleWithBalance(personId, termId);
    }
}
