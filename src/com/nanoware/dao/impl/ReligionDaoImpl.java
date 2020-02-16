/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao.impl;

import com.nanoware.bll.Functions;
import com.nanoware.dao.ReligionDao;
import com.nanoware.model.Religion;
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
public class ReligionDaoImpl extends ReligionDao{

    @Override
    public int AddReligion(Religion religion) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainReligion(?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, -1);
            cs.setString(2, religion.getReligion());
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(3);
        } catch (SQLException e) {
            Functions.errorMessage("MaintainReligion error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int EditReligion(Religion religion) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainReligion(?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, religion.getReligionId());
            cs.setString(2, religion.getReligion());
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(3);
        } catch (SQLException e) {
            Functions.errorMessage("MaintainReligion error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int RemoveReligion(int religionId) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "CALL RemoveReligion(?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, religionId);
            // execute the java preparedstatement
            int count = pst.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("RemoveReligion error: "+ e.getLocalizedMessage());
        }
        return 0;
    }

    @Override
    public Religion GetReligion(int religionId) {
        try{
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetReligion(?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, religionId);
            rs = pst.executeQuery();
            if(rs.next())
                return GetReligionDetailsFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getReligion error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Religion> GetAllReligions() {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetAllReligions()";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            return GetReligionDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getAllReligions error: "+ e.getLocalizedMessage());
        }
        return null;
    }
    
}
