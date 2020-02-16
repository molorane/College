/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll.impl;

import com.molorane.college.bll.EducationBo;
import com.molorane.college.dao.EducationDao;
import com.molorane.college.dao.impl.EducationDaoImpl;
import com.molorane.college.model.Education;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public class EducationBoImpl implements EducationBo{
    
    private final EducationDao dao;

    public EducationBoImpl() {
        this.dao = new EducationDaoImpl();
    }

    @Override
    public int AddEducation(Education education) {
        return dao.AddEducation(education);
    }

    @Override
    public int EditEducation(Education education) {
        return dao.EditEducation(education);
    }

    @Override
    public int RemoveEducation(int eduId) {
        return dao.RemoveEducation(eduId);
    }

    @Override
    public Education GetEducation(int eduId) {
        return dao.GetEducation(eduId);
    }

    @Override
    public ArrayList<Education> GetPersonEducation(long personId) {
        return dao.GetPersonEducation(personId);
    }    
}
