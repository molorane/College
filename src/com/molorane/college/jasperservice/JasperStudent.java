/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.jasperservice;

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
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Mothusi Molorane
 */
public class JasperStudent extends Jasper{
    
    public void StudentModuleGrades(int personId, int termId, String moduleCode){
        try {
            Connection conn = DBConnection.getConnection();
            JasperDesign jd = GetDesign("StudentModuleGrades.jrxml");
            HashMap params = new HashMap();
            params.put("personId", personId);
            params.put("termId", termId);
            params.put("moduleCode", moduleCode);
            params.put("logo", GetImageLogo());
            params.put("footer", GetImageFooter());
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, params, conn);
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setTitle("Student Module Grades");
            jv.setVisible(true);
         } catch (JRException ex) {
             JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
         }
    }
    
}
