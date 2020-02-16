/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll;

import com.molorane.college.model.StaffSubject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mothusi Molorane
 */
public interface StaffSubjectBo {
    public abstract int AddStaffSubject(StaffSubject staffSubject);
    public abstract int EditStaffSubject(StaffSubject staffSubject);
    public abstract int RemoveStaffSubject(StaffSubject staffSubject);    
    public abstract StaffSubject GetStaffSubject(StaffSubject staffSubject);
    public abstract List<HashMap<String, Object>> GetStaffInSubject(String SubjectCode, int syear);
    public abstract ArrayList<StaffSubject> GetStaffSubjectInYear(int syear);
    public abstract List<HashMap<String, Object>> SearchMatricStaff(String search);
    public abstract List<HashMap<String, Object>> GetStaffSubjects(int personId);
}
