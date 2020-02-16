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
public class TestTimeTable {
    
    private int ttId;
    private int campusCode;
    private String moduleCode;
    private int termId;
    private int gradeId;
    private String testDate;
    private String startTime;
    private String duration;
    private String venue;
    
    private String campus;
    private String module;
    private String term;
    private String grade;
    
    public void setTestTimeTable(int ttId,int campusCode,String moduleCode,int termId,
            int gradeId,String testDate,String startTime,String duration,String venue,
            String campus,String module,String term,String grade){
        this.ttId = ttId;
        this.campusCode = campusCode;
        this.moduleCode = moduleCode;
        this.termId = termId;
        this.gradeId = gradeId;
        this.testDate = testDate;
        this.startTime = startTime;
        this.duration = duration;
        this.venue = venue;
        this.campus = campus;
        this.module = module;
        this.term = term;
        this.grade = grade;
    }

    /**
     * @return the ttId
     */
    public int getTtId() {
        return ttId;
    }

    /**
     * @param ttId the ttId to set
     */
    public void setTtId(int ttId) {
        this.ttId = ttId;
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
    public int getGradeId() {
        return gradeId;
    }

    /**
     * @param gradeId the gradeId to set
     */
    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    /**
     * @return the testDate
     */
    public String getTestDate() {
        return testDate;
    }

    /**
     * @param testDate the testDate to set
     */
    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }

    /**
     * @return the startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * @return the venue
     */
    public String getVenue() {
        return venue;
    }

    /**
     * @param venue the venue to set
     */
    public void setVenue(String venue) {
        this.venue = venue;
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
     * @return the grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * @param grade the grade to set
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }
    
    
}
