/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao;

import com.molorane.college.model.PFile;
import java.io.FileInputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class PFileDao {
    
    
    public abstract int UploadFile(PFile file);
    public abstract PFile GetFile(int upId);
    public abstract int RemoveFile(int upId);
    public abstract ArrayList<PFile> GetFiles(long personId);
    
    // PFile CONVERSION METHODS
    protected PFile GetPFileDetailsFromResultSet(ResultSet rs) throws SQLException, ParseException{
        PFile file = new PFile();
        file.setPFile(rs.getInt("upId"),
                        rs.getLong("personId"), 
                        rs.getString("fName"), 
                        rs.getBlob("file"));
        return file;
    }
    
    protected PFile GetPlainPFileDetailsFromResultSet(ResultSet rs) throws SQLException, ParseException{
        PFile file = new PFile();
        file.setPFile(rs.getInt("upId"),
                        rs.getLong("personId"), 
                        rs.getString("fName"));
        return file;
    }
    
    protected ArrayList<PFile> GetPFileDetailsCollectionFromResultSet(ResultSet rs, String option) throws SQLException, ParseException{
       ArrayList<PFile> files = new ArrayList<>();
        while (rs.next())
            if(option.equalsIgnoreCase("PLAIN"))
                files.add(GetPlainPFileDetailsFromResultSet(rs));
            else
                files.add(GetPFileDetailsFromResultSet(rs));
        return files;
    }
}
