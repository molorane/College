/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll.impl;

import com.nanoware.bll.Functions;
import com.nanoware.bll.TermBo;
import com.nanoware.dao.TermDao;
import com.nanoware.dao.impl.TermDaoImpl;
import com.nanoware.model.Term;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author Mothusi Molorane
 */
public class TermBoImpl extends TermBo{
    
    private final TermDao dao;

    public TermBoImpl() {
        this.dao = new TermDaoImpl();
    }

    @Override
    public int AddTerm(Term term) {
        return dao.AddTerm(term);
    }

    @Override
    public int EditTerm(Term term) {
        return dao.EditTerm(term);
    }

    @Override
    public int RemoveTerm(int termId) {
        return dao.RemoveTerm(termId);
    }

    @Override
    public Term GetTerm(int termId) {
        return dao.GetTerm(termId);
    }

    @Override
    public ArrayList<Term> GetAllTerms() {
        return dao.GetAllTerms();
    }
    
    public void fillComboBoxTerm(JComboBox con){
        con.removeAllItems();
        GetAllTerms().forEach((bean) -> {
            con.addItem(bean);
        });
    }
    
    public void fillComboBoxTermYears(JComboBox con){
        con.removeAllItems();
        GetAllTermYears().forEach((bean) -> {
            con.addItem(bean.get("Year"));
        });
    }
    
    public void fillComboBoxDepts(JComboBox con){
        con.removeAllItems();
        Functions.getDepts().forEach((bean) -> {
            con.addItem(bean);
        });
    }
    
    public void fillComboBoxByDepartment(JComboBox con,String department){
        con.removeAllItems();
        GetTermsByDepartment(department).forEach((bean) -> {
            con.addItem(bean);
        });
    }
    

    @Override
    public ArrayList<Term> GetTermsByYear(int year) {
        return dao.GetTermsByYear(year);
    }

    @Override
    public ArrayList<Term> GetTermsByDepartment(String dept) {
        return dao.GetTermsByDepartment(dept);
    }

    @Override
    public ArrayList<Term> GetTermsByYearAndDepartment(int year, String dept) {
        return dao.GetTermsByYearAndDepartment(year, dept);
    }

    @Override
    public List<HashMap<String, Object>> GetAllTermYears() {
        return dao.GetAllTermYears();
    }
}
