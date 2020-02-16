/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll.impl;

import com.nanoware.bll.ModuleBo;
import com.nanoware.dao.ModuleDao;
import com.nanoware.dao.impl.ModuleDaoImpl;
import com.nanoware.model.Module;
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
