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
public class Campus{
    
    private int campusCode;
    private String campusName;
    private String campusABR;
    private int isHQ;
    private String telephone;
    private String fax;
    private String address;
    private String email;
    
    public void setCampus(int campusCode,String campusName,String campusABR,int isHQ,String telephone,String fax,String address,String email){
        this.campusCode = campusCode;
        this.campusName = campusName;
        this.campusABR = campusABR;
        this.isHQ = isHQ;
        this.telephone = telephone;
        this.fax = fax;
        this.address = address;
        this.email = email;
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
     * @return the campusName
     */
    public String getCampusName() {
        return campusName;
    }

    /**
     * @param campusName the campusName to set
     */
    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    /**
     * @return the campusABR
     */
    public String getCampusABR() {
        return campusABR;
    }

    /**
     * @param campusABR the campusABR to set
     */
    public void setCampusABR(String campusABR) {
        this.campusABR = campusABR;
    }

    /**
     * @return the isHQ
     */
    public int getIsHQ() {
        return isHQ;
    }

    /**
     * @param isHQ the isHQ to set
     */
    public void setIsHQ(int isHQ) {
        this.isHQ = isHQ;
    }

    /**
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @return the fax
     */
    public String getFax() {
        return fax;
    }

    /**
     * @param fax the fax to set
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    
    @Override
    public String toString(){
        return campusABR;
    }
}
