/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll.impl;

import com.molorane.college.bll.EnrolmentBo;
import com.molorane.college.bll.EnrolmentDatelineBo;
import com.molorane.college.custom.Functions;
import com.molorane.college.dao.EnrolmentDao;
import com.molorane.college.dao.impl.EnrolmentDaoImpl;
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
public class EnrolmentBoImpl implements EnrolmentBo{
    
    private final EnrolmentDao dao = new EnrolmentDaoImpl();
    private final EnrolmentDatelineBo srv = new EnrolmentDatelineBoImpl();

    @Override
    public int AddEnrolment(Enrolment enrolment) {
        return dao.AddEnrolment(enrolment);
    }

    @Override
    public int EditEnrolment(Enrolment enrolment) {
        return dao.EditEnrolment(enrolment);
    }

    @Override
    public int RemoveEnrolment(Enrolment enrolment) {
        return dao.RemoveEnrolment(enrolment);
    }

    @Override
    public Enrolment GetEnrolment(Enrolment enrolment) {
        return dao.GetEnrolment(enrolment);
    }

    @Override
    public ArrayList<Enrolment> GetPersonEnrolments(long personId) {
        return dao.GetPersonEnrolments(personId);
    }

    @Override
    public int EnrolStudent(long personId, String courseCode, ArrayList<String> modules, Term term, StudyType studyType, Campus campus) {
        if(srv.IsEnrolmentBeforeDateline(term.getTermId())){
            return dao.EnrolStudent(personId, courseCode, modules, term, studyType, campus);
        }else{
            Functions.errorMessage("You cannot enrol modules for term "+term+"\n"
                    + "Dateline for enrolling modules for term "+term+" is overdue.");
        }
        return 0;
    }    

    @Override
    public List<HashMap<String, Object>> GetStudentCourseEnrolments(long personId) {
        return dao.GetStudentCourseEnrolments(personId);
    }

    @Override
    public List<HashMap<String, Object>> GetStudentCourseEnrolmentModules(long personId, String courseCode, String term) {
        return dao.GetStudentCourseEnrolmentModules(personId, courseCode, term);
    }

    @Override
    public int deregisterModules(long personId, String courseCode, ArrayList<String> modules, String term) {
        if(srv.IsEnrolmentBeforeDateline2(term)){
            return dao.deregisterModules(personId, courseCode, modules, term);
        }else{
            Functions.errorMessage("You cannot deregister modules for term "+term+"\n"
                    + "Dateline for deregistration of modules for the  term "+term+" is overdue.");
        }
        return 0;
    }

    @Override
    public List<HashMap<String, Object>> GetStudentTermEnrolments(long personId, int termId) {
        return dao.GetStudentTermEnrolments(personId, termId);
    }
}
