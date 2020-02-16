/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao;

import com.molorane.college.model.Country;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class CountryDao {
    
    public abstract int AddCountry(Country country);
    public abstract int EditCountry(Country country);
    public abstract int RemoveCountry(int countryId);    
    public abstract Country GetCountry(int countryId);    
    public abstract ArrayList<Country> GetAllCountries();
    
    // COUNTRY CONVERSION METHODS
    protected Country GetCountryDetailsFromResultSet(ResultSet rs) throws SQLException{
        Country country = new Country();
        country.setCountry(rs.getInt("countryId"), 
                        rs.getString("country"), 
                        rs.getString("nationality")==null? "":rs.getString("nationality"));
        return country;
    }
    
    protected ArrayList<Country> GetCountryDetailsCollectionFromResultSet(ResultSet rs) throws SQLException{
       ArrayList<Country> countries = new ArrayList<>();
        while (rs.next())
            countries.add(GetCountryDetailsFromResultSet(rs));
        return countries;
    }
}
