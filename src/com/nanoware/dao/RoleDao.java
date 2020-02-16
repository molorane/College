/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao;

import com.nanoware.model.Role;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class RoleDao {
    
    public abstract int AddRole(Role role);
    public abstract int EditRole(Role role);
    public abstract int RemoveRole(int roleId);    
    public abstract Role GetRole(int roleId);    
    public abstract ArrayList<Role> GetAllRoles();
    
    // RACE CONVERSION METHODS
    protected Role GetRoleDetailsFromResultSet(ResultSet rs) throws SQLException{
        Role role = new Role();
        role.setRole(rs.getInt("roleId"), 
                        rs.getString("roleName"));
        return role;
    }
    
    protected ArrayList<Role> GetRoleDetailsCollectionFromResultSet(ResultSet rs) throws SQLException{
       ArrayList<Role> roles = new ArrayList<>();
        while (rs.next())
            roles.add(GetRoleDetailsFromResultSet(rs));
        return roles;
    }
}
