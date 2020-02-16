/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao;

import com.nanoware.model.Term;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class TermDao {
    
    public abstract int AddTerm(Term term);
    public abstract int EditTerm(Term term);
    public abstract int RemoveTerm(int termId);    
    public abstract Term GetTerm(int termId);    
    public abstract ArrayList<Term> GetAllTerms();
    public abstract ArrayList<Term> GetTermsByYear(int year);
    public abstract ArrayList<Term> GetTermsByDepartment(String dept);
    public abstract ArrayList<Term> GetTermsByYearAndDepartment(int year,String dept);
    public abstract List<HashMap<String, Object>> GetAllTermYears();
    
    // DURATION CONVERSION METHODS
    public Term GetTermDetailsFromResultSet(ResultSet rs) throws SQLException{
        Term term = new Term();
        term.setTerm(rs.getInt("termId"), 
                        rs.getString("term"));
        return term;
    }
    
    public ArrayList<Term> GetTermDetailsCollectionFromResultSet(ResultSet rs) throws SQLException{
       ArrayList<Term> terms = new ArrayList<>();
        while (rs.next())
            terms.add(GetTermDetailsFromResultSet(rs));
        return terms;
    }
}
