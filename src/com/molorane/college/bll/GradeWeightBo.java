/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll;

import com.molorane.college.model.GradeWeight;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public interface GradeWeightBo {
    
    public abstract int AddGradeWeight(GradeWeight gradeWeight);
    public abstract int EditGradeWeight(GradeWeight gradeWeight);
    public abstract int RemoveGradeWeight(int gwId);    
    public abstract GradeWeight GetGradeWeight(int gwId);
    public abstract ArrayList<GradeWeight> GetGradeWeightByCourseAndTerm(String courseCode,int termId);
}
