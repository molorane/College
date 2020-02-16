/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao.impl;

import com.nanoware.bll.Functions;
import com.nanoware.dao.CourseDao;
import com.nanoware.model.Course;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public class CourseDaoImpl extends CourseDao {

    @Override
    public int AddCourse(Course course) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainCourse(?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setString(1, course.getCourseCode());
            cs.setString(2, course.getCourse());
            cs.setString(3, course.getLevel());
            cs.setString(4, course.getDepartment());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("MaintainCourse error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int EditCourse(Course course) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL MaintainCourse(?,?,?,?)";
            cs = conn.prepareCall(sql);
            cs.setString(1, course.getCourseCode());
            cs.setString(2, course.getCourse());
            cs.setString(3, course.getLevel());
            cs.setString(4, course.getDepartment());
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("MaintainCourse error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public int RemoveCourse(String courseCode) {
        try {
            Connection conn = DBConnection.getConnection();
            CallableStatement cs = null;
            String sql = "CALL RemoveCourse(?)";
            cs = conn.prepareCall(sql);
            cs.setString(1, courseCode);
            int count = cs.executeUpdate();
            conn.close();
            return count;
        } catch (SQLException e) {
            Functions.errorMessage("RemoveCourse error: "+ e.getLocalizedMessage());
        }        
        return 0;
    }

    @Override
    public Course GetCourse(String courseCode) {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetCourse(?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, courseCode);
            rs = pst.executeQuery();
            if (rs.next()) {
                return GetCourseDetailsFromResultSet(rs);
            }
        } catch (SQLException e) {
            Functions.errorMessage("getCourse error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Course> GetAllCourses() {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetAllCourses()";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            return GetCourseDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getAllCourses error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<String> GetAllDepartments() {
         try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetAllDepartments()";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            ArrayList<String> departments = new ArrayList<>();
            while (rs.next())
                departments.add(rs.getString("department"));
            return departments;
        } catch (SQLException e) {
            Functions.errorMessage("getAllDepartments error: "+ e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Course> GetAllDepartmentCourses(String department){
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = null;
            PreparedStatement pst = null;
            String sql = "CALL GetAllDepartmentCourses(?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, department);
            rs = pst.executeQuery();
            return GetCourseDetailsCollectionFromResultSet(rs);
        } catch (SQLException e) {
            Functions.errorMessage("getAllDepartmentCourses error: "+ e.getLocalizedMessage());
        }
        return null;
    }
}
