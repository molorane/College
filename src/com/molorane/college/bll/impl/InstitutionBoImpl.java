/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll.impl;

import com.molorane.college.bll.InstitutionBo;
import com.molorane.college.dao.InstitutionDao;
import com.molorane.college.dao.impl.InstitutionDaoImpl;
import com.molorane.college.model.Institution;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author Mothusi Molorane
 */
public class InstitutionBoImpl implements InstitutionBo{
    
    private final InstitutionDao dao;

    public InstitutionBoImpl() {
        this.dao = new InstitutionDaoImpl();
    }

    @Override
    public int AddInstitution(String institution) {
        return dao.AddInstitution(institution);
    }

    @Override
    public int EditInstitution(Institution institution) {
        return dao.EditInstitution(institution);
    }

    @Override
    public int RemoveInstitution(int institutionId) {
        return dao.RemoveInstitution(institutionId);
    }

    @Override
    public Institution GetInstitution(int institutionId) {
        return dao.GetInstitution(institutionId);
    }

    @Override
    public ArrayList<Institution> GetAllInstitutions() {
        return dao.GetAllInstitutions();
    }
    
    public void fillComboBoxInstitution(JComboBox con){
        con.removeAllItems();
        GetAllInstitutions().forEach((bean) -> {
            con.addItem(bean);
        });
    }
    
}
