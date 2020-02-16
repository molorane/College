/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll;

import com.molorane.college.model.PaymentMethod;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public interface PaymentMethodBo {
    
    public abstract int AddPaymentMethod(PaymentMethod method);
    public abstract int EditPaymentMethod(PaymentMethod method);
    public abstract int RemovePaymentMethod(int methodId);    
    public abstract PaymentMethod GetPaymentMethod(int methodId);    
    public abstract ArrayList<PaymentMethod> GetAllPaymentMethods();
}
