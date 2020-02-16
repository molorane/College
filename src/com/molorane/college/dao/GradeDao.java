/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao;

import com.molorane.college.model.Grade;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class GradeDao {
    
    public abstract int AddGrade(Grade assessment,int campusCode);
    public abstract int EditGrade(Grade assessment,int campusCode);
    public abstract int RemoveGrade(Grade assessment,int campusCode);    
    public abstract Grade GetGrade(Grade assessment);
    public abstract int UploadMarks(List<HashMap<String, Object>> marks,int campusCode,String moduleCode, int termId, int assId);
    public abstract List<HashMap<String,Object>> GetStudentMarks(int campusCode, String moduleCode, int termId, int assId);
    
    // Grade CONVERSION METHODS
    protected Grade GetGradeDetailsFromResultSet(ResultSet rs) throws SQLException{
        Grade assessment = new Grade();
        assessment.setGrade(rs.getInt("personId"),
                        rs.getString("moduleCode"),
                        rs.getInt("termId"), 
                        rs.getInt("gradeId"), 
                        rs.getDouble("marks"));
        return assessment;
    }
    
    protected ArrayList<Grade> GetGradeDetailsCollectionFromResultSet(ResultSet rs) throws SQLException{
       ArrayList<Grade> assessments = new ArrayList<>();
        while (rs.next())
            assessments.add(GetGradeDetailsFromResultSet(rs));
        return assessments;
    }
}
