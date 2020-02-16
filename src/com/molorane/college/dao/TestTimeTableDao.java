/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao;

import com.molorane.college.model.TestTimeTable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class TestTimeTableDao {
    
    public abstract int AddTestTimeTable(TestTimeTable testTimeTable);
    public abstract int EditTestTimeTable(TestTimeTable testTimeTable);
    public abstract int RemoveTestTimeTable(int ttId);    
    public abstract TestTimeTable GetTestTimeTable(int ttId);
    public abstract ArrayList<TestTimeTable> GetCampusModuleTestTimeTable(int campusCode,String moduleCode,int termId);
    
    // TestTimeTable CONVERSION METHODS
    protected TestTimeTable GetTestTimeTableDetailsFromResultSet(ResultSet rs) throws SQLException{
        TestTimeTable timetable = new TestTimeTable();
        timetable.setTestTimeTable(rs.getInt("ttId"), 
                        rs.getInt("campusCode"),
                        rs.getString("moduleCode"),
                        rs.getInt("termId"), 
                        rs.getInt("gradeId"), 
                        rs.getString("testDate"), 
                        rs.getString("startTime"),
                        rs.getString("duration"),
                        rs.getString("venue"),
                        rs.getString("campusABR"),
                        rs.getString("module"),   
                        rs.getString("term"),
                        rs.getString("gradecategory"));
        return timetable;
    }
    
    protected ArrayList<TestTimeTable> GetTestTimeTableDetailsCollectionFromResultSet(ResultSet rs) throws SQLException{
       ArrayList<TestTimeTable> timetable = new ArrayList<>();
        while (rs.next())
            timetable.add(GetTestTimeTableDetailsFromResultSet(rs));
        return timetable;
    }
}
