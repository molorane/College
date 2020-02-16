/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao;

import com.molorane.college.model.Subject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class SubjectDao {
    
    public abstract int AddSubject(Subject subject);
    public abstract int EditSubject(Subject subject);
    public abstract int RemoveSubject(int subjectId);    
    public abstract Subject GetSubject(int subjectId);    
    public abstract ArrayList<Subject> GetAllSubjects();
    
    // Subject CONVERSION METHODS
    protected Subject GetSubjectDetailsFromResultSet(ResultSet rs) throws SQLException{
        Subject subject = new Subject();
        subject.setSubject(rs.getInt("subjectId"), 
                        rs.getString("subject"));
        return subject;
    }
    
    protected ArrayList<Subject> GetSubjectDetailsCollectionFromResultSet(ResultSet rs) throws SQLException{
       ArrayList<Subject> subjects = new ArrayList<>();
        while (rs.next())
            subjects.add(GetSubjectDetailsFromResultSet(rs));
        return subjects;
    }
}
