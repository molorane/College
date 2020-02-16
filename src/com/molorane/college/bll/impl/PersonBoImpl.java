/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll.impl;

import com.molorane.college.bll.PersonBo;
import com.molorane.college.dao.PersonDao;
import com.molorane.college.dao.impl.PersonDaoImpl;
import com.molorane.college.model.Person;
import com.molorane.college.model.Term;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author Mothusi Molorane
 */
public class PersonBoImpl implements PersonBo{
    
    private final PersonDao dao;

    public PersonBoImpl() {
        this.dao = new PersonDaoImpl();
    }

    @Override
    public int AddPerson(Person person){
        return dao.AddPerson(person);
    }

    @Override
    public int EditPerson(Person person){
        return dao.EditPerson(person);
    }

    @Override
    public int RemovePerson(long personId){
        return dao.RemovePerson(personId);
    }

    @Override
    public Person GetPerson(long personId){
        return dao.GetPerson(personId);
    }

    @Override
    public Person GetPersonByIdNo(String idno){
        return dao.GetPersonByIdNo(idno);
    }

    @Override
    public Person GetPersonByPassport(String passport){
        return dao.GetPersonByPassport(passport);
    }

    @Override
    public ArrayList<Person> GetPersonsByFirstName(String firstName){
        return dao.GetPersonsByFirstName(firstName);
    }

    @Override
    public ArrayList<Person> GetPersonsByLastName(String lastName){
        return dao.GetPersonsByLastName(lastName);
    }

    @Override
    public long GetNewStudentNo(){
        return dao.GetNewStudentNo();
    }

    @Override
    public ArrayList<Person> searchPersons(String search){
        return dao.searchPersons(search);
    }

    @Override
    public List<Term> GetPersonTerms(long personId) {
        return dao.GetPersonTerms(personId);
    }
    
    public void fillComboBoxPersonTerm(JComboBox con,long personId){
        con.removeAllItems();
        GetPersonTerms(personId).forEach((bean) -> {
            con.addItem(bean);
        });
    }

    @Override
    public int UploadCollegeStudents(List<HashMap<String, Object>> students) {
        return dao.UploadCollegeStudents(students);
    }

    @Override
    public int AddCollegeStudent(Person person) {
        return dao.AddCollegeStudent(person);
    }
}
