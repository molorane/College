/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll.impl;

import com.nanoware.bll.UserBo;
import com.nanoware.dao.UserDao;
import com.nanoware.dao.impl.UserDaoImpl;
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
public class UserBoImpl implements UserBo{
    
    private final UserDao dao;

    public UserBoImpl() {
        this.dao = new UserDaoImpl();
    }

    @Override
    public int AddUser(User user) throws SQLException{
        return dao.AddUser(user);
    }

    @Override
    public int EditUser(User user) throws SQLException {
        return dao.EditUser(user);
    }

    @Override
    public int RemoveUser(String userName) throws SQLException {
        return dao.RemoveUser(userName);
    }

    @Override
    public User GetUser(String userName) throws SQLException {
        return dao.GetUser(userName);
    }

    @Override
    public User GetUserByPersonId(int personId) throws SQLException {
        return dao.GetUserByPersonId(personId);
    }

    @Override
    public User GetUserByUsername(String userName) throws SQLException {
        return dao.GetUserByUsername(userName);
    }

    @Override
    public ArrayList<User> GetUsersByRole(int roleId) throws SQLException {
        return dao.GetUsersByRole(roleId);
    }

    @Override
    public ArrayList<User> GetAllUsers() throws SQLException {
        return dao.GetAllUsers();
    }

    @Override
    public int setPasswordQuestion(String userName, String passwordQuestion, String passwordAnswer) throws SQLException {
        return dao.SetPasswordQuestion(userName, passwordQuestion, passwordAnswer);
    }

    @Override
    public User GetPasswordQuestion(String userName, String passwordAnswer) throws SQLException {
        return dao.GetPasswordQuestion(userName, passwordAnswer);
    }

    @Override
    public User validateUser(Login login) throws Exception {
        return dao.ValidateUser(login);
    }

    @Override
    public boolean IsAccountActive(String username) throws Exception {
        return dao.IsAccountActive(username);
    }

    @Override
    public boolean IsAccountLocked(String username) throws Exception {
        return dao.IsAccountLocked(username);
    }

    @Override
    public List<HashMap<String, Object>> AccountStatus(String username) throws Exception {
        return dao.AccountStatus(username);
    }
    
}