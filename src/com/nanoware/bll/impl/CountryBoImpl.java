/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll.impl;

import com.nanoware.bll.CountryBo;
import com.nanoware.dao.CountryDao;
import com.nanoware.dao.impl.CountryDaoImpl;
import com.nanoware.model.Country;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author Mothusi Molorane
 */
public class CountryBoImpl implements CountryBo{
    
    private final CountryDao dao;

    public CountryBoImpl() {
        this.dao = new CountryDaoImpl();
    }

    @Override
    public int AddCountry(String country, String nationality) {
        Country obj = new Country();
        obj.setCountry(country);
        obj.setNationality(nationality);
        return dao.AddCountry(obj);
    }

    @Override
    public int EditCountry(int countryId, String country, String nationality) {
        Country obj = new Country();
        obj.setCountryId(countryId);
        obj.setCountry(country);
        obj.setNationality(nationality);
        return dao.EditCountry(obj);
    }

    @Override
    public int RemoveCountry(int countryId) {
        return dao.RemoveCountry(countryId);
    }

    @Override
    public Country GetCountry(int countryId) {
        return dao.GetCountry(countryId);
    }

    @Override
    public ArrayList<Country> GetAllCountries() {
        return dao.GetAllCountries();
    }
    
    public void fillComboBoxCountry(JComboBox con){
        con.removeAllItems();
        GetAllCountries().forEach((bean) -> {
            con.addItem(bean);
        });
    }
}
