/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll;

import com.nanoware.model.Institution;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public interface InstitutionBo {
    public abstract int AddInstitution(String institution);
    public abstract int EditInstitution(Institution institution);
    public abstract int RemoveInstitution(int institutionId);    
    public abstract Institution GetInstitution(int institutionId);    
    public abstract ArrayList<Institution> GetAllInstitutions();
}
