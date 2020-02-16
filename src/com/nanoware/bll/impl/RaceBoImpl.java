/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll.impl;

import com.nanoware.bll.RaceBo;
import com.nanoware.dao.RaceDao;
import com.nanoware.dao.impl.RaceDaoImpl;
import com.nanoware.model.Race;
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
        GetAllRaces().forEach((bean) -> {
            con.addItem(bean);
        });
    }
    
}
