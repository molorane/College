/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll;

import com.nanoware.model.Term;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class TermBo {
    public abstract int AddTerm(Term term);
    public abstract int EditTerm(Term term);
    public abstract int RemoveTerm(int termId);    
    public abstract Term GetTerm(int termId);    
    public abstract ArrayList<Term> GetAllTerms();
    public abstract ArrayList<Term> GetTermsByYear(int year);
    public abstract ArrayList<Term> GetTermsByDepartment(String dept);
    public abstract ArrayList<Term> GetTermsByYearAndDepartment(int year,String dept);
    public abstract List<HashMap<String, Object>> GetAllTermYears();
}
