/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll.impl;

import com.nanoware.bll.ReligionBo;
import com.nanoware.dao.ReligionDao;
import com.nanoware.dao.impl.ReligionDaoImpl;
import com.nanoware.model.Religion;
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
        GetAllReligions().forEach((bean) -> {
            con.addItem(bean);
        });
    }
}
