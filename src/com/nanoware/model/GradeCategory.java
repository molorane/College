/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.model;

/**
 *
 * @author Mothusi Molorane
 */
public class GradeCategory{
    
    private int gradeId;
    private String gradecategory;
    
    public void setGradeCategory(int gradeId, String gradecategory){
        this.setGradeId(gradeId);
        this.setGradecategory(gradecategory);
    }

    /**
     * @return the gradeId
     */
    public int getGradeId() {
        return gradeId;
    }

    /**
     * @param gradeId the gradeId to set
     */
    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    /**
     * @return the gradecategory
     */
    public String getGradecategory() {
        return gradecategory;
    }

    /**
     * @param gradecategory the gradecategory to set
     */
    public void setGradecategory(String gradecategory) {
        this.gradecategory = gradecategory;
    }
    
    @Override
    public String toString(){
        return gradecategory;
    }    
}
