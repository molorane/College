/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao.impl;

import com.molorane.college.db.DBConnection;
import com.molorane.college.custom.Functions;
import com.molorane.college.dao.CountryDao;
import com.molorane.college.model.Country;
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
public class CountryDaoImpl extends CountryDao{

    @Override
    public int AddCountry(Country country) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainCountry(?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, -1);
            cs.setString(2, country.getCountry());
            cs.setString(3, country.getNationality());
            cs.registerOutParameter(4, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(4);
        } catch (SQLException e) {
            Functions.errorMessage("MaintainCountry error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int EditCountry(Country country) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainCountry(?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, country.getCountryId());
            cs.setString(2, country.getCountry());
            cs.setString(3, country.getNationality());
            cs.registerOutParameter(4, java.sql.Types.INTEGER);
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("MaintainCountry error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int RemoveCountry(int countryId) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "CALL RemoveCountry(?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, countryId);
            // execute the java preparedstatement
            int count = pst.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("RemoveCountry error: "+ e.getLocalizedMessage());
        }
        return 0;
    }

    @Override
    public Country GetCountry(int countryId) {
        try{
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetCountry(?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, countryId);
            rs = pst.executeQuery();
            if (rs.next()) {
                return GetCountryDetailsFromResultSet(rs);
            }
        } catch (SQLException e) {
            Functions.errorMessage("getCountry error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Country> GetAllCountries() {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetAllCountries()";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            return GetCountryDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getAllCountries error: "+ e.getLocalizedMessage());
        }
        return null;
    }
    
}
