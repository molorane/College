/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll;

import com.nanoware.model.Title;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public interface TitleBo {
    
    public abstract int AddTitle(Title title);
    public abstract int EditTitle(Title title);
    public abstract int RemoveTitle(int titleId);    
    public abstract Title GetTitle(int titleId);    
    public abstract ArrayList<Title> GetAllTitles();
}
