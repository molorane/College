/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao;

import com.nanoware.model.Language;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class LanguageDao {
    
    public abstract int AddLanguage(Language language);
    public abstract int EditLanguage(Language language);
    public abstract int RemoveLanguage(int languageId);    
    public abstract Language GetLanguage(int languageId);    
    public abstract ArrayList<Language> GetAllLanguages();
    
    // RACE CONVERSION METHODS
    protected Language GetLanguageDetailsFromResultSet(ResultSet rs) throws SQLException{
        Language language = new Language();
        language.setLanguage(rs.getInt("languageId"), 
                        rs.getString("language"));
        return language;
    }
    
    protected ArrayList<Language> GetLanguageDetailsCollectionFromResultSet(ResultSet rs) throws SQLException{
       ArrayList<Language> languages = new ArrayList<>();
        while (rs.next())
            languages.add(GetLanguageDetailsFromResultSet(rs));
        return languages;
    }
}
