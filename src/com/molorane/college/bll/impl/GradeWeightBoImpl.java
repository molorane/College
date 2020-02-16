/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll.impl;

import com.molorane.college.bll.GradeWeightBo;
import com.molorane.college.dao.GradeWeightDao;
import com.molorane.college.dao.impl.GradeWeightDaoImpl;
import com.molorane.college.model.GradeWeight;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public class GradeWeightBoImpl implements GradeWeightBo{
    
    private final GradeWeightDao dao;

    public GradeWeightBoImpl() {
        this.dao = new GradeWeightDaoImpl();
    }

    @Override
    public int AddGradeWeight(GradeWeight gradeWeight) {
        return dao.AddGradeWeight(gradeWeight);
    }

    @Override
    public int EditGradeWeight(GradeWeight gradeWeight) {
        return dao.EditGradeWeight(gradeWeight);
    }

    @Override
    public int RemoveGradeWeight(int gwId) {
        return dao.RemoveGradeWeight(gwId);
    }

    @Override
    public GradeWeight GetGradeWeight(int gwId) {
        return dao.GetGradeWeight(gwId);
    }

    @Override
    public ArrayList<GradeWeight> GetGradeWeightByCourseAndTerm(String courseCode, int termId) {
        return dao.GetGradeWeightByCourseAndTerm(courseCode, termId);
    }
}
