/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao.impl;

import com.nanoware.bll.Functions;
import com.nanoware.dao.AssessmentCategoryDao;
import com.nanoware.model.AssessmentCategory;
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
public class AssessmentCategoryDaoImpl extends AssessmentCategoryDao{

    @Override
    public int AddAssessmentCategory(AssessmentCategory category) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainAssessmentCategory(?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, -1);
            cs.setString(2, category.getAsscategory());
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(3);
        } catch (SQLException e) {
            Functions.errorMessage("MaintainAssessmentCategory error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int EditAssessmentCategory(AssessmentCategory category) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainAssessmentCategory(?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, -1);
            cs.setString(2, category.getAsscategory());
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(3);
        } catch (SQLException e) {
            Functions.errorMessage("MaintainAssessmentCategory error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int RemoveAssessmentCategory(int categoryId) {
         try {
            Connection conn = DBConnection.getConnection();
            String query = "CALL RemoveAssessmentCategory(?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, categoryId);
            // execute the java preparedstatement
            int count = pst.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("RemoveAssessmentCategory error: "+ e.getLocalizedMessage());
        }
        return 0;
    }

    @Override
    public AssessmentCategory getAssessmentCategory(int categoryId) {
        try{
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL getAssessmentCategory(?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, categoryId);
            rs = pst.executeQuery();
            if (rs.next()) {
                return GetAssessmentCategoryDetailsFromResultSet(rs);
            }
        } catch (SQLException e) {
            Functions.errorMessage("getAssessmentCategory error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<AssessmentCategory> getAllAssessmentCategories() {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL getAllAssessmentCategories()";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            return GetAssessmentCategoryDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getAllAssessmentCategories error: "+ e.getLocalizedMessage());
        }
        return null;
    }
    
}
