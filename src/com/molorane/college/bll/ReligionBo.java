/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll;

import com.molorane.college.model.Religion;
import java.util.ArrayList;

/**
 * @author Mothusi Molorane
 */
public interface ReligionBo {
    public abstract int AddReligion(Religion religion);
    public abstract int EditReligion(Religion religion);
    public abstract int RemoveReligion(int religionId);    
    public abstract Religion GetReligion(int religionId);    
    public abstract ArrayList<Religion> GetAllReligions();
}
