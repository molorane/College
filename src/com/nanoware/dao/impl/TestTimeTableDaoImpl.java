/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao.impl;

import com.nanoware.bll.Functions;
import com.nanoware.dao.TestTimeTableDao;
import com.nanoware.model.TestTimeTable;
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
public class TestTimeTableDaoImpl extends TestTimeTableDao{

    @Override
    public int AddTestTimeTable(TestTimeTable testTimeTable) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainTestTimeTable(?,?,?,?,?,?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, testTimeTable.getTtId());
            cs.setInt(2, testTimeTable.getCampusCode());
            cs.setString(3, testTimeTable.getModuleCode());
            cs.setInt(4, testTimeTable.getTermId());
            cs.setInt(5, testTimeTable.getGradeId());
            cs.setString(6, testTimeTable.getTestDate());
            cs.setString(7, testTimeTable.getStartTime());
            cs.setString(8, testTimeTable.getDuration());
            cs.setString(9, testTimeTable.getVenue());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("MaintainTestTimeTable error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int EditTestTimeTable(TestTimeTable testTimeTable) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainTestTimeTable(?,?,?,?,?,?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, testTimeTable.getTtId());
            cs.setInt(2, testTimeTable.getCampusCode());
            cs.setString(3, testTimeTable.getModuleCode());
            cs.setInt(4, testTimeTable.getTermId());
            cs.setInt(5, testTimeTable.getGradeId());
            cs.setString(6, testTimeTable.getTestDate());
            cs.setString(7, testTimeTable.getStartTime());
            cs.setString(8, testTimeTable.getDuration());
            cs.setString(9, testTimeTable.getVenue());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("MaintainTestTimeTable error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int RemoveTestTimeTable(int ttId) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL RemoveTestTimeTable(?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, ttId);
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("RemoveTestTimeTable error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public TestTimeTable GetTestTimeTable(int ttId) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetTestTimeTable(?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, ttId);
            rs = pst.executeQuery();
            if(rs.next())
                return GetTestTimeTableDetailsFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("GetTestTimeTable error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<TestTimeTable> GetCampusModuleTestTimeTable(int campusCode,String moduleCode,int termId) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetCampusModuleTestTimeTable(?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, campusCode);
            pst.setString(2, moduleCode);
            pst.setInt(3, termId);
            rs = pst.executeQuery();
            return GetTestTimeTableDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("GetCampusStaff error: "+ e.getLocalizedMessage());
        }
        return null;
    }
    
}
