/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll.impl;

import com.molorane.college.bll.EntryBo;
import com.molorane.college.dao.EntryDao;
import com.molorane.college.dao.impl.EntryDaoImpl;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mothusi Molorane
 */
public class EntryBoImpl implements EntryBo{
    
    private final EntryDao dao;

    public EntryBoImpl() {
        this.dao = new EntryDaoImpl();
    }

    @Override
    public List<HashMap<String, Object>> GetMarkEntry(int termId, int campusCode) {
        return dao.GetMarkEntry(termId, campusCode);
    }

    @Override
    public List<HashMap<String, Object>> GetExamEntry(int termId, int campusCode) {
        return dao.GetExamEntry(termId, campusCode);
    }
    
}
