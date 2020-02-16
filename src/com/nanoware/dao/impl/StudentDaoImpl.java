/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao.impl;

import com.nanoware.bll.Functions;
import com.nanoware.dao.ModuleDao;
import com.nanoware.dao.StudentDao;
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
public class StudentDaoImpl extends StudentDao{

    @Override
    public List<HashMap<String, Object>> StudentModuleGrades(int personId, int termId, String moduleCode) {
        try {
            ModuleDao m = new ModuleDaoImpl();
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL StudentModuleGrades(?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, personId);
            pst.setInt(2, termId);
            pst.setString(3, moduleCode);
            rs = pst.executeQuery();
            return Functions.convertResultsetToList(rs);
        } catch (SQLException e) {
            Functions.errorMessage("StudentModuleGrades error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public List<HashMap<String, Object>> GetStudentModuleAssessment(int campusCode, int termId, String moduleCode) {
        try {
            ModuleDao m = new ModuleDaoImpl();
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetStudentModuleAssessment(?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, campusCode);
            pst.setInt(2, termId);
            pst.setString(3, moduleCode);
            rs = pst.executeQuery();
            return Functions.convertResultsetToList(rs);
        } catch (SQLException e) {
            Functions.errorMessage("GetStudentModuleAssessment error: "+ e.getLocalizedMessage());
        }
        return null;
    }
    
}
