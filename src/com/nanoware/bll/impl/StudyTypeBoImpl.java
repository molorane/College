/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll.impl;

import com.nanoware.bll.StudyTypeBo;
import com.nanoware.dao.StudyTypeDao;
import com.nanoware.dao.impl.StudyTypeDaoImpl;
import com.nanoware.model.StudyType;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author Mothusi Molorane
 */
public class StudyTypeBoImpl implements StudyTypeBo{
    
    private final StudyTypeDao dao;

    public StudyTypeBoImpl() {
        this.dao = new StudyTypeDaoImpl();
    }

    @Override
    public int AddStudyType(StudyType studytype) {
        return dao.AddStudyType(studytype);
    }

    @Override
    public int EditStudyType(StudyType studytype) {
        return dao.EditStudyType(studytype);
    }

    @Override
    public int RemoveStudyType(int studytypeId) {
        return dao.RemoveStudyType(studytypeId);
    }

    @Override
    public StudyType GetStudyType(int studytypeId) {
        return dao.GetStudyType(studytypeId);
    }

    @Override
    public ArrayList<StudyType> GetAllStudyTypes() {
        return dao.GetAllStudyTypes();
    }
    
    public void fillComboBoxStudyType(JComboBox con){
        con.removeAllItems();
        GetAllStudyTypes().forEach((bean) -> {
            con.addItem(bean);
        });
    }
    
}
