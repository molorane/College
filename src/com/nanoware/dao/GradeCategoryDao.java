/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao;

import com.nanoware.model.GradeCategory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class GradeCategoryDao {
    
    public abstract int AddGradeCategory(GradeCategory category);
    public abstract int EditGradeCategory(GradeCategory category);
    public abstract int RemoveGradeCategory(int categoryId);    
    public abstract GradeCategory GetGradeCategory(int categoryId);    
    public abstract ArrayList<GradeCategory> GetAllGradeCategories();
    
    // GradeCategory CONVERSION METHODS
    protected GradeCategory GetGradeCategoryDetailsFromResultSet(ResultSet rs) throws SQLException{
        GradeCategory category = new GradeCategory();
        category.setGradeCategory(rs.getInt("gradeId"), 
                        rs.getString("gradecategory"));
        return category;
    }
    
    protected ArrayList<GradeCategory> GetGradeCategoryDetailsCollectionFromResultSet(ResultSet rs) throws SQLException{
       ArrayList<GradeCategory> categories = new ArrayList<>();
        while (rs.next())
            categories.add(GetGradeCategoryDetailsFromResultSet(rs));
        return categories;
    }
}
