/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll;

import com.nanoware.model.Education;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public interface EducationBo {
    
    public abstract int AddEducation(Education education);
    public abstract int EditEducation(Education education);
    public abstract int RemoveEducation(int eduId);    
    public abstract Education GetEducation(int eduId);
    public abstract ArrayList<Education> GetPersonEducation(long personId);
}
