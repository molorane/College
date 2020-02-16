/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao.impl;

import com.molorane.college.db.DBConnection;
import com.molorane.college.custom.Functions;
import com.molorane.college.dao.GradeWeightDao;
import com.molorane.college.model.GradeWeight;
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
public class GradeWeightDaoImpl extends GradeWeightDao{

    @Override
    public int AddGradeWeight(GradeWeight gradeWeight) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainGradeWeight(?,?,?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, gradeWeight.getGwId());
            cs.setString(2, gradeWeight.getCourseCode());
            cs.setInt(3, gradeWeight.getTermId());
            cs.setInt(4, gradeWeight.getGradeId());
            cs.setDouble(5, gradeWeight.getWeight());
            cs.setInt(6, gradeWeight.getRecordBy());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("MaintainGradeWeight error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int EditGradeWeight(GradeWeight gradeWeight) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainGradeWeight(?,?,?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, gradeWeight.getGwId());
            cs.setString(2, gradeWeight.getCourseCode());
            cs.setInt(3, gradeWeight.getTermId());
            cs.setInt(4, gradeWeight.getGradeId());
            cs.setDouble(5, gradeWeight.getWeight());
            cs.setInt(6, gradeWeight.getRecordBy());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("MaintainGradeWeight error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int RemoveGradeWeight(int gwId) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL RemoveGradeWeight(?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, gwId);
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("RemoveGradeWeight error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public GradeWeight GetGradeWeight(int gwId) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetGradeWeight(?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, gwId);
            if (rs.next()) {
                return GetGradeWeightDetailsFromResultSet(rs);
            }
        } catch (SQLException e) {
            Functions.errorMessage("GetGradeWeight error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<GradeWeight> GetGradeWeightByCourseAndTerm(String courseCode, int termId) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetGradeWeightByCourseAndTerm(?,?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, courseCode);
            pst.setInt(2, termId);
            rs = pst.executeQuery();
            return GetGradeWeightDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("GetGradeWeightByCourseAndTerm error: "+ e.getLocalizedMessage());
        }
        return null;
    }
    
}
