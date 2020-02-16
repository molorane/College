/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.model;


/**
 *
 * @author Mothusi Molorane
 */
public class Relationship{
    private int relationshipId;
    private String relationship;
    
    public void setRelationship(int relationshipId,String relationship) {
        this.relationshipId = relationshipId;
        this.relationship = relationship;
    }

    /**
     * @return the relationshipId
     */
    public int getRelationshipId() {
        return relationshipId;
    }

    /**
     * @param relationshipId the relationshipId to set
     */
    public void setRelationshipId(int relationshipId) {
        this.relationshipId = relationshipId;
    }

    /**
     * @return the relationship
     */
    public String getRelationship() {
        return relationship;
    }

    /**
     * @param relationship the relationship to set
     */
    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
    
    @Override
    public String toString(){
        return relationship;
    }
}
