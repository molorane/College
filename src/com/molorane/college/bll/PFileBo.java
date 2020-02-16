/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll;

import com.molorane.college.model.PFile;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public interface PFileBo {
    public abstract int UploadFile(PFile file);
    public abstract PFile GetFile(int upId);
    public abstract int RemoveFile(int upId);
    public abstract ArrayList<PFile> GetFiles(long personId);
}
