/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao.impl;

import com.nanoware.bll.Functions;
import com.nanoware.dao.TermDao;
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
public class TermDaoImpl extends TermDao {

    @Override
    public int AddTerm(Term term) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainTerm(?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, -1);
            cs.setString(2, term.getTerm());
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(3);
        } catch (SQLException e) {
            Functions.errorMessage("MaintainTerm error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int EditTerm(Term term) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainTerm(?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, term.getTermId());
            cs.setString(2, term.getTerm());
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(3);
        } catch (SQLException e) {
            Functions.errorMessage("MaintainTerm error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int RemoveTerm(int termId) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "CALL RemoveTerm(?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, termId);
            int count = pst.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("RemoveTerm error: "+ e.getLocalizedMessage());
        }
        return 0;
    }

    @Override
    public Term GetTerm(int termId) {
        try{
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetTerm(?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, termId);
            rs = pst.executeQuery();
            if(rs.next())
                return GetTermDetailsFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getTerm error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Term> GetAllTerms() {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetAllTerms()";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            return GetTermDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getAllTerms error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Term> GetTermsByYear(int year) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetTermsByYear(?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, year);
            rs = pst.executeQuery();
            return GetTermDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("GetTermsByYear error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Term> GetTermsByDepartment(String dept) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetTermsByDepartment(?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, dept);
            rs = pst.executeQuery();
            return GetTermDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("GetTermsByDepartment error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Term> GetTermsByYearAndDepartment(int year, String dept) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetTermsByYearAndDepartment(?,?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, year);
            pst.setString(2, dept);
            rs = pst.executeQuery();
            return GetTermDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("GetTermsByYearAndDepartment error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public List<HashMap<String, Object>> GetAllTermYears() {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetAllTermYears()";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            return Functions.convertResultsetToList(rs);
        } catch (SQLException e) {
            Functions.errorMessage("GetAllTermYears error: "+ e.getLocalizedMessage());
        }
        return null;
    }
    
}
