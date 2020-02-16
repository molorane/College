/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll;

import com.molorane.college.model.Position;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public interface PositionBo {
    public abstract int AddPosition(Position position);
    public abstract int EditPosition(Position position);
    public abstract int RemovePosition(int positionId);    
    public abstract Position GetPosition(int positionId);    
    public abstract ArrayList<Position> GetAllPositions();
}
