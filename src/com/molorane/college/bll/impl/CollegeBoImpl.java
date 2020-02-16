/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll.impl;

import com.molorane.college.bll.CollegeBo;
import com.molorane.college.dao.CollegeDao;
import com.molorane.college.dao.impl.CollegeDaoImpl;

/**
 *
 * @author Mothusi Molorane
 */
public class CollegeBoImpl implements CollegeBo{
    
    private final CollegeDao dao;

    public CollegeBoImpl() {
        dao = new CollegeDaoImpl();
    }
    
    @Override
    public int GetCurrentYear() {
        return dao.GetCurrentYear();
    }
    
}
