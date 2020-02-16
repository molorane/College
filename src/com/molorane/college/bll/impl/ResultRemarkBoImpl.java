/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.bll.impl;

import com.molorane.college.bll.ResultRemarkBo;
import com.molorane.college.dao.ResultRemarkDao;
import com.molorane.college.dao.impl.ResultRemarkDaoImpl;
import com.molorane.college.model.ResultRemark;
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
        ResultRemark rm = new ResultRemark();
        rm.setResultRemark("-1", "SELECT");
        con.addItem(rm);
        GetAllResultRemarks().forEach((bean) -> {
            con.addItem(bean);
        });
    }
}
