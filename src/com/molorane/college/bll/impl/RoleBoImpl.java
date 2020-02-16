/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll.impl;

import com.molorane.college.bll.RoleBo;
import com.molorane.college.dao.RoleDao;
import com.molorane.college.dao.impl.RoleDaoImpl;
import com.molorane.college.model.Role;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author Mothusi Molorane
 */
public class RoleBoImpl implements RoleBo{
    
    private final RoleDao dao;

    public RoleBoImpl() {
        this.dao = new RoleDaoImpl();
    }

    @Override
    public int AddRole(Role role) {
        return dao.AddRole(role);
    }

    @Override
    public int EditRole(Role role) {
        return dao.EditRole(role);
    }

    @Override
    public int RemoveRole(int roleId) {
        return dao.RemoveRole(roleId);
    }

    @Override
    public Role GetRole(int roleId) {
        return dao.GetRole(roleId);
    }

    @Override
    public ArrayList<Role> GetAllRoles() {
        return dao.GetAllRoles();
    }
    
    public void fillComboBoxRole(JComboBox con){
        con.removeAllItems();
        Role r = new Role();
        r.setRole(0, "SELECT");
        con.addItem(r);
        GetAllRoles().forEach((bean) -> {
            con.addItem(bean);
        });
    }
    
}
