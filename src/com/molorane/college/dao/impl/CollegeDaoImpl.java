/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao.impl;

import com.molorane.college.custom.Functions;
import com.molorane.college.dao.CollegeDao;
import com.molorane.college.db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Mothusi Molorane
 */
public class CollegeDaoImpl extends CollegeDao{

    @Override
    public int GetCurrentYear() {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetCurrentYear()";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                return Integer.parseInt(rs.getString("currYear"));
            }
        } catch (SQLException e) {
            Functions.errorMessage("GetCurrentYear error: "+ e.getLocalizedMessage());
        }
        return 0;
    }
    
}
