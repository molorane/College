/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao.impl;

import com.nanoware.bll.Functions;
import com.nanoware.dao.CampusDao;
import com.nanoware.dao.LectureDao;
import com.nanoware.dao.ModuleDao;
import com.nanoware.dao.TermDao;
import com.nanoware.model.Campus;
import com.nanoware.model.Lecture;
import com.nanoware.model.Module;
import com.nanoware.model.Term;
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
public class LectureDaoImpl extends LectureDao{


    @Override
    public ArrayList<Campus> GetLectureCampuses(int lectureId) {
         try {
            CampusDao c = new CampusDaoImpl();
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetLectureCampuses(?)";
            pst = conn.prepareStatement(sql);
            pst.setLong(1, lectureId);
            rs = pst.executeQuery();
            return c.GetCampusDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getLectureTerms error: "+ e.getLocalizedMessage());
        }
        return null;
    }
    
    @Override
    public ArrayList<Term> GetLectureTermsInCampus(int lectureId, int campusCode) {
        try {
            TermDao m = new TermDaoImpl();
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetLectureTermsInCampus(?,?)";
            pst = conn.prepareStatement(sql);
            pst.setLong(1, lectureId);
            pst.setInt(2, campusCode);
            rs = pst.executeQuery();
            return m.GetTermDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getLectureTerms error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Module> GetLectureModulesForTerm(int lectureId,int campusCode, int termId) {
        try {
            ModuleDao m = new ModuleDaoImpl();
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetLectureModulesForTerm(?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setLong(1, lectureId);
            pst.setLong(2, campusCode);
            pst.setLong(3, termId);
            rs = pst.executeQuery();
            return m.GetModuleDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getLectureModulesForTerm error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public List<HashMap<String, Object>> GetLectureStudentsInTerm(int lectureId, int campusCode, int termId, String moduleCode) {
        try {
            ModuleDao m = new ModuleDaoImpl();
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetLectureStudentsInTerm(?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, lectureId);
            pst.setInt(2, campusCode);
            pst.setInt(3, termId);
            pst.setString(4, moduleCode);
            rs = pst.executeQuery();
            return Functions.convertResultsetToList(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getLectureStudentsInTerm error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public int AddLectureModule(Lecture lecture) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainLectureModule(?,?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, lecture.getLmId());
            cs.setInt(2, lecture.getLectureId());
            cs.setInt(3, lecture.getCampusCode());
            cs.setInt(4, lecture.getTermId());
            cs.setString(5, lecture.getModuleCode());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("MaintainLectureModule error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int EditLectureModule(Lecture lecture) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainLectureModule(?,?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, lecture.getLmId());
            cs.setInt(2, lecture.getLectureId());
            cs.setInt(3, lecture.getCampusCode());
            cs.setInt(4, lecture.getTermId());
            cs.setString(5, lecture.getModuleCode());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("MaintainLectureModule error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int RemoveLectureModule(int lmId) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL RemoveLectureModule(?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, lmId);
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("RemoveLectureModule error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public Lecture GetLectureModule(int lmId) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetLectureModule(?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, lmId);
            if (rs.next()) {
                return GetLectureModuleDetailsFromResultSet(rs);
            }
        } catch (SQLException e) {
            Functions.errorMessage("GetLectureModule error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Lecture> GetLectureModules(int lectureId) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetLectureModules(?)";
            pst = conn.prepareStatement(sql);
            pst.setLong(1, lectureId);
            rs = pst.executeQuery();
            return GetLectureModuleDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("GetLectureModules error: "+ e.getLocalizedMessage());
        }
        return null;
    }
    
}
