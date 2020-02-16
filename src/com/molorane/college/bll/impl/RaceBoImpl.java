/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll.impl;

import com.molorane.college.bll.RaceBo;
import com.molorane.college.dao.RaceDao;
import com.molorane.college.dao.impl.RaceDaoImpl;
import com.molorane.college.model.Race;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author Mothusi Molorane
 */
public class RaceBoImpl implements RaceBo{
    
    private final RaceDao dao;

    public RaceBoImpl() {
        this.dao = new RaceDaoImpl();
    }

    @Override
    public int AddRace(Race race) {
        return dao.AddRace(race);
    }

    @Override
    public int EditRace(Race race) {
        return dao.EditRace(race);
    }

    @Override
    public int RemoveRace(int raceId) {
        return dao.RemoveRace(raceId);
    }

    @Override
    public Race GetRace(int raceId) {
        return dao.GetRace(raceId);
    }

    @Override
    public ArrayList<Race> GetAllRaces() {
        return dao.GetAllRaces();
    }
    
    public void fillComboBoxRace(JComboBox con){
        con.removeAllItems();
        Race r = new Race();
        r.setRace(0, "SELECT");
        con.addItem(r);
        GetAllRaces().forEach((bean) -> {
            con.addItem(bean);
        });
    }
    
}
