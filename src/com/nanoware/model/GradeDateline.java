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
public class GradeDateline{
    
    private int termId;
    private  String dateline;
    
    private String term;
    
    public void setGradeDateline(int termId,String dateline,String term){
        this.termId = termId;
        this.dateline = dateline;
        this.term = term;
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

    /**
     * @return the term
     */
    public String getTerm() {
        return term;
    }

    /**
     * @param term the term to set
     */
    public void setTerm(String term) {
        this.term = term;
    }
    
    
}
