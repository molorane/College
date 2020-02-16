/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao;

import com.molorane.college.model.Campus;
import com.molorane.college.model.Enrolment;
import com.molorane.college.model.StudyType;
import com.molorane.college.model.Term;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class EnrolmentDao {
    
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
            
    // ENLROMENT CONVERSION METHODS
    protected Enrolment GetEnrolmentDetailsFromResultSet(ResultSet rs) throws SQLException{
        Enrolment enrolment = new Enrolment();
        enrolment.setEnrolment(rs.getInt("personId"), 
                        rs.getString("courseCode"),
                        rs.getString("moduleCode"),
                        rs.getInt("termId"), 
                        rs.getInt("studytypeId"), 
                        rs.getInt("campusCode"), 
                        rs.getDouble("marks"),
                        rs.getString("course"),
                        rs.getString("module"),
                        rs.getString("term"),
                        rs.getString("studytype"),   
                        rs.getString("campusABR"));
        return enrolment;
    }
    
    protected ArrayList<Enrolment> GetEnrolmentDetailsCollectionFromResultSet(ResultSet rs) throws SQLException{
       ArrayList<Enrolment> enrolments = new ArrayList<>();
        while (rs.next())
            enrolments.add(GetEnrolmentDetailsFromResultSet(rs));
        return enrolments;
    }
}
