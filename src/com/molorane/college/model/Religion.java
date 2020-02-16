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
public class Religion{
    
    private int religionId;
    private String religion;
    
    public void setReligion(int religionId,String religion) {
        this.religionId = religionId;
        this.religion = religion;
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
    
    @Override
    public String toString(){
        return religion;
    }
}
