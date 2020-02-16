/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll;

import com.nanoware.model.Country;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public interface CountryBo {
    public abstract int AddCountry(String country,String nationality);
    public abstract int EditCountry(int countryId,String country,String nationality);
    public abstract int RemoveCountry(int countryId);    
    public abstract Country GetCountry(int countryId);    
    public abstract ArrayList<Country> GetAllCountries();
}
