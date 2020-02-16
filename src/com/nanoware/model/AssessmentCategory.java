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
public class AssessmentCategory extends Bean{
    
    private int assId;
    private String asscategory;
    
    public void setAssessmentCategory(int assId, String assCategory){
        this.assId = assId;
        this.asscategory = assCategory;
    }

    /**
     * @return the assId
     */
    public int getAssId() {
        return assId;
    }

    /**
     * @param assId the assId to set
     */
    public void setAssId(int assId) {
        this.assId = assId;
    }

    /**
     * @return the asscategory
     */
    public String getAsscategory() {
        return asscategory;
    }

    /**
     * @param asscategory the asscategory to set
     */
    public void setAsscategory(String asscategory) {
        this.asscategory = asscategory;
    }
    
    @Override
    public String toString(){
        return asscategory;
    }
}
