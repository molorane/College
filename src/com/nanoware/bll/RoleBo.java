/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll;

import com.nanoware.model.Role;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public interface RoleBo {
    public abstract int AddRole(Role role);
    public abstract int EditRole(Role role);
    public abstract int RemoveRole(int roleId);    
    public abstract Role GetRole(int roleId);    
    public abstract ArrayList<Role> GetAllRoles();
}
