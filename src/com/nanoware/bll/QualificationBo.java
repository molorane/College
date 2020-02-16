/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll;

import com.nanoware.model.Qualification;
import java.util.ArrayList;

/**
 * @author Mothusi Molorane
 */
public interface QualificationBo {
    public abstract int AddQualification(Qualification qualification);
    public abstract int EditQualification(Qualification qualification);
    public abstract int RemoveQualification(int qualificationId);    
    public abstract Qualification GetQualification(int qualificationId);    
    public abstract ArrayList<Qualification> GetAllQualifications();
}
