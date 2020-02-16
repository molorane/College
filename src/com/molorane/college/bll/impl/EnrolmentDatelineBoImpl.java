/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll.impl;

import com.molorane.college.bll.EnrolmentDatelineBo;
import com.molorane.college.dao.EnrolmentDatelineDao;
import com.molorane.college.dao.impl.EnrolmentDatelineDaoImpl;
import com.molorane.college.model.EnrolmentDateline;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public class EnrolmentDatelineBoImpl implements EnrolmentDatelineBo{
    
    private final EnrolmentDatelineDao dao;

    public EnrolmentDatelineBoImpl() {
        this.dao = new EnrolmentDatelineDaoImpl();
    }

    @Override
    public int AddEnrolmentDateline(EnrolmentDateline enrolmentDateline) {
        return dao.AddEnrolmentDateline(enrolmentDateline);
    }

    @Override
    public int EditEnrolmentDateline(EnrolmentDateline enrolmentDateline) {
        return dao.EditEnrolmentDateline(enrolmentDateline);
    }

    @Override
    public int RemoveEnrolmentDateline(int termId) {
        return dao.RemoveEnrolmentDateline(termId);
    }

    @Override
    public EnrolmentDateline GetEnrolmentDateline(int termId) {
        return dao.GetEnrolmentDateline(termId);
    }

    @Override
    public boolean IsEnrolmentBeforeDateline(int termId) {
        return dao.isEnrolmentBeforeDateline(termId);
    }
    
    @Override
    public boolean IsEnrolmentBeforeDateline2(String term) {
        return dao.isEnrolmentBeforeDateline2(term);
    }

    @Override
    public ArrayList<EnrolmentDateline> GetAllEnrolmentDatelines() {
        return dao.GetAllEnrolmentDatelines();
    }
    
}
