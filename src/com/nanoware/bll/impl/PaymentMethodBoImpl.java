/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll.impl;

import com.nanoware.bll.PaymentMethodBo;
import com.nanoware.dao.PaymentMethodDao;
import com.nanoware.dao.impl.PaymentMethodDaoImpl;
import com.nanoware.model.PaymentMethod;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public class PaymentMethodBoImpl implements PaymentMethodBo{
    
    private final PaymentMethodDao dao;

    public PaymentMethodBoImpl() {
        this.dao = new PaymentMethodDaoImpl();
    }

    @Override
    public int AddPaymentMethod(PaymentMethod method) {
        return dao.AddPaymentMethod(method);
    }

    @Override
    public int EditPaymentMethod(PaymentMethod method) {
        return dao.EditPaymentMethod(method);
    }

    @Override
    public int RemovePaymentMethod(int methodId) {
        return dao.RemovePaymentMethod(methodId);
    }

    @Override
    public PaymentMethod GetPaymentMethod(int methodId) {
        return dao.GetPaymentMethod(methodId);
    }

    @Override
    public ArrayList<PaymentMethod> GetAllPaymentMethods() {
        return dao.GetAllPaymentMethods();
    }    
}
