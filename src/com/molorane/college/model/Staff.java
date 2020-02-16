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
public class Staff{
    
    private int contractId;
    private int personId;
    private int roleId;
    private int campusCode;
    private String contractFrom;
    private String contractTo;
    private String recordDate;
    private int recordBy;
    
    private String firstName;
    private String lastName;
    private String otherName;
    private String role;
    private String campus;
    
    public void setStaff(int contractId,int personId,int roleId,int campusCode,String contractFrom,String contractTo
    ,String recordDate,int recordBy,String firstName,String lastName,String otherName,String role,String campus){
        this.contractId = contractId;
        this.personId = personId;
        this.roleId = roleId;
        this.campusCode = campusCode;
        this.contractFrom = contractFrom;
        this.contractTo = contractTo;
        this.recordDate = recordDate;
        this.recordBy = recordBy;
        this.firstName = firstName;
        this.lastName = lastName;
        this.otherName = otherName;
        this.role = role;
        this.campus = campus;
    }

    /**
     * @return the contractId
     */
    public int getContractId() {
        return contractId;
    }

    /**
     * @param contractId the contractId to set
     */
    public void setContractId(int contractId) {
        this.contractId = contractId;
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
     * @return the roleId
     */
    public int getRoleId() {
        return roleId;
    }

    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(int roleId) {
        this.roleId = roleId;
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
     * @return the contractFrom
     */
    public String getContractFrom() {
        return contractFrom;
    }

    /**
     * @param contractFrom the contractFrom to set
     */
    public void setContractFrom(String contractFrom) {
        this.contractFrom = contractFrom;
    }

    /**
     * @return the contractTo
     */
    public String getContractTo() {
        return contractTo;
    }

    /**
     * @param contractTo the contractTo to set
     */
    public void setContractTo(String contractTo) {
        this.contractTo = contractTo;
    }

    /**
     * @return the recordDate
     */
    public String getRecordDate() {
        return recordDate;
    }

    /**
     * @param recordDate the recordDate to set
     */
    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
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
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the otherName
     */
    public String getOtherName() {
        return otherName;
    }

    /**
     * @param otherName the otherName to set
     */
    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
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
