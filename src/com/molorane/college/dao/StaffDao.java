/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao;

import com.molorane.college.model.Staff;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class StaffDao {
    
    public abstract int AddStaff(Staff staff);
    public abstract int EditStaff(Staff staff);
    public abstract int RemoveStaff(int contractId);    
    public abstract Staff GetStaff(int contractId);
    public abstract ArrayList<Staff> GetStaffContracts(int lectureId);
    public abstract ArrayList<Staff> GetCampusStaff(int campusCode);
    public abstract ArrayList<Staff> GetStaffByCampusAndRole(int campusCode,int roleId);
    public abstract int AddMatricStaff(int personId, String firstName, String lastName);
    
            
    // Staff CONVERSION METHODS
    protected Staff GetStaffDetailsFromResultSet(ResultSet rs) throws SQLException{
        Staff staff = new Staff();
        staff.setStaff(rs.getInt("contractId"), 
                        rs.getInt("personId"),
                        rs.getInt("roleId"),
                        rs.getInt("campusCode"), 
                        rs.getString("contractFrom"), 
                        rs.getString("contractTo"), 
                        rs.getString("recordDate"),
                        rs.getInt("recordBy"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("otherName"),   
                        rs.getString("roleName"),
                        rs.getString("campusABR"));
        return staff;
    }
    
    protected ArrayList<Staff> GetStaffDetailsCollectionFromResultSet(ResultSet rs) throws SQLException{
       ArrayList<Staff> staff = new ArrayList<>();
        while (rs.next())
            staff.add(GetStaffDetailsFromResultSet(rs));
        return staff;
    }
}
