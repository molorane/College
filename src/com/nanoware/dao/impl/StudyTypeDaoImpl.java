/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao.impl;

import com.nanoware.bll.Functions;
import com.nanoware.dao.StudyTypeDao;
import com.nanoware.model.StudyType;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public class StudyTypeDaoImpl  extends StudyTypeDao{
    
    @Override
    public int AddStudyType(StudyType studytype) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainStudyType(?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, -1);
            cs.setString(2, studytype.getStudyType());
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(3);
        } catch (SQLException e) {
            Functions.errorMessage("MaintainStudyType error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int EditStudyType(StudyType studytype) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainStudyType(?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, studytype.getStudyTypeId());
            cs.setString(2, studytype.getStudyType());
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(3);
        } catch (SQLException e) {
            Functions.errorMessage("MaintainStudyType error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int RemoveStudyType(int studytypeId) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "CALL RemoveStudyType(?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, studytypeId);
            // execute the java preparedstatement
            int count = pst.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("RemoveStudyType error: "+ e.getLocalizedMessage());
        }
        return 0;
    }

    @Override
    public StudyType GetStudyType(int studytypeId) {
        try{
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetStudyType(?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, studytypeId);
            rs = pst.executeQuery();
            if(rs.next())
                return GetStudyTypeDetailsFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getStudyType error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<StudyType> GetAllStudyTypes() {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetAllStudyTypes()";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            return GetStudyTypeDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getAllStudyTypes error: "+ e.getLocalizedMessage());
        }
        return null;
    }
}
