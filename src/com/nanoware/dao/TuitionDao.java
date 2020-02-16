/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao;

import com.nanoware.model.Tuition;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class TuitionDao {
    
    public abstract int AddTuition(Tuition tuition);
    public abstract int EditTuition(Tuition tuition);
    public abstract int RemoveTuition(int receipt_no);    
    public abstract Tuition GetTuition(int receipt_non);
    public abstract ArrayList<Tuition> GetAllStudentTransactions(long personId);
    public abstract List<HashMap<String,Object>> GetStudentTuitionAccount(long personId);
    public abstract List<HashMap<String,Object>> GetStudentTermAccount(long personId, int termId);
    
    // ENLROMENT CONVERSION METHODS
    protected Tuition GetTuitionDetailsFromResultSet(ResultSet rs) throws SQLException{
        Tuition tuition = new Tuition();
        tuition.setTuition(rs.getInt("receipt_no"), 
                        rs.getLong("personId"),
                        rs.getInt("termId"),
                        rs.getString("transaction_date"), 
                        rs.getInt("categoryId"), 
                        rs.getInt("methodId"), 
                        rs.getString("ref_no"),
                        rs.getDouble("debit"),
                        rs.getDouble("credit"),
                        rs.getLong("recordBy"),
                        rs.getString("term"),   
                        rs.getString("category"),   
                        rs.getString("method"));
        return tuition;
    }
    
    protected ArrayList<Tuition> GetTuitionDetailsCollectionFromResultSet(ResultSet rs) throws SQLException{
       ArrayList<Tuition> records = new ArrayList<>();
        while (rs.next())
            records.add(GetTuitionDetailsFromResultSet(rs));
        return records;
    }
}
