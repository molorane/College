/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll;

import com.nanoware.model.Assessment;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mothusi Molorane
 */
public interface AssessmentBo {
    public abstract int AddAssessment(Assessment assessment,int campusCode);
    public abstract int EditAssessment(Assessment assessment,int campusCode);
    public abstract int RemoveAssessment(Assessment assessment,int campusCode);    
    public abstract Assessment getAssessment(Assessment assessment);
    public abstract int UploadMarks(List<HashMap<String, Object>> marks,int campusCode,String moduleCode, int termId, int assId);
    public abstract List<HashMap<String,Object>> getStudentMarks(int campusCode, String moduleCode, int termId, int assId);
}
