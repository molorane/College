/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao.impl;

import com.molorane.college.db.DBConnection;
import com.molorane.college.custom.Functions;
import com.molorane.college.dao.RaceDao;
import com.molorane.college.model.Race;
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
public class RaceDaoImpl extends RaceDao{

    @Override
    public int AddRace(Race race) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainRace(?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, -1);
            cs.setString(2, race.getRace());
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(3);
        } catch (SQLException e) {
            Functions.errorMessage("MaintainRace error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int EditRace(Race race) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainRace(?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, race.getRaceId());
            cs.setString(2, race.getRace());
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(3);
        } catch (SQLException e) {
            Functions.errorMessage("MaintainRace error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int RemoveRace(int raceId) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "CALL RemoveRace(?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, raceId);
            // execute the java preparedstatement
            int count = pst.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("RemoveRace error: "+ e.getLocalizedMessage());
        }
        return 0;
    }

    @Override
    public Race GetRace(int raceId) {
        try{
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetRace(?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, raceId);
            rs = pst.executeQuery();
            if(rs.next())
                return GetRaceDetailsFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getRace error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Race> GetAllRaces() {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetAllRaces()";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            return GetRaceDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getAllRaces error: "+ e.getLocalizedMessage());
        }
        return null;
    }
}
