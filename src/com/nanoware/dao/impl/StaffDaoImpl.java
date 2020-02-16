/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao.impl;

import com.nanoware.bll.Functions;
import com.nanoware.dao.StaffDao;
import com.nanoware.model.Staff;
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
public class StaffDaoImpl extends StaffDao{

    @Override
    public int AddStaff(Staff staff) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainStaff(?,?,?,?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, staff.getContractId());
            cs.setInt(2, staff.getPersonId());
            cs.setInt(3, staff.getRoleId());
            cs.setInt(4, staff.getCampusCode());
            cs.setString(5, staff.getContractFrom());
            cs.setString(6, staff.getContractTo());
            cs.setInt(7, staff.getRecordBy());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("MaintainStaff error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int EditStaff(Staff staff) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainStaff(?,?,?,?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, staff.getContractId());
            cs.setInt(2, staff.getPersonId());
            cs.setInt(3, staff.getRoleId());
            cs.setInt(4, staff.getCampusCode());
            cs.setString(5, staff.getContractFrom());
            cs.setString(6, staff.getContractTo());
            cs.setInt(7, staff.getRecordBy());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("MaintainStaff error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int RemoveStaff(int contractId) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL RemoveStaff(?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, contractId);
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("RemoveStaff error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public Staff GetStaff(int contractId) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetStaff(?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, contractId);
            rs = pst.executeQuery();
            return GetStaffDetailsFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getStaff error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Staff> GetStaffContracts(int lectureId) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetStaffContracts(?)";
            pst = conn.prepareStatement(sql);
            pst.setLong(1, lectureId);
            rs = pst.executeQuery();
            return GetStaffDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getStaffContracts error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Staff> GetCampusStaff(int campusCode) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetCampusStaff(?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, campusCode);
            rs = pst.executeQuery();
            return GetStaffDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("GetCampusStaff error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Staff> GetStaffByCampusAndRole(int campusCode, int roleId) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetStaffByCampusAndRole(?,?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, campusCode);
            pst.setInt(2, roleId);
            rs = pst.executeQuery();
            return GetStaffDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("GetStaffByCampusAndRole error: "+ e.getLocalizedMessage());
        }
        return null;
    }
}
