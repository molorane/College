/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll.impl;

import com.molorane.college.bll.PositionBo;
import com.molorane.college.dao.PositionDao;
import com.molorane.college.dao.impl.PositionDaoImpl;
import com.molorane.college.model.Position;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author Mothusi Molorane
 */
public class PositionBoImpl implements PositionBo{
    
    private final PositionDao dao;

    public PositionBoImpl() {
        this.dao = new PositionDaoImpl();
    }

    @Override
    public int AddPosition(Position position) {
        return dao.AddPosition(position);
    }

    @Override
    public int EditPosition(Position position) {
        return dao.EditPosition(position);
    }

    @Override
    public int RemovePosition(int positionId) {
        return dao.RemovePosition(positionId);
    }

    @Override
    public Position GetPosition(int positionId) {
        return dao.GetPosition(positionId);
    }

    @Override
    public ArrayList<Position> GetAllPositions() {
        return dao.GetAllPositions();
    }
    
    public void fillComboBoxPosition(JComboBox con){
        con.removeAllItems();
        GetAllPositions().forEach((bean) -> {
            con.addItem(bean);
        });
    }
    
}
