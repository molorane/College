/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao;

import com.nanoware.model.User;
import com.nanoware.model.Login;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class UserDao {
    
    public abstract int AddUser(User user) throws SQLException;
    public abstract int EditUser(User user) throws SQLException;
    public abstract int RemoveUser(String userName) throws SQLException;    
    public abstract User GetUser(String userName) throws SQLException;
    public abstract User GetUserByPersonId(int personId) throws SQLException;
    public abstract User GetUserByUsername(String userName) throws SQLException;
    public abstract ArrayList<User> GetUsersByRole(int roleId) throws SQLException;
    public abstract ArrayList<User> GetAllUsers() throws SQLException;
    public abstract int SetPasswordQuestion(String userName,String passwordQuestion,String passwordAnswer) throws SQLException;
    public abstract User GetPasswordQuestion(String userName,String passwordAnswer) throws SQLException;
    public abstract User ValidateUser(Login login) throws Exception;
    public abstract boolean IsAccountActive(String username) throws Exception;
    public abstract boolean IsAccountLocked(String username) throws Exception;
    public abstract List<HashMap<String, Object>> AccountStatus(String username) throws Exception;
    
    // COURSE CONVERSION METHODS
    protected User GetUserDetailsFromResultSet(ResultSet rs) throws SQLException, ParseException{
        User user = new User();
                    user.setUser(rs.getString("userName"), 
                        Integer.parseInt(rs.getString("personId")),
                        rs.getString("email"),
                        rs.getString("password"),
                        Integer.parseInt(rs.getString("isApproved")),
                        Integer.parseInt(rs.getString("isLocked")),
                        rs.getString("createDate"),
                        Integer.parseInt(rs.getString("createBy")), 
                        Integer.parseInt(rs.getString("roleID")),
                        rs.getString("roleName"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("telephone"),
                        rs.getString("profile"));
        return user;
    }
    
    protected ArrayList<User> GetUserDetailsCollectionFromResultSet(ResultSet rs) throws SQLException, ParseException{
       ArrayList<User> users = new ArrayList<>();
        while (rs.next())
            users.add(GetUserDetailsFromResultSet(rs));
        return users;
    }
}
