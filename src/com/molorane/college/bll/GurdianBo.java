/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll;

import com.molorane.college.model.Gurdian;

/**
 *
 * @author Mothusi Molorane
 */
public interface GurdianBo {
    public abstract int AddGurdian(Gurdian gurdian);
    public abstract int EditGurdian(Gurdian gurdian);
    public abstract int RemoveGurdian(long personId);    
    public abstract Gurdian GetGurdian(long personId); 
}
