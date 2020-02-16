/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll.impl;

import com.molorane.college.bll.GradeDatelineBo;
import com.molorane.college.dao.GradeDatelineDao;
import com.molorane.college.dao.impl.GradeDatelineDaoImpl;
import com.molorane.college.model.GradeDateline;
import java.util.ArrayList;

/**
 * @author Mothusi Molorane
 */

public class GradeDatelineBoImpl implements GradeDatelineBo{
    
    private final GradeDatelineDao dao;

    public GradeDatelineBoImpl() {
        this.dao = new GradeDatelineDaoImpl();
    }
            
    @Override
    public int AddGradeDateline(GradeDateline gradeDateline) {
        return dao.AddGradeDateline(gradeDateline);
    }

    @Override
    public int EditGradeDateline(GradeDateline gradeDateline) {
        return dao.EditGradeDateline(gradeDateline);
    }

    @Override
    public int RemoveGradeDateline(int termId) {
        return dao.RemoveGradeDateline(termId);
    }

    @Override
    public GradeDateline GetGradeDateline(int termId) {
       return dao.GetGradeDateline(termId);
    }

    @Override
    public boolean isSubmissionBeforeDateline(int termId) {
        return dao.isSubmissionBeforeDateline(termId);
    }

    @Override
    public ArrayList<GradeDateline> GetAllGradeDatelines() {
        return dao.GetAllGradeDatelines();
    }
    
}
