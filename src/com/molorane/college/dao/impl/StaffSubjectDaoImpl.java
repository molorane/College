/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao.impl;

import com.molorane.college.custom.Functions;
import com.molorane.college.dao.StaffSubjectDao;
import com.molorane.college.db.DBConnection;
import com.molorane.college.model.StaffSubject;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mothusi Molorane
 */
public class StaffSubjectDaoImpl extends StaffSubjectDao{

    @Override
    public int AddStaffSubject(StaffSubject staffSubject) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL AddStaffSubject(?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, staffSubject.getPersonId());
            cs.setString(2, staffSubject.getSubjectCode());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.SQLException(e);
        }        
        return 0;
    }

    @Override
    public int EditStaffSubject(StaffSubject staffSubject) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainStaffSubject(?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, staffSubject.getPersonId());
            cs.setString(2, staffSubject.getSubjectCode());
            cs.setInt(3, staffSubject.getSyear());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("MaintainStaffSubject error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int RemoveStaffSubject(StaffSubject staffSubject) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL RemoveStaffSubject(?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, staffSubject.getPersonId());
            cs.setString(2, staffSubject.getSubjectCode());
            cs.setInt(3, staffSubject.getSyear());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("RemoveStaffSubject error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public StaffSubject GetStaffSubject(StaffSubject staffSubject) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetStaffSubject(?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, staffSubject.getPersonId());
            pst.setString(2, staffSubject.getSubjectCode());
            pst.setInt(3, staffSubject.getSyear());
            rs = pst.executeQuery();
            if(rs.next())
                return GetStaffSubjectDetailsFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("GetStaffSubject error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public List<HashMap<String, Object>> GetStaffInSubject(String SubjectCode, int syear) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetStaffInSubject(?,?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, SubjectCode);
            pst.setInt(2, syear);
            rs = pst.executeQuery();
            return Functions.convertResultsetToList(rs);
        } catch (SQLException e) {
            Functions.errorMessage("GetStaffInSubject error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<StaffSubject> GetStaffSubjectInYear(int syear) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetStaffSubjectInYear(?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, syear);
            rs = pst.executeQuery();
            return GetStaffDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("GetStaffSubjectInYear error: "+ e.getLocalizedMessage());
        }
        return null;
    }


    @Override
    public List<HashMap<String, Object>> GetStaffSubjects(int personId) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetStaffSubjects(?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, personId);
            rs = pst.executeQuery();
            return Functions.convertResultsetToList(rs);
        } catch (SQLException e) {
            Functions.errorMessage("GetStaffSubjects error: "+ e.getLocalizedMessage());
        }
        return null;
    }
    
    @Override
    public List<HashMap<String, Object>> SearchMatricStaff(String search) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL SearchMatricStaff(?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, search);
            rs = pst.executeQuery();
            return Functions.convertResultsetToList(rs);
        } catch (SQLException e) {
            Functions.errorMessage("SearchMatricStaff error: "+ e.getLocalizedMessage());
        }
        return null;
    }
}
