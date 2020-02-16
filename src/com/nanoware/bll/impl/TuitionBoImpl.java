/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll.impl;

import com.nanoware.bll.TuitionBo;
import com.nanoware.dao.TuitionDao;
import com.nanoware.dao.impl.TuitionDaoImpl;
import com.nanoware.model.Tuition;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mothusi Molorane
 */
public class TuitionBoImpl implements TuitionBo{
    
    private final TuitionDao dao;

    public TuitionBoImpl() {
        this.dao = new TuitionDaoImpl();
    }

    @Override
    public int AddTuition(Tuition tuition) {
        return dao.AddTuition(tuition);
    }

    @Override
    public int EditTuition(Tuition tuition) {
        return dao.EditTuition(tuition);
    }

    @Override
    public int RemoveTuition(int receipt_no) {
        return dao.RemoveTuition(receipt_no);
    }

    @Override
    public Tuition GetTuition(int receipt_non) {
        return dao.GetTuition(receipt_non);
    }

    @Override
    public ArrayList<Tuition> GetAllStudentTransactions(long personId) {
        return dao.GetAllStudentTransactions(personId);
    }

    @Override
    public List<HashMap<String, Object>> GetStudentTuitionAccount(long personId) {
        return dao.GetStudentTuitionAccount(personId);
    }

    @Override
    public List<HashMap<String, Object>> GetStudentTermAccount(long personId, int termId) {
        return dao.GetStudentTermAccount(personId, termId);
    }
}
