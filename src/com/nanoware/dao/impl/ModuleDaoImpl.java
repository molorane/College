/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao.impl;

import com.nanoware.bll.Functions;
import com.nanoware.dao.ModuleDao;
import com.nanoware.model.Module;
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
public class ModuleDaoImpl extends ModuleDao{

    @Override
    public int AddModule(Module module) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainModule(?,?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setString(1, module.getModuleCode());
            cs.setString(2, module.getCourseCode());
            cs.setString(3, module.getModule());
            cs.setString(4, module.getLevel());
            cs.setString(5, module.getDepartment());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("MaintainModule error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int EditModule(Module module) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainModule(?,?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setString(1, module.getModuleCode());
            cs.setString(2, module.getModule());
            cs.setString(3, module.getLevel());
            cs.setString(4, module.getDepartment());
            cs.setString(5, module.getCourseCode());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("MaintainModule error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int RemoveModule(String moduleCode,String courseCode) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL RemoveModule(?,?)";
            cs = conn.prepareCall(sql);
            cs.setString(1, moduleCode);
            cs.setString(2, courseCode);
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("RemoveModule error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public Module GetModule(String moduleCode,String courseCode) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetModule(?,?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, moduleCode);
            pst.setString(2, courseCode);
            rs = pst.executeQuery();
            if (rs.next()) {
                return GetModuleDetailsFromResultSet(rs);
            }
        } catch (SQLException e) {
            Functions.errorMessage("getModule error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Module> GetAllModules() {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetAllModules()";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            return GetModuleDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getAllModules error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Module> GetAllCourseModules(String courseCode) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetAllCourseModules(?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, courseCode);
            rs = pst.executeQuery();
            return GetModuleDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getAllCourseModules error: "+ e.getLocalizedMessage());
        }
        return null;
    }    
}
