/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao.impl;

import com.molorane.college.custom.Functions;
import com.molorane.college.dao.PFileDao;
import com.molorane.college.db.DBConnection;
import com.molorane.college.model.PFile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mothusi Molorane
 */
public class PFileDaoImpl extends PFileDao{

    @Override
    public int UploadFile(PFile file) {
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = null;
            String sql = "CALL UploadFile(?,?,?)";
            ps = conn.prepareCall(sql);
            ps.setLong(1, file.getPersonId());
            ps.setString(2, file.getFileName());
            ps.setBlob(3, file.getFile());
            int count = ps.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("UploadFile error: "+ e.getLocalizedMessage());
        }
        return 0;
    }

    @Override
    public PFile GetFile(int upId) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetFile(?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, upId);
            rs = pst.executeQuery();
            if (rs.next()) {
                return GetPFileDetailsFromResultSet(rs);
            }
        } catch (SQLException e) {
            Functions.errorMessage("GetFile error: "+ e.getLocalizedMessage());
        } catch (ParseException ex) {
            Logger.getLogger(PFileDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<PFile> GetFiles(long personId) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetFiles(?)";
            pst = conn.prepareStatement(sql);
            pst.setLong(1, personId);
            rs = pst.executeQuery();
            return GetPFileDetailsCollectionFromResultSet(rs,"PLAIN");
        } catch (SQLException e) {
            Functions.errorMessage("GetFiles error: "+ e.getLocalizedMessage());
        } catch (ParseException ex) {
            Logger.getLogger(PFileDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public int RemoveFile(int upId) {
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = null;
            String sql = "CALL RemoveFile(?)";
            ps = conn.prepareCall(sql);
            ps.setInt(1, upId);
            int count = ps.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("RemoveFile error: "+ e.getLocalizedMessage());
        }
        return 0;
    }
    
}
