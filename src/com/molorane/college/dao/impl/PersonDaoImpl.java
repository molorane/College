/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao.impl;

import com.molorane.college.db.DBConnection;
import com.molorane.college.custom.Functions;
import com.molorane.college.dao.PersonDao;
import com.molorane.college.model.Person;
import com.molorane.college.model.Term;
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
 * @author Mothusi Molorane
 */
public class PersonDaoImpl extends PersonDao{
    
    private TermDaoImpl term = new TermDaoImpl();

    @Override
    public int AddPerson(Person person){
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
    public int EditPerson(Person person){
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
    public int RemovePerson(long personId){
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
    public Person GetPerson(long personId){
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
    public Person GetPersonByIdNo(String idno){
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
    public Person GetPersonByPassport(String passport){
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
    public ArrayList<Person> GetPersonsByFirstName(String firstName){
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
    public ArrayList<Person> GetPersonsByLastName(String lastName){
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
    public long GetNewStudentNo(){    
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
    public ArrayList<Person> searchPersons(String search){    
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

    @Override
    public int UploadCollegeStudents(List<HashMap<String, Object>> students) {
        try{            
            String sql = "CALL UploadCollegeStudents(?,?,?,?,?,?,?,?)";
            Connection conn = DBConnection.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pst = conn.prepareStatement(sql);  
            
            for(HashMap<String, Object> student:students){
                pst.setInt(1, Integer.parseInt(student.get("studentno").toString()));
                pst.setString(2, student.get("idno").toString());
                pst.setString(3, student.get("firstName").toString());
                pst.setString(4, student.get("lastName").toString());
                pst.setString(5, student.get("otherName").toString());
                pst.setInt(6, Integer.parseInt(student.get("genderId").toString()));
                pst.setInt(7, Integer.parseInt(student.get("raceId").toString()));
                pst.setInt(8, Integer.parseInt(student.get("langaugeId").toString()));
                pst.addBatch();
            }
            int[] rows = pst.executeBatch();
            conn.commit();
            conn.close();
            return rows.length;
        } catch (SQLException e) {
            Functions.errorMessage("UploadCollegeStudents error: "+ e.getLocalizedMessage());
        }
        return -1;  
    }

    @Override
    public int AddCollegeStudent(Person person) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL AddCollegeStudent(?,?,?,?,?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setLong(1, person.getPersonId());
            cs.setString(2, person.getIdno());
            cs.setString(3, person.getFirstName());
            cs.setString(4, person.getLastName());
            cs.setString(5, person.getOtherName());
            cs.setInt(6, person.getGenderId());
            cs.setInt(7, person.getRaceId());
            cs.setInt(8, person.getLanguageId());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("AddCollegeStudent error: "+ e.getLocalizedMessage());
        }
        return 0;
    }
}
