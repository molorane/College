/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll;

import com.molorane.college.model.EnrolmentDateline;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public interface EnrolmentDatelineBo {
    public abstract int AddEnrolmentDateline(EnrolmentDateline enrolmentDateline);
    public abstract int EditEnrolmentDateline(EnrolmentDateline enrolmentDateline);
    public abstract int RemoveEnrolmentDateline(int termId);    
    public abstract EnrolmentDateline GetEnrolmentDateline(int termId); 
    public abstract boolean IsEnrolmentBeforeDateline(int termId);
    public abstract boolean IsEnrolmentBeforeDateline2(String term);
    public abstract ArrayList<EnrolmentDateline> GetAllEnrolmentDatelines();
}
