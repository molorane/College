/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao;

import com.nanoware.model.Assessment;
import com.nanoware.model.Campus;
import com.nanoware.model.Module;
import com.nanoware.model.Term;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class AssessmentDao {
    
    public abstract int AddAssessment(Assessment assessment,int campusCode);
    public abstract int EditAssessment(Assessment assessment,int campusCode);
    public abstract int RemoveAssessment(Assessment assessment,int campusCode);    
    public abstract Assessment getAssessment(Assessment assessment);
    public abstract int UploadMarks(List<HashMap<String, Object>> marks,int campusCode,String moduleCode, int termId, int assId);
    public abstract List<HashMap<String,Object>> getStudentMarks(int campusCode, String moduleCode, int termId, int assId);
    
    protected Assessment GetAssessmentDetailsFromResultSet(ResultSet rs) throws SQLException{
        Assessment assessment = new Assessment();
        assessment.setAssessment(rs.getInt("personId"),
                        rs.getString("moduleCode"),
                        rs.getInt("termId"), 
                        rs.getInt("assId"), 
                        rs.getDouble("marks"));
        return assessment;
    }
    
    protected ArrayList<Assessment> GetAssessmentDetailsCollectionFromResultSet(ResultSet rs) throws SQLException{
       ArrayList<Assessment> assessments = new ArrayList<>();
        while (rs.next())
            assessments.add(GetAssessmentDetailsFromResultSet(rs));
        return assessments;
    }
}
