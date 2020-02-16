/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll;

import com.molorane.college.model.Module;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public interface ModuleBo {
    public abstract int AddModule(Module module);
    public abstract int EditModule(Module module);
    public abstract int RemoveModule(String moduleCode,String courseCode);    
    public abstract Module GetModule(String moduleCode,String courseCode);    
    public abstract ArrayList<Module> GetAllModules();
    public abstract ArrayList<Module> GetAllCourseModules(String courseCode);
}
