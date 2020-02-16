/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao.impl;

import com.nanoware.bll.Functions;
import com.nanoware.dao.GradeCategoryDao;
import com.nanoware.model.GradeCategory;
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
public class GradeCategoryDaoImpl extends GradeCategoryDao{

    @Override
    public int AddGradeCategory(GradeCategory category) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainGradeCategory(?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, -1);
            cs.setString(2, category.getGradecategory());
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(3);
        } catch (SQLException e) {
            Functions.errorMessage("MaintainGradeCategory error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int EditGradeCategory(GradeCategory category) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainGradeCategory(?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, -1);
            cs.setString(2, category.getGradecategory());
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(3);
        } catch (SQLException e) {
            Functions.errorMessage("MaintainGradeCategory error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int RemoveGradeCategory(int categoryId) {
         try {
            Connection conn = DBConnection.getConnection();
            String query = "CALL RemoveGradeCategory(?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, categoryId);
            // execute the java preparedstatement
            int count = pst.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("RemoveGradeCategory error: "+ e.getLocalizedMessage());
        }
        return 0;
    }

    @Override
    public GradeCategory GetGradeCategory(int categoryId) {
        try{
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetGradeCategory(?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, categoryId);
            rs = pst.executeQuery();
            if (rs.next()) {
                return GetGradeCategoryDetailsFromResultSet(rs);
            }
        } catch (SQLException e) {
            Functions.errorMessage("getGradeCategory error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<GradeCategory> GetAllGradeCategories() {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetAllGradeCategories()";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            return GetGradeCategoryDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getAllGradeCategories error: "+ e.getLocalizedMessage());
        }
        return null;
    }
    
}
