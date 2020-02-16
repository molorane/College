/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll;

import com.molorane.college.model.Employment;

/**
 *
 * @author Mothusi Molorane
 */
public interface EmploymentBo {
    public abstract int AddEmployment(Employment employment);
    public abstract int EditEmployment(Employment employment);
    public abstract int RemoveEmployment(long personId);    
    public abstract Employment GetEmployment(long personId);
}
