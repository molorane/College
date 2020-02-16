/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll.impl;

import com.nanoware.bll.EmploymentBo;
import com.nanoware.dao.EmploymentDao;
import com.nanoware.dao.impl.EmploymentDaoImpl;
import com.nanoware.model.Employment;

/**
 * @author Mothusi Molorane
 */
public class EmploymentBoImpl implements EmploymentBo{
    
    private final EmploymentDao dao;

    public EmploymentBoImpl() {
        this.dao = new EmploymentDaoImpl();
    }

    @Override
    public int AddEmployment(Employment employment) {
        return dao.AddEmployment(employment);
    }

    @Override
    public int EditEmployment(Employment employment) {
        return dao.EditEmployment(employment);
    }

    @Override
    public int RemoveEmployment(long personId) {
        return dao.RemoveEmployment(personId);
    }

    @Override
    public Employment GetEmployment(long personId) {
        return dao.GetEmployment(personId);
    }
}
