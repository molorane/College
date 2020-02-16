/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll.impl;

import com.molorane.college.bll.RelationshipBo;
import com.molorane.college.dao.RelationshipDao;
import com.molorane.college.dao.impl.RelationshipDaoImpl;
import com.molorane.college.model.Relationship;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author Mothusi Molorane
 */
public class RelationshipBoImpl implements RelationshipBo{
    
    private final RelationshipDao dao;

    public RelationshipBoImpl() {
        this.dao = new RelationshipDaoImpl();
    }

    @Override
    public int AddRelationship(Relationship relationship) {
        return dao.AddRelationship(relationship);
    }

    @Override
    public int EditRelationship(Relationship relationship) {
        return dao.EditRelationship(relationship);
    }

    @Override
    public int RemoveRelationship(int relationshipId) {
        return dao.RemoveRelationship(relationshipId);
    }

    @Override
    public Relationship GetRelationship(int relationshipId) {
        return dao.GetRelationship(relationshipId);
    }

    @Override
    public ArrayList<Relationship> GetAllRelationships() {
        return dao.GetAllRelationships();
    }
    
    public void fillComboBoxRelationship(JComboBox con){
        con.removeAllItems();
        Relationship r = new Relationship();
        r.setRelationship(0, "SELECT");
        con.addItem(r);
        GetAllRelationships().forEach((bean) -> {
            con.addItem(bean);
        });
    }
}
