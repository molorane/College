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
public class ResultRemark{
    
    private String resultremarkId;
    private String resultremark;
    
    public void setResultRemark(String resultremarkId,String resultremark) {
        this.resultremarkId = resultremarkId;
        this.resultremark = resultremark;
    }

    /**
     * @return the resultremarkId
     */
    public String getResultRemarkId() {
        return resultremarkId;
    }

    /**
     * @param resultremarkId the resultremarkId to set
     */
    public void setResultRemarkId(String resultremarkId) {
        this.resultremarkId = resultremarkId;
    }

    /**
     * @return the resultremark
     */
    public String getResultRemark() {
        return resultremark;
    }

    /**
     * @param resultremark the resultremark to set
     */
    public void setResultRemark(String resultremark) {
        this.resultremark = resultremark;
    }
    
    @Override
    public String toString(){
        return resultremark;
    }
}
