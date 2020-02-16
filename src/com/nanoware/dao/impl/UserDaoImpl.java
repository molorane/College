/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao.impl;

import com.nanoware.bll.Functions;
import com.nanoware.dao.UserDao;
import com.nanoware.model.User;
import com.nanoware.model.Login;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
            List<HashMap<String, Object>> accountStatus = AccountStatus(login.getUsername());
            
            if(!accountStatus.isEmpty()){
                
                boolean isApproved = (boolean)accountStatus.get(0).get("isApproved");
                boolean isLocked  = (boolean)accountStatus.get(0).get("isLocked");
                
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
                        return GetUserDetailsFromResultSet(rs);
                    }else{
                        Functions.errorMessage("Account is locked. Please consult the administrator about the issue.");
                    }
                }else{
                    Functions.errorMessage("Account was created but never approved. Please consult the administrator to approve the account.");
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
    public List<HashMap<String, Object>> AccountStatus(String username) throws Exception {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL AccountStatus(?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            rs = pst.executeQuery();
            return Functions.convertResultsetToList(rs);
        } catch (SQLException e) {
            Functions.errorMessage("AccountStatus error: "+ e.getLocalizedMessage());
        }
        return null;
    }
}
