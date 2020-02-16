/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao.impl;

import com.molorane.college.custom.Functions;
import com.molorane.college.dao.PinDao;
import com.molorane.college.db.DBConnection;
import com.molorane.college.model.Pin;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Mothusi Molorane
 */
public class PinDaoImpl extends PinDao{

    @Override
    public int AddPin(Pin pin) {
       try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainPin(?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, pin.getPersonId());
            cs.setString(2, pin.getPin());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("MaintainPin error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int EditPin(Pin pin) {
       try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainPin(?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, pin.getPersonId());
            cs.setString(2, pin.getPin());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("MaintainPin error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int RemovePin(int personId) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "CALL RemovePin(?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, personId);
            int count = pst.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("RemovePin error: "+ e.getLocalizedMessage());
        }
        return 0;
    }

    @Override
    public Pin GetPin(int personId) {
        try{
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetPin(?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, personId);
            rs = pst.executeQuery();
            if(rs.next())
                return GetPinDetailsFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("GetPin error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public boolean validatePin(String pin) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL validatePin(?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, pin);
            rs = pst.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            Functions.errorMessage("validatePin error: "+ e.getLocalizedMessage());
        }
        return false;
    }

    @Override
    public boolean IsAdminPinValid(int personId, String pin) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL IsAdminPinValid(?,?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, personId);
            pst.setString(2, pin);
            rs = pst.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            Functions.errorMessage("IsAdminPinValid error: "+ e.getLocalizedMessage());
        }
        return false;
    }

    @Override
    public boolean updateAdminPin(int personId, String pin) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL updateAdminPin(?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, personId);
            cs.setString(2, pin);
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(3) > 0;
        } catch (SQLException e) {
            Functions.errorMessage("updateAdminPin error: "+ e.getLocalizedMessage());
        }        
        return false;
    }
}
