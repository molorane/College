/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll;

import com.nanoware.model.ResultRemark;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public interface ResultRemarkBo {
    public abstract int AddResultRemark(ResultRemark resultremark);
    public abstract int EditResultRemark(ResultRemark resultremark);
    public abstract int RemoveResultRemark(String resultremarkId);    
    public abstract ResultRemark GetResultRemark(String resultremarkId);    
    public abstract ArrayList<ResultRemark> GetAllResultRemarks();
}
