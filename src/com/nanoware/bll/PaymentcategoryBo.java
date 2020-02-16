/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll;

import com.nanoware.model.PaymentCategory;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public interface PaymentcategoryBo {
    
    public abstract int AddPaymentCategory(PaymentCategory category);
    public abstract int EditPaymentCategory(PaymentCategory category);
    public abstract int RemovePaymentCategory(int categoryId);    
    public abstract PaymentCategory GetPaymentCategory(int categoryId);    
    public abstract ArrayList<PaymentCategory> GetAllPaymentCategories();
}
