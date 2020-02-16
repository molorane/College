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
public class Matric {
    
    private String idno;
    private String lastName;
    private String firstName;
    private int myear;
    private String subject;
    private String term1;
    private String term2;
    private String term3;
    
    public void setMatric(String idno,String lastName,String firstName,int myear,String subject,
            String term1,String term2,String term3){
        this.idno = idno;
        this.lastName = lastName;
        this.firstName = firstName;
        this.myear = myear;
        this.subject = subject;
        this.setTerm1(term1);
        this.setTerm2(term2);
        this.setTerm3(term3);
    }
    
    public void setMatric(String idno, int myear, String subject, String term1, String term2,String term3){
        this.idno = idno;
        this.myear = myear;
        this.subject = subject;
        this.setTerm1(term1);
        this.setTerm2(term2);
        this.setTerm3(term3);
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
     * @return the myear
     */
    public int getMyear() {
        return myear;
    }

    /**
     * @param myear the myear to set
     */
    public void setMyear(int myear) {
        this.myear = myear;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the term1
     */
    public String getTerm1() {
        return term1;
    }

    /**
     * @param term1 the term1 to set
     */
    public void setTerm1(String term1) {
        this.term1 = term1;
    }

    /**
     * @return the term2
     */
    public String getTerm2() {
        return term2;
    }

    /**
     * @param term2 the term2 to set
     */
    public void setTerm2(String term2) {
        this.term2 = term2;
    }

    /**
     * @return the term3
     */
    public String getTerm3() {
        return term3;
    }

    /**
     * @param term3 the term3 to set
     */
    public void setTerm3(String term3) {
        this.term3 = term3;
    }
    
}
