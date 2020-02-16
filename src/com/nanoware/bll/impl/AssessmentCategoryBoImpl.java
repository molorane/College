/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll.impl;

import com.nanoware.bll.AssessmentCategoryBo;
import com.nanoware.dao.impl.AssessmentCategoryDaoImpl;
import com.nanoware.model.AssessmentCategory;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author Mothusi Molorane
 */
public class AssessmentCategoryBoImpl implements AssessmentCategoryBo{
    
    private final AssessmentCategoryDaoImpl dao = new AssessmentCategoryDaoImpl();

    @Override
    public int AddAssessmentCategory(AssessmentCategory category) {
       return dao.AddAssessmentCategory(category);
    }

    @Override
    public int EditAssessmentCategory(AssessmentCategory category) {
        return dao.EditAssessmentCategory(category);
    }

    @Override
    public int RemoveAssessmentCategory(int categoryId) {
        return dao.RemoveAssessmentCategory(categoryId);
    }

    @Override
    public AssessmentCategory getAssessmentCategory(int categoryId) {
        return dao.getAssessmentCategory(categoryId);
    }

    @Override
    public ArrayList<AssessmentCategory> getAllAssessmentCategories() {
        return dao.getAllAssessmentCategories();
    }
    
    public void fillComboBoxAssessmentCategory(JComboBox con){
        con.removeAllItems();
        getAllAssessmentCategories().forEach((bean) -> {
            con.addItem(bean);
        });
    }
}
