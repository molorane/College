/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll;

import com.molorane.college.model.Subject;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public interface SubjectBo {
    
    public abstract int AddSubject(Subject subject);
    public abstract int EditSubject(Subject subject);
    public abstract int RemoveSubject(int subjectId);    
    public abstract Subject GetSubject(int subjectId);    
    public abstract ArrayList<Subject> GetAllSubjects();
    
}
