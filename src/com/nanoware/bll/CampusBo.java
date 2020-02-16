/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll;

import com.nanoware.model.Campus;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public interface CampusBo {
    
    public abstract int AddCampus(Campus campus);
    public abstract int EditCampus(Campus campus);
    public abstract int RemoveCampus(int campusCode);    
    public abstract Campus GetCampus(int campusCode);    
    public abstract ArrayList<Campus> GetAllCampuses();

}
