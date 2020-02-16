/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.model;

/**
 * @author Mothusi Molorane
 */
public class Person{
    
    private long personId;
    private String idno;
    private String passport;
    private String firstName;
    private String lastName;
    private String otherName;
    private int genderId;
    private int titleId;
    private String initials;
    private String dob;
    private int raceId;
    private int nationalityId;
    private int languageId;
    private int religionId;
    private String address;
    private String postalCode;
    private String telephone;
    private String fax;
    private String cellphone;
    private String email;
    
    // Additional
    private String gender;
    private String title;
    private String race;
    private String nationality;
    private String language;
    private String religion;
    
    public void setPerson(long personId,String idno,String passport,String firstName,String lastName,
        String otherName,int genderId,int titleId,String initials,String dob,int raceId,int nationalityId,
        int languageId,int religionId,String address,String postalCode,String telephone,
        String fax,String cellphone,String email,String gender,String title,String race,
        String nationality,String language,String religion){
        
        this.personId = personId;
        this.idno = idno;
        this.passport = passport;
        this.firstName = firstName;
        this.lastName = lastName;
        this.otherName = otherName;
        this.genderId = genderId;
        this.titleId =titleId;
        this.initials = initials;
        this.dob = dob;
        this.raceId = raceId;
        this.nationalityId = nationalityId;
        this.languageId = languageId;
        this.religionId = religionId;
        this.address = address;
        this.postalCode = postalCode;
        this.telephone = telephone;
        this.fax = fax;
        this.cellphone = cellphone;
        this.email = email;
        this.gender = gender;
        this.title = title;
        this.race = race;
        this.nationality = nationality;
        this.language = language;
        this.religion = religion;
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
     * @return the idno
     */
    public String getIdno() {
        return idno;
    }

    /**
     * @param idno the idno to set
     */
    public void setIdno(String idno) {
        this.idno = idno;
    }

    /**
     * @return the passport
     */
    public String getPassport() {
        return passport;
    }

    /**
     * @param passport the passport to set
     */
    public void setPassport(String passport) {
        this.passport = passport;
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
     * @return the genderId
     */
    public int getGenderId() {
        return genderId;
    }

    /**
     * @param genderId the genderId to set
     */
    public void setGenderId(int genderId) {
        this.genderId = genderId;
    }

    /**
     * @return the titleId
     */
    public int getTitleId() {
        return titleId;
    }

    /**
     * @param titleId the titleId to set
     */
    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    /**
     * @return the initials
     */
    public String getInitials() {
        return initials;
    }

    /**
     * @param initials the initials to set
     */
    public void setInitials(String initials) {
        this.initials = initials;
    }

    /**
     * @return the dob
     */
    public String getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     * @return the raceId
     */
    public int getRaceId() {
        return raceId;
    }

    /**
     * @param raceId the raceId to set
     */
    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    /**
     * @return the nationalityId
     */
    public int getNationalityId() {
        return nationalityId;
    }

    /**
     * @param nationalityId the nationalityId to set
     */
    public void setNationalityId(int nationalityId) {
        this.nationalityId = nationalityId;
    }

    /**
     * @return the languageId
     */
    public int getLanguageId() {
        return languageId;
    }

    /**
     * @param languageId the homeLanguageId to set
     */
    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    /**
     * @return the religionId
     */
    public int getReligionId() {
        return religionId;
    }

    /**
     * @param religionId the religionId to set
     */
    public void setReligionId(int religionId) {
        this.religionId = religionId;
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

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the race
     */
    public String getRace() {
        return race;
    }

    /**
     * @param race the race to set
     */
    public void setRace(String race) {
        this.race = race;
    }

    /**
     * @return the nationality
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * @param nationality the nationality to set
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * @return the homeLanguage
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language the language to set
     */
    public void setHomeLanguage(String language) {
        this.language = language;
    }

    /**
     * @return the religion
     */
    public String getReligion() {
        return religion;
    }

    /**
     * @param religion the religion to set
     */
    public void setReligion(String religion) {
        this.religion = religion;
    }
}
