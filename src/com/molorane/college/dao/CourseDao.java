/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.dao;

import com.molorane.college.model.Course;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class CourseDao {
    
    public abstract int AddCourse(Course course);
    public abstract int EditCourse(Course course);
    public abstract int RemoveCourse(String courseCode);    
    public abstract Course GetCourse(String courseCode);    
    public abstract ArrayList<Course> GetAllCourses();
    public abstract ArrayList<String> GetAllDepartments();
    public abstract ArrayList<Course> GetAllDepartmentCourses(String department);
    
    // COURSE CONVERSION METHODS
    protected Course GetCourseDetailsFromResultSet(ResultSet rs) throws SQLException{
        Course course = new Course();
        course.setCourse(rs.getString("courseCode"), 
                        rs.getString("course"), 
                        rs.getString("level"), 
                        rs.getString("department"));
        return course;
    }
    
    protected ArrayList<Course> GetCourseDetailsCollectionFromResultSet(ResultSet rs) throws SQLException{
       ArrayList<Course> courses = new ArrayList<>();
        while (rs.next())
            courses.add(GetCourseDetailsFromResultSet(rs));
        return courses;
    }
}
