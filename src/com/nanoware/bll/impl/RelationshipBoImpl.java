/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll.impl;

import com.nanoware.bll.RelationshipBo;
import com.nanoware.dao.RelationshipDao;
import com.nanoware.dao.impl.RelationshipDaoImpl;
import com.nanoware.model.Relationship;
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
        GetAllRelationships().forEach((bean) -> {
            con.addItem(bean);
        });
    }
}
