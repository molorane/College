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
public class PaymentMethod{
    
    private int methodId;
    private String method;
    
    public void setPaymentMethod(int methodId,String method) {
        this.methodId = methodId;
        this.method = method;
    }

    /**
     * @return the methodId
     */
    public int getMethodId() {
        return methodId;
    }

    /**
     * @param methodId the methodId to set
     */
    public void setMethodId(int methodId) {
        this.methodId = methodId;
    }

    /**
     * @return the method
     */
    public String getMethod() {
        return method;
    }

    /**
     * @param method the method to set
     */
    public void setMethod(String method) {
        this.method = method;
    }
    
    @Override
    public String toString(){
        return method;
    } 
}
