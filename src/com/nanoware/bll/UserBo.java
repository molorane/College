/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll;

import com.nanoware.model.Login;
import com.nanoware.model.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mothusi Molorane
 */
public interface UserBo {
    public abstract int AddUser(User user) throws SQLException;
    public abstract int EditUser(User user) throws SQLException;
    public abstract int RemoveUser(String userName) throws SQLException;    
    public abstract User GetUser(String userName) throws SQLException;
    public abstract User GetUserByPersonId(int personId) throws SQLException;
    public abstract User GetUserByUsername(String userName) throws SQLException;
    public abstract ArrayList<User> GetUsersByRole(int roleId) throws SQLException;
    public abstract ArrayList<User> GetAllUsers() throws SQLException;
    public abstract int setPasswordQuestion(String userName,String passwordQuestion,String passwordAnswer) throws SQLException;
    public abstract User GetPasswordQuestion(String userName,String passwordAnswer) throws SQLException;
    public abstract User validateUser(Login login) throws Exception;
    public abstract boolean IsAccountActive(String username) throws Exception;
    public abstract boolean IsAccountLocked(String username) throws Exception;
    public abstract List<HashMap<String, Object>> AccountStatus(String username) throws Exception;
}
