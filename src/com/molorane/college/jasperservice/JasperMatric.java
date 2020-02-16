/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.jasperservice;

import com.molorane.college.bll.impl.MatricBoImpl;
import com.molorane.college.db.DBConnection;
import java.sql.Connection;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Mothusi Molorane
 */
public class JasperMatric extends Jasper{
    
    private final MatricBoImpl matricBoImpl = new MatricBoImpl();
   
    public JasperPrint GetStudentReport(String idno,int year){
        try {
            Connection conn = DBConnection.getConnection();
            JasperDesign jd = GetDesign("GetStudentMatricReport.jrxml");
            HashMap params = new HashMap();
            params.put("idno", idno);
            params.put("year", year);
            params.put("logo", GetImageLogo());
            params.put("signature", GetImage("sign.jpg"));
            params.put("footer", GetImageFooter());
            JasperReport jr = JasperCompileManager.compileReport(jd);
            jr.setWhenNoDataType(WhenNoDataTypeEnum.NO_PAGES);
            JasperPrint jp = JasperFillManager.fillReport(jr, params, conn);
            return jp;
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
        return null;
    }
    
    public void PrintStudentReport(String idno,int year){
        JasperPrint jp = GetStudentReport(idno,year);
        JasperViewer jv = new JasperViewer(jp, false);
        jv.setTitle("Print Student Report");
        jv.setVisible(true);
    }
    
    public void GetAnalysisByTerm(String term, int year){
        try {
            Connection conn = DBConnection.getConnection();
            JasperDesign jd = GetDesign("GetAnalysisByTerm.jrxml");
            HashMap params = new HashMap();
            params.put("term", term);
            params.put("year", year);
            params.put("logo", GetImageLogo());
            params.put("footer", GetImageFooter());
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, params, conn);
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setTitle("Term Analysis");
            jv.setVisible(true);
         } catch (JRException ex) {
             JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
         }
    }
}
