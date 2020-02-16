/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.model;

/**
 * @author Mothusi Molorane
 */

public class Tuition{
    
    private int receipt_no;
    private long personId;
    private int termId;
    private String transaction_date;
    private int categoryId;
    private int methodId;
    private String ref_no;
    private double debit;
    private double credit;
    private long recordedBy;
    
    private String term;
    private String category;
    private String method;
    
    public void setTuition(int receipt_no,long personId,int termId,String transaction_date,int categoryId,int methodId,String ref_no,
            double debit,double credit,long recordedBy,String term,String category,String method){        
        this.setReceipt_no(receipt_no);
        this.setPersonId(personId);
        this.setTermId(termId);
        this.setTransaction_date(transaction_date);
        this.setCategoryId(categoryId);
        this.setMethodId(methodId);
        this.setRef_no(ref_no);
        this.setDebit(debit);
        this.setCredit(credit);
        this.setRecordedBy(recordedBy);
        this.term = term;
        this.category = category;
        this.method = method;
    }

    /**
     * @return the receipt_no
     */
    public int getReceipt_no() {
        return receipt_no;
    }

    /**
     * @param receipt_no the receipt_no to set
     */
    public void setReceipt_no(int receipt_no) {
        this.receipt_no = receipt_no;
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
     * @return the transaction_date
     */
    public String getTransaction_date() {
        return transaction_date;
    }

    /**
     * @param transaction_date the transaction_date to set
     */
    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    /**
     * @return the categoryId
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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
     * @return the ref_no
     */
    public String getRef_no() {
        return ref_no;
    }

    /**
     * @param ref_no the ref_no to set
     */
    public void setRef_no(String ref_no) {
        this.ref_no = ref_no;
    }

    /**
     * @return the debit
     */
    public double getDebit() {
        return debit;
    }

    /**
     * @param debit the debit to set
     */
    public void setDebit(double debit) {
        this.debit = debit;
    }

    /**
     * @return the credit
     */
    public double getCredit() {
        return credit;
    }

    /**
     * @param credit the credit to set
     */
    public void setCredit(double credit) {
        this.credit = credit;
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

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
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
    
    
}
