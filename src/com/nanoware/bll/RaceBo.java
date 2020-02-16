/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll;

import com.nanoware.model.Race;
import java.util.ArrayList;

/**
 * @author Mothusi Molorane
 */
public interface RaceBo {
    public abstract int AddRace(Race race);
    public abstract int EditRace(Race race);
    public abstract int RemoveRace(int raceId);    
    public abstract Race GetRace(int raceId);    
    public abstract ArrayList<Race> GetAllRaces();
}
