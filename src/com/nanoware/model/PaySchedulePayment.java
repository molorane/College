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
public class PaySchedulePayment{
    
    private int pspId;
    private int payscheduleId;
    private double amount;
    private String paymentdate;
    
    public void setPaySchedulePayment(int pspId,int payscheduleId,double amount,String paymentdate){
        this.setPspId(pspId);
        this.setPayscheduleId(payscheduleId);
        this.setAmount(amount);
        this.setPaymentdate(paymentdate);
    }

    /**
     * @return the pspId
     */
    public int getPspId() {
        return pspId;
    }

    /**
     * @param pspId the pspId to set
     */
    public void setPspId(int pspId) {
        this.pspId = pspId;
    }

    /**
     * @return the payscheduleId
     */
    public int getPayscheduleId() {
        return payscheduleId;
    }

    /**
     * @param payscheduleId the payscheduleId to set
     */
    public void setPayscheduleId(int payscheduleId) {
        this.payscheduleId = payscheduleId;
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @return the paymentdate
     */
    public String getPaymentdate() {
        return paymentdate;
    }

    /**
     * @param paymentdate the paymentdate to set
     */
    public void setPaymentdate(String paymentdate) {
        this.paymentdate = paymentdate;
    }
}
