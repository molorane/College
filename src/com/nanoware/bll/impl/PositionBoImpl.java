/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll.impl;

import com.nanoware.bll.PositionBo;
import com.nanoware.dao.PositionDao;
import com.nanoware.dao.impl.PositionDaoImpl;
import com.nanoware.model.Position;
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
