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
public class EnrolementDateline {
    
    
    private int termId;
    private  String dateline;
    
    private void setEnrolementDateline(int termId,String dateline){
        this.termId = termId;
        this.dateline = dateline;
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
     * @return the dateline
     */
    public String getDateline() {
        return dateline;
    }

    /**
     * @param dateline the dateline to set
     */
    public void setDateline(String dateline) {
        this.dateline = dateline;
    }
}
