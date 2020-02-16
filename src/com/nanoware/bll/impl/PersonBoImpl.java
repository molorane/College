/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll.impl;

import com.nanoware.bll.PersonBo;
import com.nanoware.dao.PersonDao;
import com.nanoware.dao.impl.PersonDaoImpl;
import com.nanoware.model.Person;
import com.nanoware.model.Term;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public int AddPerson(Person person) throws SQLException {
        return dao.AddPerson(person);
    }

    @Override
    public int EditPerson(Person person) throws SQLException {
        return dao.EditPerson(person);
    }

    @Override
    public int RemovePerson(long personId) throws SQLException {
        return dao.RemovePerson(personId);
    }

    @Override
    public Person GetPerson(long personId) throws SQLException {
        return dao.GetPerson(personId);
    }

    @Override
    public Person GetPersonByIdNo(String idno) throws SQLException {
        return dao.GetPersonByIdNo(idno);
    }

    @Override
    public Person GetPersonByPassport(String passport) throws SQLException {
        return dao.GetPersonByPassport(passport);
    }

    @Override
    public ArrayList<Person> GetPersonsByFirstName(String firstName) throws SQLException {
        return dao.GetPersonsByFirstName(firstName);
    }

    @Override
    public ArrayList<Person> GetPersonsByLastName(String lastName) throws SQLException {
        return dao.GetPersonsByLastName(lastName);
    }

    @Override
    public long GetNewStudentNo() throws SQLException {
        return dao.GetNewStudentNo();
    }

    @Override
    public ArrayList<Person> searchPersons(String search) throws SQLException {
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
}
