/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class StudentDao {
    
    public abstract List<HashMap<String, Object>> StudentModuleGrades(int personId, int termId, String moduleCode);
    public abstract List<HashMap<String, Object>> GetStudentModuleAssessment(int campusCode, int termId, String moduleCode);
    
}
