/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao.impl;

import com.nanoware.bll.Functions;
import com.nanoware.dao.AssessmentDao;
import com.nanoware.model.Assessment;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mothusi Molorane
 */
public class AssessmentDaoImpl extends AssessmentDao{

    @Override
    public int AddAssessment(Assessment assessment,int campusCode) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainAssessment(?,?,?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, campusCode);
            cs.setInt(2, assessment.getPersonId());
            cs.setString(3, assessment.getModuleCode());
            cs.setInt(4, assessment.getTermId());
            cs.setInt(5, assessment.getAssId());
            cs.setDouble(6, assessment.getMark());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("MaintainAssessment error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int EditAssessment(Assessment assessment,int campusCode) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainAssessment(?,?,?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, campusCode);
            cs.setInt(2, assessment.getPersonId());
            cs.setString(3, assessment.getModuleCode());
            cs.setInt(4, assessment.getTermId());
            cs.setInt(5, assessment.getAssId());
            cs.setDouble(6, assessment.getMark());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("MaintainAssessment error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int RemoveAssessment(Assessment assessment,int campusCode) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL RemoveAssessment(?,?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, assessment.getPersonId());
            cs.setString(2, assessment.getModuleCode());
            cs.setInt(3, assessment.getTermId());
            cs.setInt(4, assessment.getAssId());
            cs.setInt(5, campusCode);
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("RemoveAssessment error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public Assessment getAssessment(Assessment assessment) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL getAssessment(?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, assessment.getPersonId());
            pst.setString(2, assessment.getModuleCode());
            pst.setInt(3, assessment.getTermId());
            pst.setInt(4, assessment.getAssId());
            if (rs.next()) {
                return GetAssessmentDetailsFromResultSet(rs);
            }
        } catch (SQLException e) {
            Functions.errorMessage("getAssessment error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public int UploadMarks(List<HashMap<String, Object>> marks,int campusCode,String moduleCode, int termId, int assId) {
        try{            
            String sql = "CALL UploadMarks(?,?,?,?,?,?)";
            Connection conn = DBConnection.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pst = conn.prepareStatement(sql);
            for(HashMap<String, Object> row:marks){
                pst.setInt(1, campusCode);
                pst.setInt(2, Integer.parseInt(row.get("personId").toString()));
                pst.setString(3, moduleCode);
                pst.setInt(4, termId);
                pst.setInt(5, assId);
                pst.setDouble(6, Double.parseDouble(row.get("mark").toString()));
                pst.addBatch();
            }
            int[] rows = pst.executeBatch();
            conn.commit();
            conn.close();
            return rows.length;
        }catch (SQLException ex) {
            Functions.errorMessage("UploadMarks error: "+ ex.getLocalizedMessage());
        }
        return -1;
    }

    @Override
    public List<HashMap<String, Object>> getStudentMarks(int campusCode, String moduleCode, int termId, int assId) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL getStudentMarks(?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, campusCode);
            pst.setString(2, moduleCode);
            pst.setInt(3, termId);
            pst.setInt(4, assId);
            rs = pst.executeQuery();
            return Functions.convertResultsetToList(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getStudentMarks error: "+ e.getLocalizedMessage());
        }
        return null;
    }
}
