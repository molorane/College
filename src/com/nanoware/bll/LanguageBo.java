/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll;

import com.nanoware.model.Language;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public interface LanguageBo {
    public abstract int AddLanguage(Language language);
    public abstract int EditLanguage(Language language);
    public abstract int RemoveLanguage(int languageId);    
    public abstract Language GetLanguage(int languageId);    
    public abstract ArrayList<Language> GetAllLanguages();
}
