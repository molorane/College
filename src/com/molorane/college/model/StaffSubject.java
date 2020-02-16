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
public class StaffSubject {
    
    private int personId;
    private String subjectCode;
    private int syear;
    
    private String fullNames;
    
    public void setStaffSubject(int personId,String subjectCode,int syear, String fullNames){
        this.setPersonId(personId);
        this.setSubjectCode(subjectCode);
        this.setSyear(syear);
        this.setFullNames(fullNames);
    }

    /**
     * @return the personId
     */
    public int getPersonId() {
        return personId;
    }

    /**
     * @param personId the personId to set
     */
    public void setPersonId(int personId) {
        this.personId = personId;
    }

    /**
     * @return the subjectCode
     */
    public String getSubjectCode() {
        return subjectCode;
    }

    /**
     * @param subjectCode the subjectCode to set
     */
    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    /**
     * @return the syear
     */
    public int getSyear() {
        return syear;
    }

    /**
     * @param syear the syear to set
     */
    public void setSyear(int syear) {
        this.syear = syear;
    }

    /**
     * @return the fullNames
     */
    public String getFullNames() {
        return fullNames;
    }

    /**
     * @param fullNames the fullNames to set
     */
    public void setFullNames(String fullNames) {
        this.fullNames = fullNames;
    }
    
    
}
