/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll.impl;

import com.molorane.college.bll.GurdianBo;
import com.molorane.college.dao.GurdianDao;
import com.molorane.college.dao.impl.GurdianDaoImpl;
import com.molorane.college.model.Gurdian;

/**
 *
 * @author Mothusi Molorane
 */
public class GurdianBoImpl implements GurdianBo{
    
    private final GurdianDao dao;

    public GurdianBoImpl() {
        this.dao = new GurdianDaoImpl();
    }

    @Override
    public int AddGurdian(Gurdian gurdian) {
        return dao.AddGurdian(gurdian);
    }

    @Override
    public int EditGurdian(Gurdian gurdian) {
        return dao.EditGurdian(gurdian);
    }

    @Override
    public int RemoveGurdian(long personId) {
        return dao.RemoveGurdian(personId);
    }

    @Override
    public Gurdian GetGurdian(long personId) {
        return dao.GetGurdian(personId);
    }
    
}
