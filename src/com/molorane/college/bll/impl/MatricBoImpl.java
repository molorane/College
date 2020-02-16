/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll.impl;

import com.molorane.college.bll.MatricBo;
import com.molorane.college.dao.MatricDao;
import com.molorane.college.dao.impl.MatricDaoImpl;
import com.molorane.college.model.Matric;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author Mothusi Molorane
 */
public class MatricBoImpl implements MatricBo{
    
    private final MatricDao dao;

    public MatricBoImpl() {
        this.dao = new MatricDaoImpl();
    }

    @Override
    public int AddMatric(Matric matric) {
        return dao.AddMatric(matric);
    }

    @Override
    public int EditMatric(Matric matric) {
        return dao.EditMatric(matric);
    }

    @Override
    public int RemoveMatric(String idno, int myear, String subject) {
        return dao.RemoveMatric(idno, myear, subject);
    }

    @Override
    public Matric GetMatric(String idno, int myear, String subject) {
        return dao.GetMatric(idno, myear, subject);
    }

    @Override
    public ArrayList<Matric> GetMatricBySubjectAndYear(int myear, String subject) {
        return dao.GetMatricBySubjectAndYear(myear, subject);
    }

    @Override
    public ArrayList<Matric> GetMatricByYear(int myear) {
        return dao.GetMatricByYear(myear);
    }

    @Override
    public ArrayList<Matric> SearchMatricStudent(String search,int selectedYear) {
        return dao.SearchMatricStudent(search,selectedYear);
    }

    @Override
    public List<HashMap<String, Object>> GetAllUniqueYears() {
        return dao.GetAllUniqueYears();
    }

    @Override
    public List<HashMap<String, Object>> GetAllUniqueSubjects() {
        return dao.GetAllUniqueSubjects();
    }
    
    public void fillComboBoxYear(JComboBox con){
        con.removeAllItems();
        con.addItem("2018");
        GetAllUniqueYears().forEach((bean) -> {
            con.addItem(bean.get("myear"));
        });
        con.addItem("2020");
    }
    
    public void fillComboBoxSubject(JComboBox con){
        con.removeAllItems();
        GetAllUniqueSubjects().forEach((bean) -> {
            con.addItem(bean.get("subject"));
        });
    }

    @Override
    public int EditProfile(String idno, String lastName, String firstName) {
        return dao.EditProfile(idno, lastName, firstName);
    }

    @Override
    public ArrayList<Matric> GetMatricStudentMarks(String idno) {
        return dao.GetMatricStudentMarks(idno);
    }

    @Override
    public int EditMatricStudentMark(Matric matric) {
        return dao.EditMatricStudentMark(matric);
    }

    @Override
    public int UploadMatricMarks(List<HashMap<String, Object>> marks) {
        return dao.UploadMatricMarks(marks);
    }

    @Override
    public List<HashMap<String, Object>> GetUniqueIDsMatric(int year) {
        return dao.GetUniqueIDsMatric(year);
    }

    @Override
    public int UploadMatricStudents(List<HashMap<String, Object>> students, int year) {
        return dao.UploadMatricStudents(students, year);
    } 

    @Override
    public int LectureUploadMatricMarks(List<HashMap<String, Object>> marks, int personId) {
        return dao.LectureUploadMatricMarks(marks, personId);
    }

    @Override
    public List<HashMap<String, Object>> GetStaffToAuthorize() {
        return dao.GetStaffToAuthorize();
    }

    @Override
    public List<HashMap<String, Object>> GetMarksToAuthorize(int personId, int myear, String subject) {
        return dao.GetMarksToAuthorize(personId, myear, subject);
    }

    @Override
    public int AuthorizeMarks(List<HashMap<String, Object>> marks, int staffNo, int adminId) {
        return dao.AuthorizeMarks(marks, staffNo, adminId);
    }

    @Override
    public int LectureUpdateStudentMark(Matric matric, int personId) {
        return dao.LectureUpdateStudentMark(matric, personId);
    }

    @Override
    public List<HashMap<String, Object>> GetSubjectsToAuthorize(int personId) {
        return dao.GetSubjectsToAuthorize(personId);
    }

    @Override
    public int RevertMarkUpdate(List<HashMap<String, Object>> marks, int personId) {
        return dao.RevertMarkUpdate(marks, personId);
    }

    @Override
    public ArrayList<Matric> GetStudentsForALecture(int year, String subject, String search) {
        return dao.GetStudentsForALecture(year, subject, search);
    }

    @Override
    public List<HashMap<String, Object>> GetAnalysisByTerm(String term, int year) {
        return dao.GetAnalysisByTerm(term, year);
    }

    @Override
    public List<HashMap<String, Object>> GetWhoAuthorizedMatricMarks() {
        return dao.GetWhoAuthorizedMatricMarks();
    }

    @Override
    public List<HashMap<String, Object>> GetLogMarksAuthorized(int adminId, int myear, String subject) {
        return dao.GetLogMarksAuthorized(adminId, myear, subject);
    }

}
