/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.model;

/**
 * @author Mothusi Molorane
 */

public class Assessment extends Bean{
    
    private int personId;
    private String moduleCode;
    private int termId;
    private int assId;
    private double mark;
    
    public void setAssessment(int personId,String moduleCode,int termId,int assId,double mark){
        this.personId = personId;
        this.moduleCode = moduleCode;
        this.termId = termId;
        this.assId = assId;
        this.mark = mark;
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
     * @return the moduleCode
     */
    public String getModuleCode() {
        return moduleCode;
    }

    /**
     * @param moduleCode the moduleCode to set
     */
    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    /**
     * @return the termId
     */
    public int getTermId() {
        return termId;
    }

    /**
     * @param termId the termId to set
     */
    public void setTermId(int termId) {
        this.termId = termId;
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
     * @return the mark
     */
    public double getMark() {
        return mark;
    }

    /**
     * @param mark the mark to set
     */
    public void setMark(double mark) {
        this.mark = mark;
    }
}
