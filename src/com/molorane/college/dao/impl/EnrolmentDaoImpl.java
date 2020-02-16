/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao.impl;

import com.molorane.college.db.DBConnection;
import com.molorane.college.custom.Functions;
import com.molorane.college.dao.EnrolmentDao;
import com.molorane.college.model.Campus;
import com.molorane.college.model.Enrolment;
import com.molorane.college.model.StudyType;
import com.molorane.college.model.Term;
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
public class EnrolmentDaoImpl extends EnrolmentDao{

    @Override
    public int AddEnrolment(Enrolment enrolment) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainEnrolment(?,?,?,?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, enrolment.getPersonId());
            cs.setString(2, enrolment.getCourseCode());
            cs.setString(3, enrolment.getModuleCode());
            cs.setInt(4, enrolment.getTermId());
            cs.setInt(5, enrolment.getStudytypeId());
            cs.setInt(6, enrolment.getCampusCode());
            cs.setDouble(7, enrolment.getMarks());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("MaintainEnrolment error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int EditEnrolment(Enrolment enrolment) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainEnrolment(?,?,?,?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, enrolment.getPersonId());
            cs.setString(2, enrolment.getCourseCode());
            cs.setString(3, enrolment.getModuleCode());
            cs.setInt(4, enrolment.getTermId());
            cs.setInt(5, enrolment.getStudytypeId());
            cs.setInt(6, enrolment.getCampusCode());
            cs.setDouble(7, enrolment.getMarks());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("MaintainEnrolment error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int RemoveEnrolment(Enrolment enrolment) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL RemoveEnrolment(?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, enrolment.getPersonId());
            cs.setString(2, enrolment.getCourseCode());
            cs.setString(3, enrolment.getModuleCode());
            cs.setInt(4, enrolment.getTermId());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("RemoveEnrolment error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public Enrolment GetEnrolment(Enrolment enrolment) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetEnrolment(?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, enrolment.getPersonId());
            pst.setString(2, enrolment.getCourseCode());
            pst.setString(3, enrolment.getModuleCode());
            pst.setInt(4, enrolment.getTermId());
            rs = pst.executeQuery();
            if (rs.next()) {
                return GetEnrolmentDetailsFromResultSet(rs);
            }
        } catch (SQLException e) {
            Functions.errorMessage("getEnrolment error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Enrolment> GetPersonEnrolments(long personId) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetPersonEnrolments(?)";
            pst = conn.prepareStatement(sql);
            pst.setLong(1, personId);
            rs = pst.executeQuery();
            return GetEnrolmentDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getPersonEnrolments error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public int EnrolStudent(long personId, String courseCode, ArrayList<String> modules, Term term, StudyType studyType, Campus campus) {
       try{            
            String sql = "CALL EnrolStudent(?,?,?,?,?,?)";
            Connection conn = DBConnection.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pst = conn.prepareStatement(sql);            
            for(String module:modules){
                pst.setLong(1, personId);
                pst.setString(2, courseCode);
                pst.setString(3, module);
                pst.setInt(4, term.getTermId());
                pst.setInt(5, studyType.getStudyTypeId());
                pst.setInt(6, campus.getCampusCode());
                pst.addBatch();
            }
            int[] rows = pst.executeBatch();
            conn.commit();
            conn.close();
            return rows.length;
        } catch (SQLException e) {
            Functions.errorMessage("EnrolStudent error: "+ e.getLocalizedMessage());
        }
        return -1;    
    }

    @Override
    public List<HashMap<String, Object>> GetStudentCourseEnrolments(long personId) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetStudentCourseEnrolments(?)";
            pst = conn.prepareStatement(sql);
            pst.setLong(1, personId);
            rs = pst.executeQuery();
            return Functions.convertResultsetToList(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getStudentCourseEnrolments error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public List<HashMap<String, Object>> GetStudentCourseEnrolmentModules(long personId,String courseCode,String term) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetStudentCourseEnrolmentModules(?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setLong(1, personId);
            pst.setString(2, courseCode);
            pst.setString(3, term);
            rs = pst.executeQuery();
            return Functions.convertResultsetToList(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getStudentCourseEnrolmentModules error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public int deregisterModules(long personId, String courseCode, ArrayList<String> modules, String term) {
        try{            
            String sql = "CALL DeregisterModules(?,?,?,?)";
            Connection conn = DBConnection.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pst = conn.prepareStatement(sql);            
            for(String module:modules){
                pst.setLong(1, personId);
                pst.setString(2, courseCode);
                pst.setString(3, module);
                pst.setString(4, term);
                pst.addBatch();
            }
            int[] rows;
            rows = pst.executeBatch();
            conn.commit();
            conn.close();
            return rows.length;
        } catch (SQLException e) {
            Functions.errorMessage("deregisterModules error: "+ e.getLocalizedMessage());
        }
        return -1; 
    }

    @Override
    public List<HashMap<String, Object>> GetStudentTermEnrolments(long personId, int termId) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetStudentTermEnrolments(?,?)";
            pst = conn.prepareStatement(sql);
            pst.setLong(1, personId);
            pst.setInt(2, termId);
            rs = pst.executeQuery();
            return Functions.convertResultsetToList(rs);
        } catch (SQLException e) {
            Functions.errorMessage("GetStudentTermEnrolments error: "+ e.getLocalizedMessage());
        }
        return null;
    }
}
