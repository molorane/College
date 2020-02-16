/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll;

import com.nanoware.model.Gender;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public interface GenderBo {
    public abstract int AddGender(Gender gender);
    public abstract int EditGender(Gender gender);
    public abstract int RemoveGender(int genderId);    
    public abstract Gender GetGender(int genderId);    
    public abstract ArrayList<Gender> GetAllGenders();
}
