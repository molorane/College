/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao;

import com.nanoware.model.PaymentCategory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class PaymentCategoryDao {
    
    public abstract int AddPaymentCategory(PaymentCategory category);
    public abstract int EditPaymentCategory(PaymentCategory category);
    public abstract int RemovePaymentCategory(int categoryId);    
    public abstract PaymentCategory GetPaymentCategory(int categoryId);    
    public abstract ArrayList<PaymentCategory> GetAllPaymentCategories();
    
    // RACE CONVERSION METHODS
    protected PaymentCategory GetPaymentCategoryDetailsFromResultSet(ResultSet rs) throws SQLException{
        PaymentCategory category = new PaymentCategory();
        category.setPaymentCategory(rs.getInt("categoryId"), 
                        rs.getString("category"));
        return category;
    }
    
    protected ArrayList<PaymentCategory> GetPaymentCategoryDetailsCollectionFromResultSet(ResultSet rs) throws SQLException{
       ArrayList<PaymentCategory> categories = new ArrayList<>();
        while (rs.next())
            categories.add(GetPaymentCategoryDetailsFromResultSet(rs));
        return categories;
    }
}
