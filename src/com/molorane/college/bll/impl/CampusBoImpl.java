/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll.impl;

import com.molorane.college.bll.CampusBo;
import com.molorane.college.dao.CampusDao;
import com.molorane.college.dao.impl.CampusDaoImpl;
import com.molorane.college.model.Campus;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author Mothusi Molorane
 */
public class CampusBoImpl implements CampusBo{
    
    private final CampusDao dao;

    public CampusBoImpl() {
        this.dao = new CampusDaoImpl();
    }

    @Override
    public int AddCampus(Campus campus) {
        return dao.AddCampus(campus);
    }

    @Override
    public int EditCampus(Campus campus) {
        return dao.EditCampus(campus);
    }

    @Override
    public int RemoveCampus(int campusCode) {
        return dao.RemoveCampus(campusCode);
    }

    @Override
    public Campus GetCampus(int campusCode) {
        return dao.GetCampus(campusCode);
    }

    @Override
    public ArrayList<Campus> GetAllCampuses() {
        return dao.GetAllCampuses();
    }
    
    
    public void fillComboBoxCampuses(JComboBox con){
        con.removeAllItems();
        Campus c = new Campus();
        c.setCampusCode(0);
        c.setCampusABR("SELECT");
        con.addItem(c);
        GetAllCampuses().forEach((bean) -> {
            con.addItem(bean);
        });
    }
}
