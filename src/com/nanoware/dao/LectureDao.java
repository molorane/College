/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao;

import com.nanoware.model.Campus;
import com.nanoware.model.Lecture;
import com.nanoware.model.Module;
import com.nanoware.model.Term;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class LectureDao {
    public abstract ArrayList<Campus> GetLectureCampuses(int lectureId);
    public abstract ArrayList<Term> GetLectureTermsInCampus(int lectureId, int campusCode);
    public abstract ArrayList<Module> GetLectureModulesForTerm(int lectureId,int campusCode, int termId);
    public abstract List<HashMap<String, Object>> GetLectureStudentsInTerm(int lectureId,int campusCode, int termId, String moduleCode);
    
    public abstract int AddLectureModule(Lecture lecture);
    public abstract int EditLectureModule(Lecture lecture);
    public abstract int RemoveLectureModule(int lmId);    
    public abstract Lecture GetLectureModule(int lmId);
    public abstract ArrayList<Lecture> GetLectureModules(int lectureId);
    
    // Lecture CONVERSION METHODS
    protected Lecture GetLectureModuleDetailsFromResultSet(ResultSet rs) throws SQLException{
        Lecture lecture = new Lecture();
        lecture.setLecture(rs.getInt("lmId"), 
                        rs.getInt("lectureId"),
                        rs.getInt("campusCode"),
                        rs.getInt("termId"), 
                        rs.getString("moduleCode"),
                        rs.getString("campusABR"),
                        rs.getString("term"),
                        rs.getString("module"));
        return lecture;
    }
    
    protected ArrayList<Lecture> GetLectureModuleDetailsCollectionFromResultSet(ResultSet rs) throws SQLException{
       ArrayList<Lecture> lectures = new ArrayList<>();
        while (rs.next())
            lectures.add(GetLectureModuleDetailsFromResultSet(rs));
        return lectures;
    }
}
