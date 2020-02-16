/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.model;

/**
 *
 * @author Mothusi Molorane
 */
public class Role {
    
    private int roleId;
    private String roleName;
    
    public void setRole(int roleId,String roleName){
        this.roleId = roleId;
        this.roleName = roleName;
    }

    /**
     * @return the roleId
     */
    public int getRoleId() {
        return roleId;
    }

    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    /**
     * @return the roleName
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName the roleName to set
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    @Override
    public String toString(){
        return roleName;
    }
}
