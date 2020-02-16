/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao;

import com.molorane.college.model.Race;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class RaceDao {
    
    public abstract int AddRace(Race race);
    public abstract int EditRace(Race race);
    public abstract int RemoveRace(int raceId);    
    public abstract Race GetRace(int raceId);    
    public abstract ArrayList<Race> GetAllRaces();
    
    // RACE CONVERSION METHODS
    protected Race GetRaceDetailsFromResultSet(ResultSet rs) throws SQLException{
        Race race = new Race();
        race.setRace(rs.getInt("raceId"), 
                        rs.getString("race"));
        return race;
    }
    
    protected ArrayList<Race> GetRaceDetailsCollectionFromResultSet(ResultSet rs) throws SQLException{
       ArrayList<Race> races = new ArrayList<>();
        while (rs.next())
            races.add(GetRaceDetailsFromResultSet(rs));
        return races;
    }
}
