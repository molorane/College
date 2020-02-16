/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao;

import com.molorane.college.model.Matric;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class MatricDao {
    
    public abstract int AddMatric(Matric matric);
    public abstract int EditMatric(Matric matric);
    public abstract int RemoveMatric(String  idno, int myear, String subject);    
    public abstract Matric GetMatric(String  idno, int myear, String subject);
    public abstract ArrayList<Matric> GetMatricBySubjectAndYear(int myear, String subject);
    public abstract ArrayList<Matric> GetMatricByYear(int myear);
    public abstract ArrayList<Matric> SearchMatricStudent(String search,int selectedYear);
    public abstract List<HashMap<String,Object>> GetAllUniqueYears();
    public abstract List<HashMap<String,Object>> GetAllUniqueSubjects();
    public abstract int EditProfile(String idno, String lastName, String firstName);
    public abstract ArrayList<Matric> GetMatricStudentMarks(String idno);
    public abstract int EditMatricStudentMark(Matric matric);
    public abstract int UploadMatricMarks(List<HashMap<String,Object>> marks);
    public abstract List<HashMap<String, Object>> GetUniqueIDsMatric(int year);
    public abstract int UploadMatricStudents(List<HashMap<String,Object>> students, int year);
    public abstract int AuthorizeMatricMarks(int personId, String subject);
    public abstract int LectureUploadMatricMarks(List<HashMap<String,Object>> marks, int personId);
    public abstract List<HashMap<String, Object>> GetStaffToAuthorize();
    public abstract List<HashMap<String, Object>> GetMarksToAuthorize(int personId,int myear,String subject);
    public abstract int AuthorizeMarks(List<HashMap<String,Object>> marks, int staffNo, int adminId);
    public abstract int LectureUpdateStudentMark(Matric matric, int personId);
    public abstract List<HashMap<String, Object>> GetSubjectsToAuthorize(int personId);
    public abstract int RevertMarkUpdate(List<HashMap<String,Object>> marks, int personId);
    public abstract ArrayList<Matric> GetStudentsForALecture(int year,String subject, String search);
    public abstract List<HashMap<String, Object>> GetAnalysisByTerm(String term, int year);
    public abstract List<HashMap<String, Object>> GetWhoAuthorizedMatricMarks();
    public abstract List<HashMap<String, Object>> GetLogMarksAuthorized(int adminId,int myear,String subject);
    
    
    // MATRIC CONVERSION METHODS
    protected Matric GetMatricDetailsFromResultSet(ResultSet rs) throws SQLException{
        Matric matric = new Matric();
        matric.setMatric(rs.getString("idno"), 
                        rs.getString("lastName"),
                        rs.getString("firstName"),
                        rs.getInt("myear"), 
                        rs.getString("subject"),
                        rs.getString("term1"),
                        rs.getString("term2"),
                        rs.getString("term3"));
        return matric;
    }
    
    protected ArrayList<Matric> GetMatricDetailsCollectionFromResultSet(ResultSet rs) throws SQLException{
       ArrayList<Matric> matric = new ArrayList<>();
        while (rs.next())
            matric.add(GetMatricDetailsFromResultSet(rs));
        return matric;
    }
}
