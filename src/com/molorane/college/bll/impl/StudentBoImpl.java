/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll.impl;

import com.molorane.college.bll.StudentBo;
import com.molorane.college.dao.StudentDao;
import com.molorane.college.dao.impl.StudentDaoImpl;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mothusi Molorane
 */
public class StudentBoImpl implements StudentBo{
    private final StudentDao dao;

    public StudentBoImpl() {
        this.dao = new StudentDaoImpl();
    }
    
    @Override
    public List<HashMap<String, Object>> StudentModuleGrades(int personId, int termId, String moduleCode) {
        return dao.StudentModuleGrades(personId, termId, moduleCode);
    }

    @Override
    public List<HashMap<String, Object>> GetStudentModuleAssessment(int campusCode, int termId, String moduleCode) {
        return dao.GetStudentModuleAssessment(campusCode, termId, moduleCode);
    }
    
}
