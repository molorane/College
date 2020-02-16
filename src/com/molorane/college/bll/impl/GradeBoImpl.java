/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll.impl;

import com.molorane.college.dao.GradeDao;
import com.molorane.college.dao.impl.GradeDaoImpl;
import com.molorane.college.model.Grade;
import java.util.HashMap;
import java.util.List;
import com.molorane.college.bll.GradeBo;

/**
 *
 * @author Mothusi Molorane
 */
public class GradeBoImpl implements GradeBo{
    
    private final GradeDao dao;

    public GradeBoImpl() {
        this.dao = new GradeDaoImpl();
    }

    @Override
    public int AddGrade(Grade grade, int campusCode) {
        return dao.AddGrade(grade, campusCode);
    }

    @Override
    public int EditGrade(Grade grade, int campusCode) {
        return dao.EditGrade(grade, campusCode);
    }

    @Override
    public int RemoveGrade(Grade grade, int campusCode) {
        return dao.RemoveGrade(grade, campusCode);
    }

    @Override
    public Grade GetGrade(Grade grade) {
        return dao.GetGrade(grade);
    }

    @Override
    public int UploadMarks(List<HashMap<String, Object>> marks, int campusCode, String moduleCode, int termId, int gradeId) {
        return dao.UploadMarks(marks, campusCode, moduleCode, termId, gradeId);
    }

    @Override
    public List<HashMap<String, Object>> GetStudentMarks(int campusCode, String moduleCode, int termId, int gradeId) {
        return dao.GetStudentMarks(campusCode, moduleCode, termId, gradeId);
    }
}
