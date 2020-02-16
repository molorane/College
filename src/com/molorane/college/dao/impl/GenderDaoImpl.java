/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao.impl;

import com.molorane.college.db.DBConnection;
import com.molorane.college.custom.Functions;
import com.molorane.college.dao.GenderDao;
import com.molorane.college.model.Gender;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public class GenderDaoImpl extends GenderDao{

    @Override
    public int AddGender(Gender gender) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainGender(?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, -1);
            cs.setString(2, gender.getGender());
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(3);
        } catch (SQLException e) {
            Functions.errorMessage("MaintainGender error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int EditGender(Gender gender) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainGender(?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, gender.getGenderId());
            cs.setString(2, gender.getGender());
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(3);
        } catch (SQLException e) {
            Functions.errorMessage("MaintainGender error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int RemoveGender(int genderId) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "CALL RemoveGender(?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, genderId);
            // execute the java preparedstatement
            int count = pst.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("RemoveGender error: "+ e.getLocalizedMessage());
        }
        return 0;
    }

    @Override
    public Gender GetGender(int genderId) {
        try{
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetGender(?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, genderId);
            rs = pst.executeQuery();
            if (rs.next()) {
                return GetGenderDetailsFromResultSet(rs);
            }
        } catch (SQLException e) {
            Functions.errorMessage("getGender error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Gender> GetAllGenders() {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetAllGenders()";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            return GetGenderDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getAllGenders error: "+ e.getLocalizedMessage());
        }
        return null;
    }
    
}
