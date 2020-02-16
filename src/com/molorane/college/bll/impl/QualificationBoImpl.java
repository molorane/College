/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll.impl;

import com.molorane.college.bll.QualificationBo;
import com.molorane.college.dao.QualificationDao;
import com.molorane.college.dao.impl.QualificationDaoImpl;
import com.molorane.college.model.Qualification;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author Mothusi Molorane
 */
public class QualificationBoImpl implements QualificationBo{
    
    private final QualificationDao dao;

    public QualificationBoImpl() {
        this.dao = new QualificationDaoImpl();
    }

    @Override
    public int AddQualification(Qualification qualification) {
        return dao.AddQualification(qualification);
    }

    @Override
    public int EditQualification(Qualification qualification) {
        return dao.EditQualification(qualification);
    }

    @Override
    public int RemoveQualification(int qualificationId) {
        return dao.RemoveQualification(qualificationId);
    }

    @Override
    public Qualification GetQualification(int qualificationId) {
        return dao.GetQualification(qualificationId);
    }

    @Override
    public ArrayList<Qualification> GetAllQualifications() {
        return dao.GetAllQualifications();
    }
    
    public void fillComboBoxRace(JComboBox con){
        con.removeAllItems();
        Qualification q = new Qualification();
        q.setQualification(0, "SELECT");
        con.addItem(q);
        GetAllQualifications().forEach((bean) -> {
            con.addItem(bean);
        });
    }
    
}
