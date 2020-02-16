/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll.impl;

import com.molorane.college.bll.ModuleBo;
import com.molorane.college.dao.ModuleDao;
import com.molorane.college.dao.impl.ModuleDaoImpl;
import com.molorane.college.model.Module;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public class ModuleBoImpl implements ModuleBo{
    
    private final ModuleDao dao;

    public ModuleBoImpl() {
        this.dao = new ModuleDaoImpl();
    }

    @Override
    public int AddModule(Module module) {
        return dao.AddModule(module);
    }

    @Override
    public int EditModule(Module module) {
        return dao.EditModule(module);
    }

    @Override
    public int RemoveModule(String moduleCode, String courseCode) {
        return dao.RemoveModule(moduleCode, courseCode);
    }

    @Override
    public Module GetModule(String moduleCode, String courseCode) {
        return dao.GetModule(moduleCode, courseCode);
    }

    @Override
    public ArrayList<Module> GetAllModules() {
        return dao.GetAllModules();
    } 

    @Override
    public ArrayList<Module> GetAllCourseModules(String courseCode) {
        return dao.GetAllCourseModules(courseCode);
    }
}
