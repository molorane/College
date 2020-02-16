/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao.impl;

import com.nanoware.bll.Functions;
import com.nanoware.dao.PersonDao;
import com.nanoware.model.Person;
import com.nanoware.model.Term;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Mothusi Molorane
 */
public class PersonDaoImpl extends PersonDao{
    
    private TermDaoImpl term = new TermDaoImpl();

    @Override
    public int AddPerson(Person person) throws SQLException {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainPerson(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setLong(1, person.getPersonId());
            cs.setString(2, person.getIdno());
            cs.setString(3, person.getPassport());
            cs.setString(4, person.getFirstName());
            cs.setString(5, person.getLastName());
            cs.setString(6, person.getOtherName());
            cs.setInt(7, person.getGenderId());
            cs.setInt(8, person.getTitleId());
            cs.setString(9, person.getDob());
            cs.setInt(10, person.getRaceId());
            cs.setInt(11, person.getNationalityId());
            cs.setInt(12, person.getLanguageId());
            cs.setInt(13, person.getReligionId());
            cs.setString(14, person.getAddress());
            cs.setString(15, person.getPostalCode());
            cs.setString(16, person.getTelephone());
            cs.setString(17, person.getFax());
            cs.setString(18, person.getCellphone());
            cs.setString(19, person.getEmail());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("MaintainPerson error: "+ e.getLocalizedMessage());
        }
        return 0;
    }

    @Override
    public int EditPerson(Person person) throws SQLException {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainPerson(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setLong(1, person.getPersonId());
            cs.setString(2, person.getIdno());
            cs.setString(3, person.getPassport());
            cs.setString(4, person.getFirstName());
            cs.setString(5, person.getLastName());
            cs.setString(6, person.getOtherName());
            cs.setInt(7, person.getGenderId());
            cs.setInt(8, person.getTitleId());
            cs.setString(9, person.getDob());
            cs.setInt(10, person.getRaceId());
            cs.setInt(11, person.getNationalityId());
            cs.setInt(12, person.getLanguageId());
            cs.setInt(13, person.getReligionId());
            cs.setString(14, person.getAddress());
            cs.setString(15, person.getPostalCode());
            cs.setString(16, person.getTelephone());
            cs.setString(17, person.getFax());
            cs.setString(18, person.getCellphone());
            cs.setString(19, person.getEmail());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("MaintainPerson error: "+ e.getLocalizedMessage());
        }
        return 0;
    }

    @Override
    public int RemovePerson(long personId) throws SQLException {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL RemovePerson(?)";
            cs = conn.prepareCall(sql);
            cs.setLong(1, personId);
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("RemovePerson error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public Person GetPerson(long personId) throws SQLException {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetPerson(?)";
            pst = conn.prepareStatement(sql);
            pst.setLong(1, personId);
            rs = pst.executeQuery();
            if (rs.next()) {
                return GetPersonDetailsFromResultSet(rs);
            }
        } catch (SQLException e) {
            Functions.errorMessage("getPerson error: "+ e.getLocalizedMessage());
        } catch (ParseException ex) {
            Logger.getLogger(PersonDaoImpl.class.getName()).log(Level.SEVERE, null, "getPerson error: "+ex.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public Person GetPersonByIdNo(String idno) throws SQLException {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetPersonByIdNo(?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, idno);
            rs = pst.executeQuery();
            if (rs.next()) {
                return GetPersonDetailsFromResultSet(rs);
            }
        } catch (SQLException e) {
            Functions.errorMessage("getPersonByIdNo error: "+ e.getLocalizedMessage());
        } catch (ParseException ex) {
            Logger.getLogger(PersonDaoImpl.class.getName()).log(Level.SEVERE, null, "getPerson error: "+ex.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public Person GetPersonByPassport(String passport) throws SQLException {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetPersonByPassport(?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, passport);
            rs = pst.executeQuery();
            if (rs.next()) {
                return GetPersonDetailsFromResultSet(rs);
            }
        } catch (SQLException e) {
            Functions.errorMessage("getPersonByPassport error: "+ e.getLocalizedMessage());
        } catch (ParseException ex) {
            Logger.getLogger(PersonDaoImpl.class.getName()).log(Level.SEVERE, null, "getPerson error: "+ex.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Person> GetPersonsByFirstName(String firstName) throws SQLException {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetPersonsByFirstName(?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, firstName);
            rs = pst.executeQuery();
            return GetPersonDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getPersonsByFirstName error: "+ e.getLocalizedMessage());
        } catch (ParseException ex) {
            Logger.getLogger(PersonDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Person> GetPersonsByLastName(String lastName) throws SQLException {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetPersonsByLastName(?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, lastName);
            rs = pst.executeQuery();
            return GetPersonDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getPersonsByLastName error: "+ e.getLocalizedMessage());
        } catch (ParseException ex) {
            Logger.getLogger(PersonDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public long GetNewStudentNo() throws SQLException {    
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetNewStudentNO()";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                return Long.parseLong(rs.getString("AUTO_INCREMENT"));
            }
        } catch (SQLException e) {
            Functions.errorMessage("getPersonByPassport error: "+ e.getLocalizedMessage());
        }
        return 0;
    }

    @Override
    public ArrayList<Person> searchPersons(String search) throws SQLException {    
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL searchPersons(?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, search);
            rs = pst.executeQuery();
            return GetPersonDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("searchPersons error: "+ e.getLocalizedMessage());
        } catch (ParseException ex) {
            Logger.getLogger(PersonDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }    

    @Override
    public List<Term> GetPersonTerms(long personId) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetPersonTerms(?)";
            pst = conn.prepareStatement(sql);
            pst.setLong(1, personId);
            rs = pst.executeQuery();
            return term.GetTermDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getPersonsByLastName error: "+ e.getLocalizedMessage());
        }
        return null;
    }
}
