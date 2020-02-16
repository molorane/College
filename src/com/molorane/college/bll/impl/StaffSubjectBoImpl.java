/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll.impl;

import com.molorane.college.bll.StaffSubjectBo;
import com.molorane.college.dao.StaffSubjectDao;
import com.molorane.college.dao.impl.StaffSubjectDaoImpl;
import com.molorane.college.model.StaffSubject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mothusi Molorane
 */
public class StaffSubjectBoImpl implements StaffSubjectBo{
    
    private final StaffSubjectDao dao;

    public StaffSubjectBoImpl() {
        this.dao = new StaffSubjectDaoImpl();
    }

    @Override
    public int AddStaffSubject(StaffSubject staffSubject) {
        return dao.AddStaffSubject(staffSubject);
    }

    @Override
    public int EditStaffSubject(StaffSubject staffSubject) {
        return dao.EditStaffSubject(staffSubject);
    }

    @Override
    public int RemoveStaffSubject(StaffSubject staffSubject) {
        return dao.RemoveStaffSubject(staffSubject);
    }

    @Override
    public StaffSubject GetStaffSubject(StaffSubject staffSubject) {
        return dao.GetStaffSubject(staffSubject);
    }

    @Override
    public List<HashMap<String, Object>> GetStaffInSubject(String SubjectCode, int syear) {
        return dao.GetStaffInSubject(SubjectCode, syear);
    }

    @Override
    public ArrayList<StaffSubject> GetStaffSubjectInYear(int syear) {
        return dao.GetStaffSubjectInYear(syear);
    }

    @Override
    public List<HashMap<String, Object>> SearchMatricStaff(String search) {
        return dao.SearchMatricStaff(search);
    }

    @Override
    public List<HashMap<String, Object>> GetStaffSubjects(int personId) {
        return dao.GetStaffSubjects(personId);
    }

}