/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao;

import com.molorane.college.model.Position;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class PositionDao {
    
    public abstract int AddPosition(Position position);
    public abstract int EditPosition(Position position);
    public abstract int RemovePosition(int positionId);    
    public abstract Position GetPosition(int positionId);    
    public abstract ArrayList<Position> GetAllPositions();
    
    // POSITION CONVERSION METHODS
    protected Position GetPositionDetailsFromResultSet(ResultSet rs) throws SQLException{
        Position position = new Position();
        position.setPosition(rs.getInt("positionId"), 
                        rs.getString("position"));
        return position;
    }
    
    protected ArrayList<Position> GetPositionDetailsCollectionFromResultSet(ResultSet rs) throws SQLException{
       ArrayList<Position> positions = new ArrayList<>();
        while (rs.next())
            positions.add(GetPositionDetailsFromResultSet(rs));
        return positions;
    }
}
