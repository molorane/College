/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao.impl;

import com.molorane.college.db.DBConnection;
import com.molorane.college.custom.Functions;
import com.molorane.college.dao.GurdianDao;
import com.molorane.college.model.Gurdian;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Mothusi Molorane
 */
public class GurdianDaoImpl extends GurdianDao{

    @Override
    public int AddGurdian(Gurdian gurdian) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainGurdian(?,?,?,?,?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setLong(1, gurdian.getPersonId());
            cs.setString(2, gurdian.getLastName());
            cs.setString(3, gurdian.getFirstName());
            cs.setInt(4, gurdian.getRelationshipId());
            cs.setString(5, gurdian.getTelephone());
            cs.setString(6, gurdian.getCellphone());
            cs.setString(7, gurdian.getAddress());
            cs.setString(8, gurdian.getPostalCode());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("MaintainGurdian error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int EditGurdian(Gurdian gurdian) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainGurdian(?,?,?,?,?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setLong(1, gurdian.getPersonId());
            cs.setString(2, gurdian.getLastName());
            cs.setString(3, gurdian.getFirstName());
            cs.setInt(4, gurdian.getRelationshipId());
            cs.setString(5, gurdian.getTelephone());
            cs.setString(6, gurdian.getCellphone());
            cs.setString(7, gurdian.getAddress());
            cs.setString(8, gurdian.getPostalCode());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("MaintainGurdian error: "+ e.getLocalizedMessage());
        }
        return 0;
    }

    @Override
    public int RemoveGurdian(long personId) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL RemoveGurdian(?)";
            cs = conn.prepareCall(sql);
            cs.setLong(1, personId);
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("RemoveGurdian error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public Gurdian GetGurdian(long personId) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetGurdian(?)";
            pst = conn.prepareStatement(sql);
            pst.setLong(1, personId);
            rs = pst.executeQuery();
            if (rs.next()) {
                return GetGurdianDetailsFromResultSet(rs);
            }
        } catch (SQLException e) {
            Functions.errorMessage("getGurdian error: "+ e.getLocalizedMessage());
        }
        return null;
    }
    
}
