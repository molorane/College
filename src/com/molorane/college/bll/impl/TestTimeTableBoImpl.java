/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll.impl;

import com.molorane.college.bll.TestTimeTableBo;
import com.molorane.college.dao.TestTimeTableDao;
import com.molorane.college.dao.impl.TestTimeTableDaoImpl;
import com.molorane.college.model.TestTimeTable;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public class TestTimeTableBoImpl implements TestTimeTableBo{
    
    private final TestTimeTableDao dao;

    public TestTimeTableBoImpl() {
        this.dao = new TestTimeTableDaoImpl();
    }

    @Override
    public int AddTestTimeTable(TestTimeTable testTimeTable) {
        return dao.AddTestTimeTable(testTimeTable);
    }

    @Override
    public int EditTestTimeTable(TestTimeTable testTimeTable) {
        return dao.EditTestTimeTable(testTimeTable);
    }

    @Override
    public int RemoveTestTimeTable(int ttId) {
        return dao.RemoveTestTimeTable(ttId);
    }

    @Override
    public TestTimeTable GetTestTimeTable(int ttId) {
        return dao.GetTestTimeTable(ttId);
    }

    @Override
    public ArrayList<TestTimeTable> GetCampusModuleTestTimeTable(int campusCode, String moduleCode, int termId) {
        return dao.GetCampusModuleTestTimeTable(campusCode, moduleCode, termId);
    }
    
}
