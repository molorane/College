/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll.impl;

import com.molorane.college.bll.PFileBo;
import com.molorane.college.dao.PFileDao;
import com.molorane.college.dao.impl.PFileDaoImpl;
import com.molorane.college.model.PFile;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public class PFileBoImpl implements PFileBo{
    
    private final PFileDao dao;

    public PFileBoImpl() {
        this.dao = new PFileDaoImpl();
    }
    
    @Override
    public int UploadFile(PFile file) {
        return dao.UploadFile(file);
    }

    @Override
    public PFile GetFile(int upId) {
        return dao.GetFile(upId);
    }

    @Override
    public int RemoveFile(int upId) {
        return dao.RemoveFile(upId);
    }

    @Override
    public ArrayList<PFile> GetFiles(long personId) {
        return dao.GetFiles(personId);
    }
}
