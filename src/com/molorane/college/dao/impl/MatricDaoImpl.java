/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao.impl;

import com.molorane.college.custom.Functions;
import com.molorane.college.dao.MatricDao;
import com.molorane.college.db.DBConnection;
import com.molorane.college.model.Matric;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mothusi Molorane
 */
public class MatricDaoImpl extends MatricDao{

    @Override
    public int AddMatric(Matric matric) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainMatric(?,?,?,?,?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setString(1, matric.getIdno());
            cs.setString(2, matric.getLastName());
            cs.setString(3, matric.getFirstName());
            cs.setInt(4, matric.getMyear());
            cs.setString(5, matric.getSubject());
            cs.setString(6, matric.getTerm1());
            cs.setString(7, matric.getTerm2());
            cs.setString(8, matric.getTerm3());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("MaintainMatric error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int EditMatric(Matric matric) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainMatric(?,?,?,?,?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setString(1, matric.getIdno());
            cs.setString(2, matric.getLastName());
            cs.setString(3, matric.getFirstName());
            cs.setInt(4, matric.getMyear());
            cs.setString(5, matric.getSubject());
            cs.setString(6, matric.getTerm1());
            cs.setString(7, matric.getTerm2());
            cs.setString(8, matric.getTerm3());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("MaintainMatric error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int RemoveMatric(String idno, int myear, String subject) {
         try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL RemoveMatric(?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setString(1, idno);
            cs.setInt(2, myear);
            cs.setString(3, subject);
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("RemoveMatric error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public Matric GetMatric(String idno, int myear, String subject) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetMatric(?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, idno);
            pst.setInt(2, myear);
            pst.setString(3, subject);
            rs = pst.executeQuery();
            if (rs.next()) {
                return GetMatricDetailsFromResultSet(rs);
            }
        } catch (SQLException e) {
            Functions.errorMessage("GetMatric error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Matric> GetMatricBySubjectAndYear(int myear, String subject) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetMatricBySubjectAndYear(?,?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, myear);
            pst.setString(2, subject);
            rs = pst.executeQuery();
            return GetMatricDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("GetMatricBySubjectAndYear error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Matric> GetMatricByYear(int myear) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetMatricByYear(?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, myear);
            rs = pst.executeQuery();
            return GetMatricDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("GetMatricByYear error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Matric> SearchMatricStudent(String search, int selectedYear) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL SearchMatricStudent(?,?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, search);
            pst.setInt(2, selectedYear);
            rs = pst.executeQuery();
            return GetMatricDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("SearchMatricStudent error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public List<HashMap<String, Object>> GetAllUniqueYears() {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetAllUniqueYears()";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            return Functions.convertResultsetToList(rs);
        } catch (SQLException e) {
            Functions.errorMessage("GetAllUniqueYears error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public List<HashMap<String, Object>> GetAllUniqueSubjects() {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetAllUniqueSubjects()";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            return Functions.convertResultsetToList(rs);
        } catch (SQLException e) {
            Functions.errorMessage("GetAllUniqueSubjects error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public int EditProfile(String idno, String lastName, String firstName) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL EditProfile(?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setString(1, idno);
            cs.setString(2, lastName);
            cs.setString(3, firstName);
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("EditProfile error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public ArrayList<Matric> GetMatricStudentMarks(String idno) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetMatricStudentMarks(?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, idno);
            rs = pst.executeQuery();
            return GetMatricDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("GetMatricStudentMarks error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public int EditMatricStudentMark(Matric matric) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL EditMatricStudentMark(?,?,?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setString(1, matric.getIdno());
            cs.setInt(2, matric.getMyear());
            cs.setString(3, matric.getSubject());
            cs.setString(4, matric.getTerm1());
            cs.setString(5, matric.getTerm2());
            cs.setString(6, matric.getTerm3());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("EditMatricStudentMark error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int UploadMatricMarks(List<HashMap<String, Object>> marks) {
        try{            
            String sql = "CALL UploadMatricMarks(?,?,?,?,?,?)";
            Connection conn = DBConnection.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pst = conn.prepareStatement(sql);  
            
            for(HashMap<String, Object> mark:marks){
                pst.setString(1, mark.get("IDNo").toString());
                pst.setInt(2, Integer.parseInt(mark.get("Year").toString()));
                pst.setString(3, mark.get("Subject").toString());
                pst.setString(4, mark.get("Term 1").toString());
                pst.setString(5, mark.get("Term 2").toString());
                pst.setString(6, mark.get("Term 3").toString());
                pst.addBatch();
            }
            int[] rows = pst.executeBatch();
            conn.commit();
            conn.close();
            return rows.length;
        } catch (SQLException e) {
            Functions.errorMessage("UploadMatricMarks error: "+ e.getLocalizedMessage());
        }
        return -1;  
    }

    @Override
    public List<HashMap<String, Object>> GetUniqueIDsMatric(int year) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetUniqueIDsMatric(?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, year);
            rs = pst.executeQuery();
            return Functions.convertResultsetToList(rs);
        } catch (SQLException e) {
            Functions.errorMessage("GetUniqueIDsMatric error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public int UploadMatricStudents(List<HashMap<String, Object>> students, int year) {
        try{            
            String sql = "CALL UploadMatricStudents(?,?,?,?,?)";
            Connection conn = DBConnection.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pst = conn.prepareStatement(sql);  
            
            for(HashMap<String, Object> student:students){
                pst.setString(1, student.get("idno").toString());
                pst.setString(2, student.get("lastName").toString());
                pst.setString(3, student.get("names").toString());
                pst.setInt(4, year);
                pst.setString(5, student.get("subject").toString());
                pst.addBatch();
            }
            int[] rows = pst.executeBatch();
            conn.commit();
            conn.close();
            return rows.length;
        } catch (SQLException e) {
            Functions.errorMessage("UploadMatricStudents error: "+ e.getLocalizedMessage());
        }
        return -1;  
    }

    @Override
    public int AuthorizeMatricMarks(int personId, String subject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int LectureUploadMatricMarks(List<HashMap<String, Object>> marks, int personId) {
        try{            
            String sql = "CALL LectureUploadMatricMarks(?,?,?,?,?,?,?,?,?)";
            Connection conn = DBConnection.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pst = conn.prepareStatement(sql);  
            
            for(HashMap<String, Object> mark:marks){
                pst.setString(1, mark.get("idno").toString());
                pst.setString(2, mark.get("lastName").toString());
                pst.setString(3, mark.get("names").toString());
                pst.setInt(4, Integer.parseInt(mark.get("year").toString()));
                pst.setString(5, mark.get("subject").toString());
                pst.setString(6, mark.get("term1").toString());
                pst.setString(7, mark.get("term2").toString());
                pst.setString(8, mark.get("term3").toString());
                pst.setInt(9, personId);
                pst.addBatch();
            }
            int[] rows = pst.executeBatch();
            conn.commit();
            conn.close();
            return rows.length;
        } catch (SQLException e) {
            Functions.errorMessage("LectureUploadMatricMarks error: "+ e.getLocalizedMessage());
        }
        return -1; 
    }

    @Override
    public List<HashMap<String, Object>> GetStaffToAuthorize() {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetStaffToAuthorize()";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            return Functions.convertResultsetToList(rs);
        } catch (SQLException e) {
            Functions.errorMessage("GetStaffToAuthorize error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public List<HashMap<String, Object>> GetMarksToAuthorize(int personId, int myear, String subject) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetMarksToAuthorize(?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, personId);
            pst.setInt(2, myear);
            pst.setString(3, subject);
            rs = pst.executeQuery();
            return Functions.convertResultsetToList(rs);
        } catch (SQLException e) {
            Functions.errorMessage("GetMarksToAuthorize error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public int AuthorizeMarks(List<HashMap<String, Object>> marks, int staffNo, int adminId) {
        try{            
            String sql = "CALL AuthorizeMarks(?,?,?,?,?,?,?,?,?,?)";
            Connection conn = DBConnection.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pst = conn.prepareStatement(sql);
            for(HashMap<String, Object> mark:marks){
                pst.setString(1, mark.get("idno").toString());
                pst.setString(2, mark.get("lastName").toString());
                pst.setString(3, mark.get("firstName").toString());
                pst.setString(4, mark.get("subject").toString());
                pst.setInt(5, Integer.parseInt(mark.get("year").toString()));
                pst.setString(6, mark.get("term1").toString());
                pst.setString(7, mark.get("term2").toString());
                pst.setString(8, mark.get("term3").toString());
                pst.setInt(9, staffNo);
                pst.setInt(10, adminId);
                pst.addBatch();
            }
            int[] rows = pst.executeBatch();
            conn.commit();
            conn.close();
            return rows.length;
        } catch (SQLException e) {
            Functions.errorMessage("AuthorizeMarks error: "+ e.getLocalizedMessage());
        }
        return -1; 
    }

    @Override
    public int LectureUpdateStudentMark(Matric matric, int personId) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL LectureUpdateStudentMark(?,?,?,?,?,?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setString(1, matric.getIdno());
            cs.setString(2, matric.getLastName());
            cs.setString(3, matric.getFirstName());
            cs.setInt(4, matric.getMyear());
            cs.setString(5, matric.getSubject());
            cs.setString(6, matric.getTerm1());
            cs.setString(7, matric.getTerm2());
            cs.setString(8, matric.getTerm3());
            cs.setInt(9, personId);
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("LectureUpdateStudentMark error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public List<HashMap<String, Object>> GetSubjectsToAuthorize(int personId) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetSubjectsToAuthorize(?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, personId);
            rs = pst.executeQuery();
            return Functions.convertResultsetToList(rs);
        } catch (SQLException e) {
            Functions.errorMessage("GetSubjectsToAuthorize error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public int RevertMarkUpdate(List<HashMap<String, Object>> marks, int personId) {
        try{            
            String sql = "CALL RevertMarkUpdate(?,?,?,?)";
            Connection conn = DBConnection.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pst = conn.prepareStatement(sql); 
            for(HashMap<String, Object> mark:marks){
                pst.setString(1, mark.get("idno").toString());
                pst.setInt(2, Integer.parseInt(mark.get("year").toString()));
                pst.setString(3, mark.get("subject").toString());
                pst.setInt(4, personId);
                pst.addBatch();
            }
            int[] rows = pst.executeBatch();
            conn.commit();
            conn.close();
            return rows.length;
        } catch (SQLException e) {
            Functions.errorMessage("RevertMarkUpdate error: "+ e.getLocalizedMessage());
        }
        return -1; 
    }

    @Override
    public ArrayList<Matric> GetStudentsForALecture(int year, String subject, String search) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetStudentsForALecture(?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, year);
            pst.setString(2, subject);
            pst.setString(3, search);
            rs = pst.executeQuery();
            return GetMatricDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("GetStudentsForALecture error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public List<HashMap<String, Object>> GetAnalysisByTerm(String term, int year) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetAnalysisByTerm(?,?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, term);
            pst.setInt(2, year);
            rs = pst.executeQuery();
            return Functions.convertResultsetToList(rs);
        } catch (SQLException e) {
            Functions.errorMessage("GetAnalysisByTerm error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public List<HashMap<String, Object>> GetWhoAuthorizedMatricMarks() {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetWhoAuthorizedMatricMarks()";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            return Functions.convertResultsetToList(rs);
        } catch (SQLException e) {
            Functions.errorMessage("GetWhoAuthorizedMatricMarks error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public List<HashMap<String, Object>> GetLogMarksAuthorized(int adminId, int myear, String subject) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetLogMarksAuthorized(?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, adminId);
            pst.setInt(2, myear);
            pst.setString(3, subject);
            rs = pst.executeQuery();
            return Functions.convertResultsetToList(rs);
        } catch (SQLException e) {
            Functions.errorMessage("GetLogMarksAuthorized error: "+ e.getLocalizedMessage());
        }
        return null;
    }
    
}
