/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.model;


/**
 *
 * @author Mothusi Molorane
 */
public class Qualification{
    
    private int qualificationId;
    private String qualification;
    
    public void setQualification(int qualificationId,String qualification) {
        this.qualificationId = qualificationId;
        this.qualification = qualification;
    }

    /**
     * @return the qualificationId
     */
    public int getQualificationId() {
        return qualificationId;
    }

    /**
     * @param qualificationId the qualificationId to set
     */
    public void setQualificationId(int qualificationId) {
        this.qualificationId = qualificationId;
    }

    /**
     * @return the qualification
     */
    public String getQualification() {
        return qualification;
    }

    /**
     * @param qualification the qualification to set
     */
    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
    
    @Override
    public String toString(){
        return qualification;
    }
}
