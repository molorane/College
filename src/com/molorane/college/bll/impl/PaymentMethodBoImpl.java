/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll.impl;

import com.molorane.college.bll.PaymentMethodBo;
import com.molorane.college.dao.PaymentMethodDao;
import com.molorane.college.dao.impl.PaymentMethodDaoImpl;
import com.molorane.college.model.PaymentCategory;
import com.molorane.college.model.PaymentMethod;
import java.util.ArrayList;
import javax.swing.JComboBox;

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
    
    public void fillComboBoxPaymentMethod(JComboBox con){
        con.removeAllItems();
        PaymentMethod pm = new PaymentMethod();
        pm.setPaymentMethod(0, "SELECT");
        con.addItem(pm);
        GetAllPaymentMethods().forEach((bean) -> {
            con.addItem(bean);
        });
    }
}
