/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao.impl;

import com.nanoware.bll.Functions;
import com.nanoware.dao.RelationshipDao;
import com.nanoware.model.Relationship;
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
public class RelationshipDaoImpl extends RelationshipDao{

    @Override
    public int AddRelationship(Relationship relationship) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainRelationship(?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, -1);
            cs.setString(2, relationship.getRelationship());
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(3);
        } catch (SQLException e) {
            Functions.errorMessage("MaintainRelationship error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int EditRelationship(Relationship relationship) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainRelationship(?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, relationship.getRelationshipId());
            cs.setString(2, relationship.getRelationship());
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(3);
        } catch (SQLException e) {
            Functions.errorMessage("MaintainRelationship error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int RemoveRelationship(int relationshipId) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "CALL RemoveRelationship(?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, relationshipId);
            // execute the java preparedstatement
            int count = pst.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("RemoveRelationship error: "+ e.getLocalizedMessage());
        }
        return 0;
    }

    @Override
    public Relationship GetRelationship(int relationshipId) {
        try{
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetRelationship(?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, relationshipId);
            rs = pst.executeQuery();
            if(rs.next())
                return GetRelationshipDetailsFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getRelationship error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Relationship> GetAllRelationships() {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetAllRelationships()";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            return GetRelationshipDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getAllRelationships error: "+ e.getLocalizedMessage());
        }
        return null;
    }
    
}
