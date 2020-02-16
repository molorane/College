/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll;

import com.nanoware.model.Person;
import com.nanoware.model.Term;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mothusi Molorane
 */
public interface PersonBo {
    public abstract int AddPerson(Person person) throws SQLException;
    public abstract int EditPerson(Person person) throws SQLException;
    public abstract int RemovePerson(long personId) throws SQLException;    
    public abstract Person GetPerson(long personId) throws SQLException;    
    public abstract Person GetPersonByIdNo(String idno) throws SQLException;    
    public abstract Person GetPersonByPassport(String passport) throws SQLException;   
    public abstract ArrayList<Person> GetPersonsByFirstName(String firstName) throws SQLException;
    public abstract ArrayList<Person> GetPersonsByLastName(String lastName) throws SQLException;
    public abstract ArrayList<Person> searchPersons(String search) throws SQLException;
    public abstract long GetNewStudentNo() throws SQLException; ;  
    public abstract List<Term> GetPersonTerms(long personId); 
}
