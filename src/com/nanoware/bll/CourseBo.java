/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll;

import com.nanoware.model.Course;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class CourseBo {
    public abstract int AddCourse(String courseCode, String course,String level,String department);
    public abstract int EditCourse(String courseCode, String course,String level,String department);
    public abstract int RemoveCourse(String courseCode);    
    public abstract Course GetCourse(String courseCode);    
    public abstract ArrayList<Course> GetAllCourses();
    public abstract ArrayList<String> GetAllDepartments();
    public abstract ArrayList<Course> GetAllDepartmentCourses(String department);
}
