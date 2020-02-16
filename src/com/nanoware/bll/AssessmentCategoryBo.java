/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll;

import com.nanoware.model.AssessmentCategory;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public interface AssessmentCategoryBo {
    
    public abstract int AddAssessmentCategory(AssessmentCategory category);
    public abstract int EditAssessmentCategory(AssessmentCategory category);
    public abstract int RemoveAssessmentCategory(int categoryId);    
    public abstract AssessmentCategory getAssessmentCategory(int categoryId);    
    public abstract ArrayList<AssessmentCategory> getAllAssessmentCategories();
}
