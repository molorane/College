/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll.impl;

import com.nanoware.bll.TitleBo;
import com.nanoware.dao.TitleDao;
import com.nanoware.dao.impl.TitleDaoImpl;
import com.nanoware.model.Title;
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
        GetAllTitles().forEach((bean) -> {
            con.addItem(bean);
        });
    }
}
