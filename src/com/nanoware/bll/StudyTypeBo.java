/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll;

import com.nanoware.model.StudyType;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public interface StudyTypeBo {
    public abstract int AddStudyType(StudyType studytype);
    public abstract int EditStudyType(StudyType studytype);
    public abstract int RemoveStudyType(int studytypeId);    
    public abstract StudyType GetStudyType(int studytypeId);    
    public abstract ArrayList<StudyType> GetAllStudyTypes();
}
