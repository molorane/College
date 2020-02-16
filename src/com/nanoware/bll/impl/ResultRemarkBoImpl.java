/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll.impl;

import com.nanoware.bll.ResultRemarkBo;
import com.nanoware.dao.ResultRemarkDao;
import com.nanoware.dao.impl.ResultRemarkDaoImpl;
import com.nanoware.model.ResultRemark;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author Mothusi Molorane
 */
public class ResultRemarkBoImpl implements ResultRemarkBo{
    
    private final ResultRemarkDao dao;

    public ResultRemarkBoImpl() {
        this.dao = new ResultRemarkDaoImpl();
    }

    @Override
    public int AddResultRemark(ResultRemark resultremark) {
        return dao.AddResultRemark(resultremark);
    }

    @Override
    public int EditResultRemark(ResultRemark resultremark) {
        return dao.EditResultRemark(resultremark);
    }

    @Override
    public int RemoveResultRemark(String resultremarkId) {
        return dao.RemoveResultRemark(resultremarkId);
    }

    @Override
    public ResultRemark GetResultRemark(String resultremarkId) {
        return dao.GetResultRemark(resultremarkId);
    }

    @Override
    public ArrayList<ResultRemark> GetAllResultRemarks() {
        return dao.GetAllResultRemarks();
    }
    
    public void fillComboBoxResultRemark(JComboBox con){
        con.removeAllItems();
        GetAllResultRemarks().forEach((bean) -> {
            con.addItem(bean);
        });
    }
}
