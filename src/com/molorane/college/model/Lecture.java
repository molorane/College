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
public class Lecture {
    
    private int lmId;
    private int lectureId;
    private int campusCode;
    private int termId;
    private String moduleCode;
    
    private String campus;
    private String term;
    private String module;
    
    public void setLecture(int lmId,int lectureId,int campusCode,int termId,String moduleCode,
            String campus,String term,String module){
        this.lmId = lmId;
        this.lectureId = lectureId;
        this.campusCode = campusCode;
        this.termId = termId;
        this.moduleCode = moduleCode;
        this.campus = campus;
        this.term = term;
        this.module = module;
    }

    /**
     * @return the lmId
     */
    public int getLmId() {
        return lmId;
    }

    /**
     * @param lmId the lmId to set
     */
    public void setLmId(int lmId) {
        this.lmId = lmId;
    }

    /**
     * @return the lectureId
     */
    public int getLectureId() {
        return lectureId;
    }

    /**
     * @param lectureId the lectureId to set
     */
    public void setLectureId(int lectureId) {
        this.lectureId = lectureId;
    }

    /**
     * @return the campusCode
     */
    public int getCampusCode() {
        return campusCode;
    }

    /**
     * @param campusCode the campusCode to set
     */
    public void setCampusCode(int campusCode) {
        this.campusCode = campusCode;
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
     * @return the campus
     */
    public String getCampus() {
        return campus;
    }

    /**
     * @param campus the campus to set
     */
    public void setCampus(String campus) {
        this.campus = campus;
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

    /**
     * @return the module
     */
    public String getModule() {
        return module;
    }

    /**
     * @param module the module to set
     */
    public void setModule(String module) {
        this.module = module;
    }
    
    
    
}
