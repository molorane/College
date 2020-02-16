/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll;

import com.nanoware.model.Tuition;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mothusi Molorane
 */
public interface TuitionBo {
    public abstract int AddTuition(Tuition tuition);
    public abstract int EditTuition(Tuition tuition);
    public abstract int RemoveTuition(int receipt_no);    
    public abstract Tuition GetTuition(int receipt_non);
    public abstract ArrayList<Tuition> GetAllStudentTransactions(long personId);
    public abstract List<HashMap<String,Object>> GetStudentTuitionAccount(long personId);
    public abstract List<HashMap<String,Object>> GetStudentTermAccount(long personId, int termId);
}
