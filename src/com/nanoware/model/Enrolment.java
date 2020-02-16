/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.model;


/**
 * @author Mothusi Molorane
 */
public class Enrolment{
    
    private int personId;
    private String courseCode;
    private String moduleCode;
    private int termId;
    private int studytypeId;
    private int campusCode;
    private Double marks;
    
    // Additional
    private String course;
    private String module;
    private String term;
    private String studytype;
    private String campus;
    
    public void setEnrolment(int personId,String courseCode,String moduleCode,int termId,int studytypeId,int campusCode,Double marks,String course,String module,String term,String studytype,String campus){
        this.setPersonId(personId);
        this.setCourseCode(courseCode);
        this.setModuleCode(moduleCode);
        this.setTermId(termId);
        this.setStudytypeId(studytypeId);
        this.setMarks(marks);
        this.setCampusCode(campusCode);
        this.setCourse(course);
        this.setModule(module);
        this.setTerm(term);
        this.setStudytype(studytype);
        this.setCampus(campus);
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
     * @return the courseCode
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * @param courseCode the courseCode to set
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
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
     * @return the studytypeId
     */
    public int getStudytypeId() {
        return studytypeId;
    }

    /**
     * @param studytypeId the studytypeId to set
     */
    public void setStudytypeId(int studytypeId) {
        this.studytypeId = studytypeId;
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
     * @return the marks
     */
    public Double getMarks() {
        return marks;
    }

    /**
     * @param marks the marks to set
     */
    public void setMarks(Double marks) {
        this.marks = marks;
    }

    /**
     * @return the course
     */
    public String getCourse() {
        return course;
    }

    /**
     * @param course the course to set
     */
    public void setCourse(String course) {
        this.course = course;
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
     * @return the studytype
     */
    public String getStudytype() {
        return studytype;
    }

    /**
     * @param studytype the studytype to set
     */
    public void setStudytype(String studytype) {
        this.studytype = studytype;
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
}
