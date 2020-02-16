/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao.impl;

import com.molorane.college.db.DBConnection;
import com.molorane.college.custom.Functions;
import com.molorane.college.dao.InstitutionDao;
import com.molorane.college.model.Institution;
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
public class InstitutionDaoImpl extends InstitutionDao{

    @Override
    public int AddInstitution(String institution) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainInstitution(?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, -1);
            cs.setString(2, institution);
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(3);
        } catch (SQLException e) {
            Functions.errorMessage("MaintainInstitution error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int EditInstitution(Institution institution) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainInstitution(?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, institution.getInstitutionId());
            cs.setString(2, institution.getInstitution());
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(3);
        } catch (SQLException e) {
            Functions.errorMessage("MaintainInstitution error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int RemoveInstitution(int institutionId) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "CALL RemoveInstitution(?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, institutionId);
            // execute the java preparedstatement
            int count = pst.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("RemoveInstitution error: "+ e.getLocalizedMessage());
        }
        return 0;
    }

    @Override
    public Institution GetInstitution(int institutionId) {
        try{
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetInstitution(?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, institutionId);
            rs = pst.executeQuery();
            if (rs.next()) {
                return GetInstitutionDetailsFromResultSet(rs);
            }
        } catch (SQLException e) {
            Functions.errorMessage("getInstitution error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Institution> GetAllInstitutions() {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetAllInstitutions()";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            return GetInstitutionDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getAllInstitutions error: "+ e.getLocalizedMessage());
        }
        return null;
    }
    
}
