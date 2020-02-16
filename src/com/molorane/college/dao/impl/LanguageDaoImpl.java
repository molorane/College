/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao.impl;

import com.molorane.college.db.DBConnection;
import com.molorane.college.custom.Functions;
import com.molorane.college.dao.LanguageDao;
import com.molorane.college.model.Language;
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
public class LanguageDaoImpl extends LanguageDao{

    @Override
    public int AddLanguage(Language language) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainLanguage(?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, -1);
            cs.setString(2, language.getLanguage());
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(3);
        } catch (SQLException e) {
            Functions.errorMessage("MaintainLanguage error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int EditLanguage(Language language) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainLanguage(?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, language.getLanguageId());
            cs.setString(2, language.getLanguage());
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(3);
        } catch (SQLException e) {
            Functions.errorMessage("MaintainLanguage error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int RemoveLanguage(int languageId) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "CALL RemoveLanguage(?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, languageId);
            // execute the java preparedstatement
            int count = pst.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("RemoveLanguage error: "+ e.getLocalizedMessage());
        }
        return 0;
    }

    @Override
    public Language GetLanguage(int languageId) {
        try{
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetLanguage(?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, languageId);
            rs = pst.executeQuery();
            if (rs.next()) {
                return GetLanguageDetailsFromResultSet(rs);
            }
        } catch (SQLException e) {
            Functions.errorMessage("getLanguage error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Language> GetAllLanguages() {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetAllLanguages()";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            return GetLanguageDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getAllLanguages error: "+ e.getLocalizedMessage());
        }
        return null;
    }
    
}
