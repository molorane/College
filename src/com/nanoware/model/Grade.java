/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.model;

/**
 * @author Mothusi Molorane
 */

public class Grade{
    
    private int personId;
    private String moduleCode;
    private int termId;
    private int gradeId;
    private double mark;
    
    public void setGrade(int personId,String moduleCode,int termId,int gradeId,double mark){
        this.personId = personId;
        this.moduleCode = moduleCode;
        this.termId = termId;
        this.gradeId = gradeId;
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
     * @return the gradeId
     */
    public int getAssId() {
        return gradeId;
    }

    /**
     * @param gradeId the gradeId to set
     */
    public void setAssId(int gradeId) {
        this.gradeId = gradeId;
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
