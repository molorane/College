/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao.impl;

import com.molorane.college.db.DBConnection;
import com.molorane.college.custom.Functions;
import com.molorane.college.dao.RoleDao;
import com.molorane.college.model.Role;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public class RoleDaoImpl extends RoleDao{

    @Override
    public int AddRole(Role role) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainRole(?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, -1);
            cs.setString(2, role.getRoleName());
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(3);
        } catch (SQLException e) {
            Functions.errorMessage("MaintainRole error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int EditRole(Role role) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainRole(?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, role.getRoleId());
            cs.setString(2, role.getRoleName());
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(3);
        } catch (SQLException e) {
            Functions.errorMessage("MaintainRole error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int RemoveRole(int roleId) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "CALL RemoveRole(?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, roleId);
            // execute the java preparedstatement
            int count = pst.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("RemoveRole error: "+ e.getLocalizedMessage());
        }
        return 0;
    }

    @Override
    public Role GetRole(int roleId) {
         try{
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetRole(?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, roleId);
            rs = pst.executeQuery();
            if(rs.next())
                return GetRoleDetailsFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("GetRole error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Role> GetAllRoles() {
         try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetAllRoles()";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            return GetRoleDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("GetAllRoles error: "+ e.getLocalizedMessage());
        }
        return null;
    }
}
