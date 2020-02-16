/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao;

import com.molorane.college.model.GradeWeight;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class GradeWeightDao {
    
    public abstract int AddGradeWeight(GradeWeight gradeWeight);
    public abstract int EditGradeWeight(GradeWeight gradeWeight);
    public abstract int RemoveGradeWeight(int gwId);    
    public abstract GradeWeight GetGradeWeight(int gwId);
    public abstract ArrayList<GradeWeight> GetGradeWeightByCourseAndTerm(String courseCode,int termId);
    
     // GRADEWEIGHT CONVERSION METHODS
    protected GradeWeight GetGradeWeightDetailsFromResultSet(ResultSet rs) throws SQLException{
        GradeWeight gradeWeight = new GradeWeight();
        gradeWeight.setGradeWeight(rs.getInt("gwId"), 
                        rs.getString("courseCode"),
                        rs.getInt("termId"), 
                        rs.getInt("gradeId"),  
                        rs.getDouble("weight"),   
                        rs.getInt("recordBy"),
                        rs.getString("term"),
                        rs.getString("gradecategory"));
        return gradeWeight;
    }
    
    protected ArrayList<GradeWeight> GetGradeWeightDetailsCollectionFromResultSet(ResultSet rs) throws SQLException{
       ArrayList<GradeWeight> gradeWeights = new ArrayList<>();
        while (rs.next())
            gradeWeights.add(GetGradeWeightDetailsFromResultSet(rs));
        return gradeWeights;
    }
}
