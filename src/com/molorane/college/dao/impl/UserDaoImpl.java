/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao.impl;

import com.molorane.college.db.DBConnection;
import com.molorane.college.custom.Functions;
import com.molorane.college.dao.UserDao;
import com.molorane.college.model.User;
import com.molorane.college.model.Login;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mothusi Molorane
 */
public class UserDaoImpl extends UserDao{

    @Override
    public int AddUser(User user) throws SQLException{
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainUser(?,?,?,?,?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setString(1, user.getUserName());
            cs.setInt(2, user.getPersonId());
            cs.setString(3, user.getEmail());
            cs.setString(4, user.getPassword());
            cs.setInt(5, user.getRoleID());
            cs.setInt(6, user.getIsApproved());
            cs.setInt(7, user.getIsLocked());
            cs.setInt(8, user.getCreateBy());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("MaintainUser error: "+ e.getLocalizedMessage());
        }
        return 0;
    }

    @Override
    public int EditUser(User user) throws SQLException{
       try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainUser(?,?,?,?,?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setString(1, user.getUserName());
            cs.setInt(2, user.getPersonId());
            cs.setString(3, user.getEmail());
            cs.setString(4, user.getPassword());
            cs.setInt(5, user.getRoleID());
            cs.setInt(6, user.getIsApproved());
            cs.setInt(7, user.getIsLocked());
            cs.setInt(8, user.getCreateBy());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("MaintainUser error: "+ e.getLocalizedMessage());
        }
        return 0;
    }

    @Override
    public int RemoveUser(String userName) throws SQLException{
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL RemoveUser(?)";
            cs = conn.prepareCall(sql);
            cs.setString(1, userName);
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("RemoveUser error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public User GetUser(String userName) throws SQLException{
       try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetUser(?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, userName);
            rs = pst.executeQuery();
            if(rs.next())
                return GetUserDetailsFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getUser error: "+ e.getLocalizedMessage());
        } catch (ParseException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, "getUser error: "+ex.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public User GetUserByPersonId(int personId) throws SQLException{
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetUserByPersonId(?)";
            pst.setInt(1, personId);
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            return GetUserDetailsFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getUserByPersonId error: "+ e.getLocalizedMessage());
        } catch (ParseException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, "getUser error: "+ex.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public User GetUserByUsername(String userName) throws SQLException{
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetUserByUsername(?)";
            pst.setString(1, userName);
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            return GetUserDetailsFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getUserByUsername error: "+ e.getLocalizedMessage());
        } catch (ParseException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, "getUser error: "+ex.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<User> GetUsersByRole(int roleId) throws SQLException{
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetUsersByRole(?)";
            pst.setInt(1, roleId);
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            return GetUserDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getUsersByRole error: "+ e.getLocalizedMessage());
        } catch (ParseException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, "getUsersByRole error: "+ ex.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<User> GetAllUsers() throws SQLException{
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetAllUsers()";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            return GetUserDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getAllUsers error: "+ e.getLocalizedMessage());
        } catch (ParseException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, "getAllUsers error: "+ ex.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public int SetPasswordQuestion(String userName, String passwordQuestion, String passwordAnswer) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User GetPasswordQuestion(String userName, String passwordAnswer) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User ValidateUser(Login login) throws Exception {
        try {
            HashMap<String, Object> accountStatus = AccountStatus(login.getUsername());
            
            if(accountStatus != null){
                
                boolean isApproved = (boolean)accountStatus.get("IsApproved");
                boolean isLocked  = (boolean)accountStatus.get("IsLocked");
                
                if(isApproved){
                    if(!isLocked){
                        Connection conn = DBConnection.getConnection();
                        ResultSet rs = null;
                        PreparedStatement pst = null;
                        String sql = "CALL validateUser(?,?)";
                        pst = conn.prepareStatement(sql);
                        pst.setString(1, login.getUsername());
                        pst.setString(2, login.getUserPassword());
                        rs = pst.executeQuery();
                        if(rs.next()){
                            return GetUserDetailsFromResultSet(rs);
                        }else{
                            return null;
                        }
                    }else{
                        Functions.errorMessage("Account is locked");
                    }
                }else{
                    Functions.errorMessage("Account needs approval.");
                }
            }else{
                Functions.errorMessage("Account does not exist");
            }
        } catch (SQLException e) {
            Functions.errorMessage("validateUser error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public boolean IsAccountActive(String username) throws Exception {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL IsAccountActive(?,?)";
            cs = conn.prepareCall(sql);
            cs.setString(1, username);
            cs.registerOutParameter(2, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(2) > 0;
        } catch (SQLException e) {
            Functions.errorMessage("IsAccountActive error: "+ e.getLocalizedMessage());
        }        
        return false;
    }

    @Override
    public boolean IsAccountLocked(String username) throws Exception {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL IsAccountLocked(?,?)";
            cs = conn.prepareCall(sql);
            cs.setString(1, username);
            cs.registerOutParameter(2, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(2) > 0;
        } catch (SQLException e) {
            Functions.errorMessage("IsAccountLocked  error: "+ e.getLocalizedMessage());
        }        
        return false;
    }

    @Override
    public HashMap<String, Object> AccountStatus(String username) throws Exception {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL AccountStatus(?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            rs = pst.executeQuery();
            if(rs.next())
                return Functions.convertResultsetToRecord(rs);
        } catch (SQLException e) {
            Functions.errorMessage("AccountStatus error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public boolean validateOldPassword(int personId, String oldPassword) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL validateOldPassword(?,?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, personId);
            pst.setString(2, oldPassword);
            rs = pst.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            Functions.errorMessage("validateOldPassword error: "+ e.getLocalizedMessage());
        }
        return false;
    }

    @Override
    public boolean changePassword(int personId, String newPassword) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL changePassword(?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, personId);
            cs.setString(2, newPassword);
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(3) > 0;
        } catch (SQLException e) {
            Functions.errorMessage("changePassword error: "+ e.getLocalizedMessage());
        }
        return false;
    }

    @Override
    public int AddStaffAccount(User user) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL AddStaffAccount(?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setInt(1, user.getPersonId());
            cs.setString(2, user.getEmail());
            cs.setInt(3, user.getRoleID());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("AddStaffAccount error: "+ e.getLocalizedMessage());
        }
        return 0;
    }
}
