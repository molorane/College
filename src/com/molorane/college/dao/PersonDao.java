/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao;

import com.molorane.college.model.Person;
import com.molorane.college.model.Term;
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
public abstract class PersonDao {
    
    public abstract int AddPerson(Person person);
    public abstract int EditPerson(Person person);
    public abstract int RemovePerson(long personId);    
    public abstract Person GetPerson(long personId);    
    public abstract Person GetPersonByIdNo(String idno);    
    public abstract Person GetPersonByPassport(String passport);   
    public abstract ArrayList<Person> GetPersonsByFirstName(String firstName);
    public abstract ArrayList<Person> GetPersonsByLastName(String lastName);
    public abstract ArrayList<Person> searchPersons(String search);
    public abstract long GetNewStudentNo();  
    public abstract List<Term> GetPersonTerms(long personId);
    public abstract int UploadCollegeStudents(List<HashMap<String,Object>> students);
    public abstract int AddCollegeStudent(Person person);
    
    
    // PERSON CONVERSION METHODS
    protected Person GetPersonDetailsFromResultSet(ResultSet rs) throws SQLException, ParseException{
        Person person = new Person();
        person.setPerson(rs.getInt("personId"), 
                        rs.getString("idno"), 
                        rs.getString("passport"), 
                        rs.getString("firstName"), 
                        rs.getString("lastName"), 
                        rs.getString("otherName"),  
                        rs.getInt("genderId"), 
                        rs.getInt("titleId"), 
                        rs.getString("initials"), 
                        rs.getString("dob"), 
                        rs.getInt("raceId"), 
                        rs.getInt("nationalityId"), 
                        rs.getInt("languageId"), 
                        rs.getInt("religionId"), 
                        rs.getString("address"), 
                        rs.getString("postalCode"), 
                        rs.getString("telephone"), 
                        rs.getString("fax"), 
                        rs.getString("cellphone"), 
                        rs.getString("email"), 
                        rs.getString("gender"), 
                        rs.getString("title"), 
                        rs.getString("race"), 
                        rs.getString("country"), 
                        rs.getString("language"), 
                        rs.getString("religion"));
        return person;
    }
    
    protected ArrayList<Person> GetPersonDetailsCollectionFromResultSet(ResultSet rs) throws SQLException, ParseException{
       ArrayList<Person> persons = new ArrayList<>();
        while (rs.next())
            persons.add(GetPersonDetailsFromResultSet(rs));
        return persons;
    }
}
