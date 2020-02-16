/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao.impl;

import com.nanoware.bll.Functions;
import com.nanoware.dao.CampusDao;
import com.nanoware.model.Campus;
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
public class CampusDaoImpl extends CampusDao{

    @Override
    public int AddCampus(Campus campus) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainCampus(?,?,?,?,?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, campus.getCampusCode());
            cs.setString(2, campus.getCampusName());
            cs.setString(3, campus.getCampusABR());
            cs.setInt(4, campus.getIsHQ());
            cs.setString(5, campus.getTelephone());
            cs.setString(6, campus.getFax());
            cs.setString(7, campus.getAddress());
            cs.setString(8, campus.getEmail());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("AddCampus error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int EditCampus(Campus campus) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainCampus(?,?,?,?,?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, campus.getCampusCode());
            cs.setString(2, campus.getCampusName());
            cs.setString(3, campus.getCampusABR());
            cs.setInt(4, campus.getIsHQ());
            cs.setString(5, campus.getTelephone());
            cs.setString(6, campus.getFax());
            cs.setString(7, campus.getAddress());
            cs.setString(8, campus.getEmail());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("EditCampus error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int RemoveCampus(int campusCode) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL RemoveCampus(?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, campusCode);
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("RemoveCampus error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public Campus GetCampus(int campusCode) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL getCampus(?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, campusCode);
            rs = pst.executeQuery();
            if (rs.next()) {
                return GetCampusDetailsFromResultSet(rs);
            }
        } catch (SQLException e) {
            Functions.errorMessage("getCampus error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Campus> GetAllCampuses() {
         try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL getAllCampuses()";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            return GetCampusDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getAllCampuses error: "+ e.getLocalizedMessage());
        }
        return null;
    }
}
