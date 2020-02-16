/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll.impl;

import com.nanoware.bll.CampusBo;
import com.nanoware.dao.CampusDao;
import com.nanoware.dao.impl.CampusDaoImpl;
import com.nanoware.model.Campus;
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
        GetAllCampuses().forEach((bean) -> {
            con.addItem(bean);
        });
    }
}
