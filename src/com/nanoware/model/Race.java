/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.model;


/**
 *
 * @author Mothusi Molorane
 */
public class Race{
    
    private int raceId;
    private String race;
    
    public void setRace(int raceId,String race) {
        this.raceId = raceId;
        this.race = race;
    }

    /**
     * @return the raceId
     */
    public int getRaceId() {
        return raceId;
    }

    /**
     * @param raceId the raceId to set
     */
    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    /**
     * @return the race
     */
    public String getRace() {
        return race;
    }

    /**
     * @param race the race to set
     */
    public void setRace(String race) {
        this.race = race;
    }
    
    @Override
    public String toString(){
        return race;
    }
}
