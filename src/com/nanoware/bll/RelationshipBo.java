/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll;

import com.nanoware.model.Relationship;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public interface RelationshipBo {
    public abstract int AddRelationship(Relationship relationship);
    public abstract int EditRelationship(Relationship relationship);
    public abstract int RemoveRelationship(int relationshipId);    
    public abstract Relationship GetRelationship(int relationshipId);    
    public abstract ArrayList<Relationship> GetAllRelationships();
}
