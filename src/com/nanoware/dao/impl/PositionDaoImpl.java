/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao.impl;

import com.nanoware.bll.Functions;
import com.nanoware.dao.PositionDao;
import com.nanoware.model.Position;
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
public class PositionDaoImpl extends PositionDao{

    @Override
    public int AddPosition(Position position) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainPosition(?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, -1);
            cs.setString(2, position.getPosition());
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(3);
        } catch (SQLException e) {
            Functions.errorMessage("MaintainPosition error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int EditPosition(Position position) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainPosition(?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, position.getPositionId());
            cs.setString(2, position.getPosition());
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(3);
        } catch (SQLException e) {
            Functions.errorMessage("MaintainPosition error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int RemovePosition(int positionId) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "CALL RemovePosition(?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, positionId);
            // execute the java preparedstatement
            int count = pst.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("RemovePosition error: "+ e.getLocalizedMessage());
        }
        return 0;
    }

    @Override
    public Position GetPosition(int positionId) {
        try{
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetPosition(?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, positionId);
            rs = pst.executeQuery();
            if(rs.next())
                return GetPositionDetailsFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getPosition error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Position> GetAllPositions() {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetAllPositions()";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            return GetPositionDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getAllPositions error: "+ e.getLocalizedMessage());
        }
        return null;
    }
    
}
