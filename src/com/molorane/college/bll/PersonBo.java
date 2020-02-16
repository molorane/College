/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll;

import com.molorane.college.model.Person;
import com.molorane.college.model.Term;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mothusi Molorane
 */
public interface PersonBo {
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
}
