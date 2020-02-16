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
public class StudyType{
    
    private int studytypeId;
    private String studytype;
    
    public void setStudyType(int studytypeId,String studytype) {
        this.studytypeId = studytypeId;
        this.studytype = studytype;
    }

    /**
     * @return the studytypeId
     */
    public int getStudyTypeId() {
        return studytypeId;
    }

    /**
     * @param studytypeId the studytypeId to set
     */
    public void setStudyTypeId(int studytypeId) {
        this.studytypeId = studytypeId;
    }

    /**
     * @return the studytype
     */
    public String getStudyType() {
        return studytype;
    }

    /**
     * @param studytype the studytype to set
     */
    public void setStudyType(String studytype) {
        this.studytype = studytype;
    }
    
    @Override
    public String toString(){
        return studytype;
    }
}
