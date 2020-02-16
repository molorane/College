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
public class Institution{
    
    private int institutionId;
    private String institution;
    
    public void setInstitution(int institutionId,String institution) {
        this.institutionId = institutionId;
        this.institution = institution;
    }

    /**
     * @return the insitutionId
     */
    public int getInstitutionId() {
        return institutionId;
    }

    /**
     * @param insitutionId the insitutionId to set
     */
    public void setInsitutionId(int institutionId) {
        this.institutionId = institutionId;
    }

    /**
     * @return the institution
     */
    public String getInstitution() {
        return institution;
    }

    /**
     * @param institution the institution to set
     */
    public void setInstitution(String institution) {
        this.institution = institution;
    }
    
    @Override
    public String toString(){
        return institution;
    }
}
