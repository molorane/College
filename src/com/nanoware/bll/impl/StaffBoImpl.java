/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll.impl;

import com.nanoware.bll.StaffBo;
import com.nanoware.dao.StaffDao;
import com.nanoware.dao.impl.StaffDaoImpl;
import com.nanoware.model.Staff;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author Mothusi Molorane
 */
public class StaffBoImpl implements StaffBo{
    
    private final StaffDao dao;

    public StaffBoImpl() {
        this.dao = new StaffDaoImpl();
    }

    @Override
    public int AddStaff(Staff staff) {
        return dao.AddStaff(staff);
    }

    @Override
    public int EditStaff(Staff staff) {
        return dao.EditStaff(staff);
    }

    @Override
    public int RemoveStaff(int contractId) {
        return dao.RemoveStaff(contractId);
    }

    @Override
    public Staff GetStaff(int contractId) {
        return dao.GetStaff(contractId);
    }

    @Override
    public ArrayList<Staff> GetStaffContracts(int lectureId) {
        return dao.GetStaffContracts(lectureId);
    }

    @Override
    public ArrayList<Staff> GetCampusStaff(int campusCode) {
        return dao.GetCampusStaff(campusCode);
    }

    @Override
    public ArrayList<Staff> GetStaffByCampusAndRole(int campusCode, int roleId) {
        return dao.GetStaffByCampusAndRole(campusCode, roleId);
    }
}
