/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll;

import com.nanoware.model.Campus;
import com.nanoware.model.Lecture;
import com.nanoware.model.Module;
import com.nanoware.model.Term;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mothusi Molorane
 */
public interface LectureBo {
    public abstract ArrayList<Campus> GetLectureCampuses(int lectureId);
    public abstract ArrayList<Term> GetLectureTermsInCampus(int lectureId, int campusCode);
    public abstract ArrayList<Module> GetLectureModulesForTerm(int lectureId,int campusCode, int termId);
    public abstract List<HashMap<String, Object>> GetLectureStudentsInTerm(int lectureId,int campusCode, int termId, String moduleCode);

    public abstract int AddLectureModule(Lecture lecture);
    public abstract int EditLectureModule(Lecture lecture);
    public abstract int RemoveLectureModule(int lmId);    
    public abstract Lecture GetLectureModule(int lmId);
    public abstract ArrayList<Lecture> GetLectureModules(int lectureId);
}
