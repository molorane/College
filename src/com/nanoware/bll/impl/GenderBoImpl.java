/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll.impl;

import com.nanoware.bll.GenderBo;
import com.nanoware.dao.GenderDao;
import com.nanoware.dao.impl.GenderDaoImpl;
import com.nanoware.model.Gender;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author Mothusi Molorane
 */
public class GenderBoImpl implements GenderBo{
    
    private final GenderDao dao;

    public GenderBoImpl() {
        this.dao = new GenderDaoImpl();
    }

    @Override
    public int AddGender(Gender gender) {
        return dao.AddGender(gender);
    }

    @Override
    public int EditGender(Gender gender) {
        return dao.EditGender(gender);
    }

    @Override
    public int RemoveGender(int genderId) {
        return dao.RemoveGender(genderId);
    }

    @Override
    public Gender GetGender(int genderId) {
        return dao.GetGender(genderId);
    }

    @Override
    public ArrayList<Gender> GetAllGenders() {
        return dao.GetAllGenders();
    }
    
    public void fillComboBoxGender(JComboBox con){
        con.removeAllItems();
        GetAllGenders().forEach((bean) -> {
            con.addItem(bean);
        });
    }
    
}
