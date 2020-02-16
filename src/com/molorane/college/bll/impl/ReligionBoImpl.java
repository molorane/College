/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll.impl;

import com.molorane.college.bll.ReligionBo;
import com.molorane.college.dao.ReligionDao;
import com.molorane.college.dao.impl.ReligionDaoImpl;
import com.molorane.college.model.Religion;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author Mothusi Molorane
 */
public class ReligionBoImpl implements ReligionBo{
    
    private final ReligionDao dao;

    public ReligionBoImpl() {
        this.dao = new ReligionDaoImpl();
    }

    @Override
    public int AddReligion(Religion religion) {
        return dao.AddReligion(religion);
    }

    @Override
    public int EditReligion(Religion religion) {
        return dao.EditReligion(religion);
    }

    @Override
    public int RemoveReligion(int religionId) {
        return dao.RemoveReligion(religionId);
    }

    @Override
    public Religion GetReligion(int religionId) {
        return dao.GetReligion(religionId);
    }

    @Override
    public ArrayList<Religion> GetAllReligions() {
        return dao.GetAllReligions();
    }
    
    public void fillComboBoxReligion(JComboBox con){
        con.removeAllItems();
        Religion r = new Religion();
        r.setReligion(0, "SELECT");
        con.addItem(r);
        GetAllReligions().forEach((bean) -> {
            con.addItem(bean);
        });
    }
}
