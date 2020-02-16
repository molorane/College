/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll.impl;

import com.molorane.college.dao.impl.GradeCategoryDaoImpl;
import com.molorane.college.model.GradeCategory;
import java.util.ArrayList;
import javax.swing.JComboBox;
import com.molorane.college.bll.GradeCategoryBo;

/**
 *
 * @author Mothusi Molorane
 */
public class GradeCategoryBoImpl implements GradeCategoryBo{
    
    private final GradeCategoryDaoImpl dao;

    public GradeCategoryBoImpl() {
        this.dao = new GradeCategoryDaoImpl();
    }

    @Override
    public int AddGradeCategory(GradeCategory category) {
       return dao.AddGradeCategory(category);
    }

    @Override
    public int EditGradeCategory(GradeCategory category) {
        return dao.EditGradeCategory(category);
    }

    @Override
    public int RemoveGradeCategory(int categoryId) {
        return dao.RemoveGradeCategory(categoryId);
    }

    @Override
    public GradeCategory GetGradeCategory(int categoryId) {
        return dao.GetGradeCategory(categoryId);
    }

    @Override
    public ArrayList<GradeCategory> GetAllGradeCategories() {
        return dao.GetAllGradeCategories();
    }
    
    public void fillComboBoxGradeCategory(JComboBox con){
        con.removeAllItems();
        GradeCategory cg = new GradeCategory();
        cg.setGradeCategory(0, "SELECT");
        con.addItem(cg);
        GetAllGradeCategories().forEach((bean) -> {
            con.addItem(bean);
        });
    }
}
