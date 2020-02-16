/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll.impl;

import com.nanoware.bll.LectureBo;
import com.nanoware.dao.LectureDao;
import com.nanoware.dao.impl.LectureDaoImpl;
import com.nanoware.model.Campus;
import com.nanoware.model.Lecture;
import com.nanoware.model.Module;
import com.nanoware.model.Term;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author Mothusi Molorane
 */
public class LectureBoImpl implements LectureBo{
    
    private final LectureDao dao;

    public LectureBoImpl() {
        this.dao = new LectureDaoImpl();
    }
    
    public void fillComboBoxLectureCampusTermModules(JComboBox con, int lectureId, int campusCode,int termId){
        con.removeAllItems();
        GetLectureModulesForTerm(lectureId,campusCode,termId).forEach((bean) -> {
            con.addItem(bean);
        });
    }
    
    public void fillComboBoxLectureCampuses(JComboBox con, int lectureId){
        con.removeAllItems();
        GetLectureCampuses(lectureId).forEach((bean) -> {
            con.addItem(bean);
        });
    }
    
    public void fillComboBoxLectureCampusTerms(JComboBox con, int lectureId, int campusCode){
        con.removeAllItems();
        GetLectureTermsInCampus(lectureId,campusCode).forEach((bean) -> {
            con.addItem(bean);
        });
    }

    @Override
    public ArrayList<Campus> GetLectureCampuses(int lectureId) {
        return dao.GetLectureCampuses(lectureId);
    }

    @Override
    public ArrayList<Term> GetLectureTermsInCampus(int lectureId, int campusCode) {
        return dao.GetLectureTermsInCampus(lectureId, campusCode);
    }

    @Override
    public ArrayList<Module> GetLectureModulesForTerm(int lectureId, int campusCode, int termId) {
        return dao.GetLectureModulesForTerm(lectureId, campusCode, termId);
    }

    @Override
    public List<HashMap<String, Object>> GetLectureStudentsInTerm(int lectureId, int campusCode, int termId, String moduleCode) {
        return dao.GetLectureStudentsInTerm(lectureId, campusCode, termId, moduleCode);
    }

    @Override
    public int AddLectureModule(Lecture lecture) {
        return dao.AddLectureModule(lecture);
    }

    @Override
    public int EditLectureModule(Lecture lecture) {
        return dao.EditLectureModule(lecture);
    }

    @Override
    public int RemoveLectureModule(int lmId) {
        return dao.RemoveLectureModule(lmId);
    }

    @Override
    public Lecture GetLectureModule(int lmId) {
        return dao.GetLectureModule(lmId);
    }

    @Override
    public ArrayList<Lecture> GetLectureModules(int lectureId) {
        return dao.GetLectureModules(lectureId);
    }
}
