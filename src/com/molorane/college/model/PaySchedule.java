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
public class PaySchedule{
    
    private int payscheduleId;
    private long personId;
    private int termId;
    private String scheduledate;
    private double amount;
    private long recordedBy;
    
    private String term;
    
    
    public void setPaySchedule(int payscheduleId,long personId,int termId,String scheduledate,
            double amount,long recordedBy,String term){
        this.setPayscheduleId(payscheduleId);
        this.setPersonId(personId);
        this.setTermId(termId);
        this.setScheduledate(scheduledate);
        this.setAmount(amount);
        this.setRecordedBy(recordedBy);
        this.setTerm(term);
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
     * @return the termId
     */
    public int getTermId() {
        return termId;
    }

    /**
     * @param termId the termId to set
     */
    public void setTermId(int termId) {
        this.termId = termId;
    }

    /**
     * @return the scheduledate
     */
    public String getScheduledate() {
        return scheduledate;
    }

    /**
     * @param scheduledate the scheduledate to set
     */
    public void setScheduledate(String scheduledate) {
        this.scheduledate = scheduledate;
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
     * @return the recordedBy
     */
    public long getRecordedBy() {
        return recordedBy;
    }

    /**
     * @param recordedBy the recordedBy to set
     */
    public void setRecordedBy(long recordedBy) {
        this.recordedBy = recordedBy;
    }

    /**
     * @return the term
     */
    public String getTerm() {
        return term;
    }

    /**
     * @param term the term to set
     */
    public void setTerm(String term) {
        this.term = term;
    } 
}
