/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao;

import com.nanoware.model.ResultRemark;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class ResultRemarkDao {
    
    public abstract int AddResultRemark(ResultRemark resultremark);
    public abstract int EditResultRemark(ResultRemark resultremark);
    public abstract int RemoveResultRemark(String resultremarkId);    
    public abstract ResultRemark GetResultRemark(String resultremarkId);    
    public abstract ArrayList<ResultRemark> GetAllResultRemarks();
    
    // RACE CONVERSION METHODS
    protected ResultRemark GetResultRemarkDetailsFromResultSet(ResultSet rs) throws SQLException{
        ResultRemark resultremark = new ResultRemark();
        resultremark.setResultRemark(rs.getString("resultremarkId"), 
                        rs.getString("resultremark"));
        return resultremark;
    }
    
    protected ArrayList<ResultRemark> GetResultRemarkDetailsCollectionFromResultSet(ResultSet rs) throws SQLException{
       ArrayList<ResultRemark> resultremarks = new ArrayList<>();
        while (rs.next())
            resultremarks.add(GetResultRemarkDetailsFromResultSet(rs));
        return resultremarks;
    }
}
