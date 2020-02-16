/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll.impl;

import com.molorane.college.bll.LanguageBo;
import com.molorane.college.dao.LanguageDao;
import com.molorane.college.dao.impl.LanguageDaoImpl;
import com.molorane.college.model.Language;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author Mothusi Molorane
 */
public class LanguageBoImpl implements LanguageBo{
    
    private final LanguageDao dao;

    public LanguageBoImpl() {
        this.dao = new LanguageDaoImpl();
    }

    @Override
    public int AddLanguage(Language language) {
        return dao.AddLanguage(language);
    }

    @Override
    public int EditLanguage(Language language) {
        return dao.EditLanguage(language);
    }

    @Override
    public int RemoveLanguage(int languageId) {
        return dao.RemoveLanguage(languageId);
    }

    @Override
    public Language GetLanguage(int languageId) {
        return dao.GetLanguage(languageId);
    }

    @Override
    public ArrayList<Language> GetAllLanguages() {
        return dao.GetAllLanguages();
    }
    
    public void fillComboBoxLanguage(JComboBox con){
        con.removeAllItems();
        Language l = new Language();
        l.setLanguage(0, "SELECT");
        con.addItem(l);
        GetAllLanguages().forEach((bean) -> {
            con.addItem(bean);
        });
    }
    
}
