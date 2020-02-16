/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao.impl;

import com.nanoware.bll.Functions;
import com.nanoware.dao.EmploymentDao;
import com.nanoware.model.Employment;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Mothusi Molorane
 */
public class EmploymentDaoImpl extends EmploymentDao{

    @Override
    public int AddEmployment(Employment employment) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainEmployment(?,?,?,?,?,?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setLong(1, employment.getPersonId());
            cs.setString(2, employment.getCompany());
            cs.setInt(3, employment.getPositionId());
            cs.setString(4, employment.getAddress());
            cs.setString(5, employment.getPostalCode());
            cs.setString(6, employment.getContactPerson());
            cs.setString(7, employment.getTelephone());
            cs.setString(8, employment.getCellphone());
            cs.setString(9, employment.getFax());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("MaintainEmployment error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int EditEmployment(Employment employment) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainEmployment(?,?,?,?,?,?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setLong(1, employment.getPersonId());
            cs.setString(2, employment.getCompany());
            cs.setInt(3, employment.getPositionId());
            cs.setString(4, employment.getAddress());
            cs.setString(5, employment.getPostalCode());
            cs.setString(6, employment.getContactPerson());
            cs.setString(7, employment.getTelephone());
            cs.setString(8, employment.getCellphone());
            cs.setString(9, employment.getFax());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("MaintainEmployment error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int RemoveEmployment(long personId) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL RemoveEmployment(?)";
            cs = conn.prepareCall(sql);
            cs.setLong(1, personId);
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("RemoveEmployment error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public Employment GetEmployment(long personId) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetEmployment(?)";
            pst = conn.prepareStatement(sql);
            pst.setLong(1, personId);
            rs = pst.executeQuery();
            if (rs.next()) {
                return GetEmploymentDetailsFromResultSet(rs);
            }
        } catch (SQLException e) {
            Functions.errorMessage("getEmployment error: "+ e.getLocalizedMessage());
        }
        return null;
    } 
}
