/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll;

import com.molorane.college.model.GradeDateline;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public interface GradeDatelineBo {
    public abstract int AddGradeDateline(GradeDateline gradeDateline);
    public abstract int EditGradeDateline(GradeDateline gradeDateline);
    public abstract int RemoveGradeDateline(int termId);    
    public abstract GradeDateline GetGradeDateline(int termId); 
    public abstract boolean isSubmissionBeforeDateline(int termId);
    public abstract ArrayList<GradeDateline> GetAllGradeDatelines();
}
