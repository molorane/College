/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll.impl;

import com.nanoware.bll.PaymentcategoryBo;
import com.nanoware.dao.PaymentCategoryDao;
import com.nanoware.dao.impl.PaymentCategoryDaoImpl;
import com.nanoware.model.PaymentCategory;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author Mothusi Molorane
 */
public class PaymentCategoryBoImpl implements PaymentcategoryBo{
    
    private final PaymentCategoryDao dao;

    public PaymentCategoryBoImpl() {
        this.dao = new PaymentCategoryDaoImpl();
    }
    
    @Override
    public int AddPaymentCategory(PaymentCategory category) {
        return dao.AddPaymentCategory(category);
    }

    @Override
    public int EditPaymentCategory(PaymentCategory category) {
        return dao.EditPaymentCategory(category);
    }

    @Override
    public int RemovePaymentCategory(int categoryId) {
        return dao.RemovePaymentCategory(categoryId);
    }

    @Override
    public PaymentCategory GetPaymentCategory(int categoryId) {
        return dao.GetPaymentCategory(categoryId);
    }

    @Override
    public ArrayList<PaymentCategory> GetAllPaymentCategories() {
        return dao.GetAllPaymentCategories();
    }
    
    public void fillComboBoxPaymentCategory(JComboBox con){
        con.removeAllItems();
        GetAllPaymentCategories().forEach((bean) -> {
            con.addItem(bean);
        });
    }
    
}
