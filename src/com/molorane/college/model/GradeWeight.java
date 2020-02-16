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
public class GradeWeight {
    
    private int gwId;
    private String courseCode;
    private int termId;
    private int gradeId;
    private double weight;
    private int recordBy;
    
    private String term;
    private String grade;
    
    public void setGradeWeight(int gwId,String courseCode,int termId,int gradeId,double weight,
            int recordBy,String term,String grade){
        this.gwId = gwId;
        this.courseCode = courseCode;
        this.termId = termId;
        this.gradeId = gradeId;
        this.weight = weight;
        this.recordBy = recordBy;
        this.term = term;
        this.grade = grade;
    }

    /**
     * @return the gwId
     */
    public int getGwId() {
        return gwId;
    }

    /**
     * @param gwId the gwId to set
     */
    public void setGwId(int gwId) {
        this.gwId = gwId;
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
     * @return the weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * @return the recordBy
     */
    public int getRecordBy() {
        return recordBy;
    }

    /**
     * @param recordBy the recordBy to set
     */
    public void setRecordBy(int recordBy) {
        this.recordBy = recordBy;
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
