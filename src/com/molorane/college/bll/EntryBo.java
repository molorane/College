/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mothusi Molorane
 */
public interface EntryBo {
    public abstract List<HashMap<String,Object>> GetMarkEntry(int termId,int campusCode);
    public abstract List<HashMap<String,Object>> GetExamEntry(int termId,int campusCode);
}
