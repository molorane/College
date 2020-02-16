/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao.impl;

import com.molorane.college.custom.Functions;
import com.molorane.college.dao.SubjectDao;
import com.molorane.college.db.DBConnection;
import com.molorane.college.model.Subject;
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
public class SubjectDaoImpl extends SubjectDao{

    @Override
    public int AddSubject(Subject subject) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainSubject(?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, -1);
            cs.setString(2, subject.getSubject());
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(3);
        } catch (SQLException e) {
            Functions.errorMessage("MaintainSubject error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int EditSubject(Subject subject) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainSubject(?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, subject.getSubjectId());
            cs.setString(2, subject.getSubject());
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(3);
        } catch (SQLException e) {
            Functions.errorMessage("MaintainSubject error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int RemoveSubject(int subjectId) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "CALL RemoveSubject(?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, subjectId);
            // execute the java preparedstatement
            int count = pst.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("RemoveSubject error: "+ e.getLocalizedMessage());
        }
        return 0;
    }

    @Override
    public Subject GetSubject(int subjectId) {
        try{
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetSubject(?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, subjectId);
            rs = pst.executeQuery();
            if(rs.next())
                return GetSubjectDetailsFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getSubject error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Subject> GetAllSubjects() {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetAllSubjects()";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            return GetSubjectDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getAllSubjects error: "+ e.getLocalizedMessage());
        }
        return null;
    }
    
}
