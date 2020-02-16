/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll.impl;

import com.molorane.college.bll.SubjectBo;
import com.molorane.college.dao.SubjectDao;
import com.molorane.college.dao.impl.SubjectDaoImpl;
import com.molorane.college.model.Subject;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author Mothusi Molorane
 */
public class SubjectBoImpl implements SubjectBo{

    private final SubjectDao dao;

    public SubjectBoImpl() {
        this.dao = new SubjectDaoImpl();
    }

    @Override
    public int AddSubject(Subject subject) {
        return dao.AddSubject(subject);
    }

    @Override
    public int EditSubject(Subject subject) {
        return dao.EditSubject(subject);
    }

    @Override
    public int RemoveSubject(int subjectId) {
        return dao.RemoveSubject(subjectId);
    }

    @Override
    public Subject GetSubject(int subjectId) {
        return dao.GetSubject(subjectId);
    }

    @Override
    public ArrayList<Subject> GetAllSubjects() {
        return dao.GetAllSubjects();
    }
    
    public void fillComboBoxSubject(JComboBox con){
        con.removeAllItems();
        Subject s = new Subject();
        s.setSubject("SELECT");
        con.addItem(s);
        GetAllSubjects().forEach((bean) -> {
            con.addItem(bean);
        });
    }
    
}
