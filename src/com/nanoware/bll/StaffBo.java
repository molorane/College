/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll;

import com.nanoware.model.Staff;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public interface StaffBo {
    public abstract int AddStaff(Staff staff);
    public abstract int EditStaff(Staff staff);
    public abstract int RemoveStaff(int contractId);    
    public abstract Staff GetStaff(int contractId);
    public abstract ArrayList<Staff> GetStaffContracts(int lectureId);
    public abstract ArrayList<Staff> GetCampusStaff(int campusCode);
    public abstract ArrayList<Staff> GetStaffByCampusAndRole(int campusCode,int roleId);
}
