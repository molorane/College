/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll;

import com.molorane.college.model.Campus;
import com.molorane.college.model.Enrolment;
import com.molorane.college.model.StudyType;
import com.molorane.college.model.Term;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mothusi Molorane
 */
public interface EnrolmentBo {
    public abstract int AddEnrolment(Enrolment enrolment);
    public abstract int EditEnrolment(Enrolment enrolment);
    public abstract int RemoveEnrolment(Enrolment enrolment);    
    public abstract Enrolment GetEnrolment(Enrolment enrolment);
    public abstract ArrayList<Enrolment> GetPersonEnrolments(long personId);
    public abstract int EnrolStudent(long personId,String courseCode,ArrayList<String> modules, Term term,StudyType studyType,Campus campus);
    public abstract List<HashMap<String,Object>> GetStudentCourseEnrolments(long personId);
    public abstract List<HashMap<String,Object>> GetStudentCourseEnrolmentModules(long personId,String courseCode,String term);
    public abstract int deregisterModules(long personId,String courseCode,ArrayList<String> modules, String term);
    public abstract List<HashMap<String,Object>> GetStudentTermEnrolments(long personId, int termId);
}
