/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao;

import com.molorane.college.model.StudyType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class StudyTypeDao {
    
    public abstract int AddStudyType(StudyType studytype);
    public abstract int EditStudyType(StudyType studytype);
    public abstract int RemoveStudyType(int studytypeId);    
    public abstract StudyType GetStudyType(int studytypeId);    
    public abstract ArrayList<StudyType> GetAllStudyTypes();
    
    // STUDY TYPE CONVERSION METHODS
    protected StudyType GetStudyTypeDetailsFromResultSet(ResultSet rs) throws SQLException{
        StudyType studytype = new StudyType();
        studytype.setStudyType(rs.getInt("studytypeId"), 
                        rs.getString("studytype"));
        return studytype;
    }
    
    protected ArrayList<StudyType> GetStudyTypeDetailsCollectionFromResultSet(ResultSet rs) throws SQLException{
       ArrayList<StudyType> studytypes = new ArrayList<>();
        while (rs.next())
            studytypes.add(GetStudyTypeDetailsFromResultSet(rs));
        return studytypes;
    }
}
