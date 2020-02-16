/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao;

import com.molorane.college.model.Module;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Mothusi Molorane
 */
public abstract class ModuleDao {
    
    public abstract int AddModule(Module module);
    public abstract int EditModule(Module module);
    public abstract int RemoveModule(String moduleCode,String courseCode);    
    public abstract Module GetModule(String moduleCode,String courseCode);    
    public abstract ArrayList<Module> GetAllModules();
    public abstract ArrayList<Module> GetAllCourseModules(String courseCode);
    
    // Module CONVERSION METHODS
    public Module GetModuleDetailsFromResultSet(ResultSet rs) throws SQLException{
        Module module = new Module();
        module.setModule(rs.getString("moduleCode"),
                        rs.getString("courseCode"), 
                        rs.getString("module"), 
                        rs.getString("level"), 
                        rs.getString("department"),
                        rs.getString("course"));
        return module;
    }
    
    public ArrayList<Module> GetModuleDetailsCollectionFromResultSet(ResultSet rs) throws SQLException{
       ArrayList<Module> modules = new ArrayList<>();
        while (rs.next())
            modules.add(GetModuleDetailsFromResultSet(rs));
        return modules;
    }
}
