/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao;

import com.nanoware.model.AssessmentCategory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class AssessmentCategoryDao {
    
    public abstract int AddAssessmentCategory(AssessmentCategory category);
    public abstract int EditAssessmentCategory(AssessmentCategory category);
    public abstract int RemoveAssessmentCategory(int categoryId);    
    public abstract AssessmentCategory getAssessmentCategory(int categoryId);    
    public abstract ArrayList<AssessmentCategory> getAllAssessmentCategories();
    
    // AssessmentCategory CONVERSION METHODS
    protected AssessmentCategory GetAssessmentCategoryDetailsFromResultSet(ResultSet rs) throws SQLException{
        AssessmentCategory category = new AssessmentCategory();
        category.setAssessmentCategory(rs.getInt("assId"), 
                        rs.getString("asscategory"));
        return category;
    }
    
    protected ArrayList<AssessmentCategory> GetAssessmentCategoryDetailsCollectionFromResultSet(ResultSet rs) throws SQLException{
       ArrayList<AssessmentCategory> categories = new ArrayList<>();
        while (rs.next())
            categories.add(GetAssessmentCategoryDetailsFromResultSet(rs));
        return categories;
    }
}
