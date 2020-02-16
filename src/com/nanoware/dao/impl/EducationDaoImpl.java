/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao.impl;

import com.nanoware.bll.Functions;
import com.nanoware.dao.EducationDao;
import com.nanoware.model.Education;
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
public class EducationDaoImpl extends EducationDao{

    @Override
    public int AddEducation(Education education) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainEducation(?,?,?,?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, -1);
            cs.setLong(2, education.getPersonId());
            cs.setInt(3, education.getInstitutionId());
            cs.setInt(4, education.getQualificationId());
            cs.setInt(5, education.getYearObtained());
            cs.setString(6, education.getSkills());
            cs.setString(7, education.getDetails());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("MaintainEducation error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int EditEducation(Education education) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainEducation(?,?,?,?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, education.getEduId());
            cs.setLong(2, education.getPersonId());
            cs.setInt(3, education.getInstitutionId());
            cs.setInt(4, education.getQualificationId());
            cs.setInt(5, education.getYearObtained());
            cs.setString(6, education.getSkills());
            cs.setString(7, education.getDetails());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("MaintainEducation error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int RemoveEducation(int eduId) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL RemoveEducation(?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, eduId);
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("RemoveEducation error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public Education GetEducation(int eduId) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetEducation(?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, eduId);
            rs = pst.executeQuery();
            if (rs.next()) {
                return GetEducationDetailsFromResultSet(rs);
            }
        } catch (SQLException e) {
            Functions.errorMessage("getEducation error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Education> GetPersonEducation(long personId) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetEducationByPersonId(?)";
            pst = conn.prepareStatement(sql);
            pst.setLong(1, personId);
            rs = pst.executeQuery();
            return GetEducationDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getEducationByPersonId error: "+ e.getLocalizedMessage());
        }
        return null;
    }
}
