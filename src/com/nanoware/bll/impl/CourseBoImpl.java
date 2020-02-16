/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll.impl;

import com.nanoware.bll.CourseBo;
import com.nanoware.dao.CourseDao;
import com.nanoware.dao.impl.CourseDaoImpl;
import com.nanoware.model.Course;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author Mothusi Molorane
 */
public class CourseBoImpl extends CourseBo{
    
    private final CourseDao dao;

    public CourseBoImpl() {
        this.dao = new CourseDaoImpl();
    }

    @Override
    public int AddCourse(String courseCode, String course, String level, String department) {
        Course obj = new Course();
        obj.setCourseCode(courseCode);
        obj.setCourse(course);
        obj.setLevel(level);
        obj.setDepartment(department);
        return dao.AddCourse(obj);
    }

    @Override
    public int EditCourse(String courseCode, String course, String level, String department) {
        Course obj = new Course();
        obj.setCourseCode(courseCode);
        obj.setCourse(course);
        obj.setLevel(level);
        obj.setDepartment(department);
        return dao.EditCourse(obj);
    }

    @Override
    public int RemoveCourse(String courseCode) {
        return dao.RemoveCourse(courseCode);
    }

    @Override
    public Course GetCourse(String courseCode) {
        return dao.GetCourse(courseCode);
    }

    @Override
    public ArrayList<Course> GetAllCourses() {
        return dao.GetAllCourses();
    }  

    @Override
    public ArrayList<String> GetAllDepartments() {
        return dao.GetAllDepartments();
    }
    
    public void fillComboBoxDepartment(JComboBox con){
        con.removeAllItems();
        GetAllDepartments().forEach((department) -> {
            con.addItem(department);
        });
    }

    @Override
    public ArrayList<Course> GetAllDepartmentCourses(String department) {
        return dao.GetAllDepartmentCourses(department);
    }
}
