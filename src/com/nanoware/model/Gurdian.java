/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.model;


/**
 * @author Mothusi Molorane
 */
public class Gurdian{
    
    private long personId;
    private String lastName;
    private String firstName;
    private int relationshipId;
    private String telephone;
    private String cellphone;
    private String address;
    private String postalCode;
    
    // Additional relationship
    private String relationship;
    
    public void setGurdian(long personId,String lastName,String firstName,int relationshipId,String telephone,String cellphone,String address,String postalCode,String relationship){
        this.personId = personId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.relationshipId = relationshipId;
        this.telephone = telephone;
        this.cellphone = cellphone;
        this.address = address;
        this.postalCode = postalCode;
        this.relationship = relationship;
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
     * @return the relationshipId
     */
    public int getRelationshipId() {
        return relationshipId;
    }

    /**
     * @param relationshipId the relationshipId to set
     */
    public void setRelationshipId(int relationshipId) {
        this.relationshipId = relationshipId;
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
     * @return the cellphone
     */
    public String getCellphone() {
        return cellphone;
    }

    /**
     * @param cellphone the cellphone to set
     */
    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
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
     * @return the postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode the postalCode to set
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }    

    /**
     * @return the relationship
     */
    public String getRelationship() {
        return relationship;
    }

    /**
     * @param relationship the relationship to set
     */
    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
}
