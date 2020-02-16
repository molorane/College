/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao.impl;

import com.nanoware.bll.Functions;
import com.nanoware.dao.TitleDao;
import com.nanoware.model.Title;
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
public class TitleDaoImpl extends TitleDao{

    @Override
    public int AddTitle(Title title) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainTitle(?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, -1);
            cs.setString(2, title.getTitle());
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(3);
        } catch (SQLException e) {
            Functions.errorMessage("MaintainTitle error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int EditTitle(Title title) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainTitle(?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, title.getTitleId());
            cs.setString(2, title.getTitle());
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(3);
        } catch (SQLException e) {
            Functions.errorMessage("MaintainTitle error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int RemoveTitle(int titleId) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "CALL RemoveTitle(?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, titleId);
            // execute the java preparedstatement
            int count = pst.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("RemoveTitle error: "+ e.getLocalizedMessage());
        }
        return 0;
    }

    @Override
    public Title GetTitle(int titleId) {
        try{
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetTitle(?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, titleId);
            rs = pst.executeQuery();
            if(rs.next())
                return GetTitleDetailsFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getTitle error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Title> GetAllTitles() {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetAllTitles()";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            return GetTitleDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getAllTitles error: "+ e.getLocalizedMessage());
        }
        return null;
    }
    
}
