/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao;

import com.nanoware.model.PaymentMethod;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class PaymentMethodDao {
    
    public abstract int AddPaymentMethod(PaymentMethod method);
    public abstract int EditPaymentMethod(PaymentMethod method);
    public abstract int RemovePaymentMethod(int methodId);    
    public abstract PaymentMethod GetPaymentMethod(int methodId);    
    public abstract ArrayList<PaymentMethod> GetAllPaymentMethods();
    
    // RACE CONVERSION METHODS
    protected PaymentMethod GetPaymentMethodDetailsFromResultSet(ResultSet rs) throws SQLException{
        PaymentMethod method = new PaymentMethod();
        method.setPaymentMethod(rs.getInt("methodId"), 
                        rs.getString("method"));
        return method;
    }
    
    protected ArrayList<PaymentMethod> GetPaymentMethodDetailsCollectionFromResultSet(ResultSet rs) throws SQLException{
       ArrayList<PaymentMethod> methods = new ArrayList<>();
        while (rs.next())
            methods.add(GetPaymentMethodDetailsFromResultSet(rs));
        return methods;
    }
}
