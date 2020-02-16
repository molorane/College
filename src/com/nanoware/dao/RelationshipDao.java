/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao;

import com.nanoware.model.Relationship;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class RelationshipDao {
    
    public abstract int AddRelationship(Relationship relationship);
    public abstract int EditRelationship(Relationship relationship);
    public abstract int RemoveRelationship(int relationshipId);    
    public abstract Relationship GetRelationship(int relationshipId);    
    public abstract ArrayList<Relationship> GetAllRelationships();
    
    // RACE CONVERSION METHODS
    protected Relationship GetRelationshipDetailsFromResultSet(ResultSet rs) throws SQLException{
        Relationship relationship = new Relationship();
        relationship.setRelationship(rs.getInt("relationshipId"), 
                        rs.getString("relationship"));
        return relationship;
    }
    
    protected ArrayList<Relationship> GetRelationshipDetailsCollectionFromResultSet(ResultSet rs) throws SQLException{
       ArrayList<Relationship> relationships = new ArrayList<>();
        while (rs.next())
            relationships.add(GetRelationshipDetailsFromResultSet(rs));
        return relationships;
    }
}
