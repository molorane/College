/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao;

import com.molorane.college.model.Title;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class TitleDao {
    
    public abstract int AddTitle(Title title);
    public abstract int EditTitle(Title title);
    public abstract int RemoveTitle(int titleId);    
    public abstract Title GetTitle(int titleId);    
    public abstract ArrayList<Title> GetAllTitles();
    
    // TITLE CONVERSION METHODS
    protected Title GetTitleDetailsFromResultSet(ResultSet rs) throws SQLException{
        Title title = new Title();
        title.setTitle(rs.getInt("titleId"), 
                        rs.getString("title"));
        return title;
    }
    
    protected ArrayList<Title> GetTitleDetailsCollectionFromResultSet(ResultSet rs) throws SQLException{
       ArrayList<Title> titles = new ArrayList<>();
        while (rs.next())
            titles.add(GetTitleDetailsFromResultSet(rs));
        return titles;
    }
}
