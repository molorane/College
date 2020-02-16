/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll.impl;

import com.nanoware.bll.AssessmentBo;
import com.nanoware.dao.AssessmentDao;
import com.nanoware.dao.impl.AssessmentDaoImpl;
import com.nanoware.model.Assessment;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mothusi Molorane
 */
public class AssessmentBoImpl implements AssessmentBo{
    
    private final AssessmentDao dao = new AssessmentDaoImpl();

    @Override
    public int AddAssessment(Assessment assessment, int campusCode) {
        return dao.AddAssessment(assessment, campusCode);
    }

    @Override
    public int EditAssessment(Assessment assessment, int campusCode) {
        return dao.EditAssessment(assessment, campusCode);
    }

    @Override
    public int RemoveAssessment(Assessment assessment, int campusCode) {
        return dao.RemoveAssessment(assessment, campusCode);
    }

    @Override
    public Assessment getAssessment(Assessment assessment) {
        return dao.getAssessment(assessment);
    }

    @Override
    public int UploadMarks(List<HashMap<String, Object>> marks, int campusCode, String moduleCode, int termId, int assId) {
        return dao.UploadMarks(marks, campusCode, moduleCode, termId, assId);
    }

    @Override
    public List<HashMap<String, Object>> getStudentMarks(int campusCode, String moduleCode, int termId, int assId) {
        return dao.getStudentMarks(campusCode, moduleCode, termId, assId);
    }
}
