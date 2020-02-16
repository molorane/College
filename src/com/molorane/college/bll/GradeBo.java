/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll;

import com.molorane.college.model.Grade;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mothusi Molorane
 */
public interface GradeBo {
    public abstract int AddGrade(Grade grade,int campusCode);
    public abstract int EditGrade(Grade grade,int campusCode);
    public abstract int RemoveGrade(Grade grade,int campusCode);    
    public abstract Grade GetGrade(Grade grade);
    public abstract int UploadMarks(List<HashMap<String, Object>> marks,int campusCode,String moduleCode, int termId, int gradeId);
    public abstract List<HashMap<String,Object>> GetStudentMarks(int campusCode, String moduleCode, int termId, int gradeId);
}
