/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao.impl;

import com.molorane.college.db.DBConnection;
import com.molorane.college.custom.Functions;
import com.molorane.college.dao.PaymentMethodDao;
import com.molorane.college.model.PaymentMethod;
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
public class PaymentMethodDaoImpl extends PaymentMethodDao{

    @Override
    public int AddPaymentMethod(PaymentMethod category) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainPaymentMethod(?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, -1);
            cs.setString(2, category.getMethod());
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(3);
        } catch (SQLException e) {
            Functions.errorMessage("MaintainPaymentMethod error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int EditPaymentMethod(PaymentMethod category) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainPaymentMethod(?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, category.getMethodId());
            cs.setString(2, category.getMethod());
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(3);
        } catch (SQLException e) {
            Functions.errorMessage("MaintainPaymentMethod error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int RemovePaymentMethod(int methodId) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "CALL RemovePaymentMethod(?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, methodId);
            // execute the java preparedstatement
            int count = pst.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("RemovePaymentMethod error: "+ e.getLocalizedMessage());
        }
        return 0;
    }

    @Override
    public PaymentMethod GetPaymentMethod(int methodId) {
        try{
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetPaymentMethod(?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, methodId);
            rs = pst.executeQuery();
            if (rs.next()) {
                return GetPaymentMethodDetailsFromResultSet(rs);
            }
        } catch (SQLException e) {
            Functions.errorMessage("getPaymentMethod error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<PaymentMethod> GetAllPaymentMethods() {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetAllPaymentMethods()";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            return GetPaymentMethodDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getAllPaymentMethods error: "+ e.getLocalizedMessage());
        }
        return null;
    }
    
}
