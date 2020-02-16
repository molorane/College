/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao.impl;

import com.nanoware.bll.Functions;
import com.nanoware.dao.PaymentCategoryDao;
import com.nanoware.model.PaymentCategory;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public class PaymentCategoryDaoImpl extends PaymentCategoryDao{

    @Override
    public int AddPaymentCategory(PaymentCategory category) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainPaymentCategory(?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, -1);
            cs.setString(2, category.getCategory());
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(3);
        } catch (SQLException e) {
            Functions.errorMessage("MaintainPaymentCategory error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int EditPaymentCategory(PaymentCategory category) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainPaymentCategory(?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, category.getCategoryId());
            cs.setString(2, category.getCategory());
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(3);
        } catch (SQLException e) {
            Functions.errorMessage("MaintainPaymentCategory error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int RemovePaymentCategory(int categoryId) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "CALL RemovePaymentCategory(?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, categoryId);
            // execute the java preparedstatement
            int count = pst.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("RemovePaymentCategory error: "+ e.getLocalizedMessage());
        }
        return 0;
    }

    @Override
    public PaymentCategory GetPaymentCategory(int categoryId) {
        try{
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetPaymentCategory(?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, categoryId);
            rs = pst.executeQuery();
            if (rs.next()) {
                return GetPaymentCategoryDetailsFromResultSet(rs);
            }
        } catch (SQLException e) {
            Functions.errorMessage("getPaymentCategory error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<PaymentCategory> GetAllPaymentCategories() {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetAllPaymentCategories()";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            return GetPaymentCategoryDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getAllPaymentCategories error: "+ e.getLocalizedMessage());
        }
        return null;
    }
    
}
