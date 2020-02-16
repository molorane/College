/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll.impl;

import com.molorane.college.bll.PinBo;
import com.molorane.college.dao.PinDao;
import com.molorane.college.dao.impl.PinDaoImpl;
import com.molorane.college.model.Pin;

/**
 *
 * @author Mothusi Molorane
 */
public class PinBoImpl implements PinBo{
    
    private final PinDao dao;

    public PinBoImpl() {
        this.dao = new PinDaoImpl();
    }

    @Override
    public int AddPin(Pin pin) {
        return dao.AddPin(pin);
    }

    @Override
    public int EditPin(Pin pin) {
        return dao.EditPin(pin);
    }

    @Override
    public boolean validatePin(String pin) {
        return dao.validatePin(pin);
    }

    @Override
    public boolean IsAdminPinValid(int personId, String pin) {
        return dao.IsAdminPinValid(personId, pin);
    }

    @Override
    public boolean updateAdminPin(int personId, String pin) {
        return dao.updateAdminPin(personId, pin);
    }
    
}
