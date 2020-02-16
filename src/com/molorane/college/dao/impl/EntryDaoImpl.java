/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao.impl;

import com.molorane.college.custom.Functions;
import com.molorane.college.dao.EntryDao;
import com.molorane.college.db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mothusi Molorane
 */
public class EntryDaoImpl extends EntryDao{

    @Override
    public List<HashMap<String, Object>> GetMarkEntry(int termId, int campusCode) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetMarkEntry(?,?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, termId);
            pst.setInt(2, campusCode);
            rs = pst.executeQuery();
            return Functions.convertResultsetToList(rs);
        } catch (SQLException e) {
            Functions.errorMessage("GetMarkEntry error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public List<HashMap<String, Object>> GetExamEntry(int termId, int campusCode) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetExamEntry(?,?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, termId);
            pst.setInt(2, campusCode);
            rs = pst.executeQuery();
            return Functions.convertResultsetToList(rs);
        } catch (SQLException e) {
            Functions.errorMessage("GetExamEntry error: "+ e.getLocalizedMessage());
        }
        return null;
    }
    
}
