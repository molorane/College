/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll.impl;

import com.nanoware.bll.StudentBo;
import com.nanoware.dao.StudentDao;
import com.nanoware.dao.impl.StudentDaoImpl;
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
