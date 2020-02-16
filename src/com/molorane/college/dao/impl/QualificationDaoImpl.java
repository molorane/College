/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao.impl;

import com.molorane.college.db.DBConnection;
import com.molorane.college.custom.Functions;
import com.molorane.college.dao.QualificationDao;
import com.molorane.college.model.Qualification;
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
public class QualificationDaoImpl extends QualificationDao{

    @Override
    public int AddQualification(Qualification qualification) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainQualification(?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, -1);
            cs.setString(2, qualification.getQualification());
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(3);
        } catch (SQLException e) {
            Functions.errorMessage("MaintainQualification error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int EditQualification(Qualification qualification) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainQualification(?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, qualification.getQualificationId());
            cs.setString(2, qualification.getQualification());
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(3);
        } catch (SQLException e) {
            Functions.errorMessage("MaintainQualification error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int RemoveQualification(int qualificationId) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "CALL RemoveQualification(?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, qualificationId);
            // execute the java preparedstatement
            int count = pst.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("RemoveQualification error: "+ e.getLocalizedMessage());
        }
        return 0;
    }

    @Override
    public Qualification GetQualification(int qualificationId) {
        try{
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetQualification(?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, qualificationId);
            rs = pst.executeQuery();
            if(rs.next())
                return GetQualificationDetailsFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getQualification error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Qualification> GetAllQualifications() {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetAllQualifications()";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            return GetQualificationDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getAllQualifications error: "+ e.getLocalizedMessage());
        }
        return null;
    }
    
}
