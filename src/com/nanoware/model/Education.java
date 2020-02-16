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
public class Education{
    
    private int eduId;
    private long personId;
    private int institutionId;
    private int qualificationId;
    private int yearObtained;
    private String skills;
    private String details;
    
    // Additional
    private String institution;
    private String qualification;
    
    public void setEducation(int eduId,long personId,int institutionId,int qualificationId,int yearObtained,String skills,String details,String institution,String qualification){
        this.eduId = eduId;
        this.personId = personId;
        this.institutionId = institutionId;
        this.qualificationId = qualificationId;
        this.yearObtained = yearObtained;
        this.skills = skills;
        this.details = details;
        this.institution = institution;
        this.qualification = qualification;
    }
    
    
    /**
     * @return the eduId
     */
    public int getEduId() {
        return eduId;
    }

    /**
     * @param eduId the eduId to set
     */
    public void setEduId(int eduId) {
        this.eduId = eduId;
    }

    /**
     * @return the personId
     */
    public long getPersonId() {
        return personId;
    }

    /**
     * @param personId the personId to set
     */
    public void setPersonId(long personId) {
        this.personId = personId;
    }

    /**
     * @return the institutionId
     */
    public int getInstitutionId() {
        return institutionId;
    }

    /**
     * @param institutionId the institutionId to set
     */
    public void setInstitutionId(int institutionId) {
        this.institutionId = institutionId;
    }

    /**
     * @return the qualificationId
     */
    public int getQualificationId() {
        return qualificationId;
    }

    /**
     * @param qualificationId the qualificationId to set
     */
    public void setQualificationId(int qualificationId) {
        this.qualificationId = qualificationId;
    }

    /**
     * @return the yearObtained
     */
    public int getYearObtained() {
        return yearObtained;
    }

    /**
     * @param yearObtained the yearObtained to set
     */
    public void setYearObtained(int yearObtained) {
        this.yearObtained = yearObtained;
    }

    /**
     * @return the skills
     */
    public String getSkills() {
        return skills;
    }

    /**
     * @param skills the skills to set
     */
    public void setSkills(String skills) {
        this.skills = skills;
    }

    /**
     * @return the details
     */
    public String getDetails() {
        return details;
    }

    /**
     * @param details the details to set
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * @return the institution
     */
    public String getInstitution() {
        return institution;
    }

    /**
     * @param institution the institution to set
     */
    public void setInstitution(String institution) {
        this.institution = institution;
    }

    /**
     * @return the qualification
     */
    public String getQualification() {
        return qualification;
    }

    /**
     * @param qualification the qualification to set
     */
    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

}
