/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll;

import com.nanoware.model.GradeCategory;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public interface GradeCategoryBo {
    
    public abstract int AddGradeCategory(GradeCategory category);
    public abstract int EditGradeCategory(GradeCategory category);
    public abstract int RemoveGradeCategory(int categoryId);    
    public abstract GradeCategory GetGradeCategory(int categoryId);    
    public abstract ArrayList<GradeCategory> GetAllGradeCategories();
}
