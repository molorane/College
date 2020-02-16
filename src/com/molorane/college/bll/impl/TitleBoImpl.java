/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll.impl;

import com.molorane.college.bll.TitleBo;
import com.molorane.college.dao.TitleDao;
import com.molorane.college.dao.impl.TitleDaoImpl;
import com.molorane.college.model.Title;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author Mothusi Molorane
 */
public class TitleBoImpl implements TitleBo{
    
    private final TitleDao dao;

    public TitleBoImpl() {
        this.dao = new TitleDaoImpl();
    }

    @Override
    public int AddTitle(Title title) {
        return dao.AddTitle(title);
    }

    @Override
    public int EditTitle(Title title) {
        return dao.EditTitle(title);
    }

    @Override
    public int RemoveTitle(int titleId) {
        return dao.RemoveTitle(titleId);
    }

    @Override
    public Title GetTitle(int titleId) {
        return dao.GetTitle(titleId);
    }

    @Override
    public ArrayList<Title> GetAllTitles() {
        return dao.GetAllTitles();
    }
    
    public void fillComboBoxTitle(JComboBox con){
        con.removeAllItems();
        Title t = new Title();
        t.setTitle(0, "SELECT");
        con.addItem(t);
        GetAllTitles().forEach((bean) -> {
            con.addItem(bean);
        });
    }
}
