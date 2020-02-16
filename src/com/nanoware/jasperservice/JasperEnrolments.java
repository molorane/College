/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.jasperservice;

import com.nanoware.dao.impl.DBConnection;
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
public class JasperEnrolments extends Jasper{
    
    public void StudentTermEnrolment(long personId, int termId){
        try {
            Connection conn = DBConnection.getConnection();
            JasperDesign jd = GetDesign("/JasperReports/StudentTermEnrolment.jrxml");
            HashMap params = new HashMap();
            params.put("personId", personId);
            params.put("termId", termId);
            params.put("imgURL",GetImageLogo());
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, params, conn);
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setTitle("Student Term Enrolment");
            jv.setVisible(true);
         } catch (JRException ex) {
             JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
         }
    }
    public void GetStudentProofOfRegistration(long personId,String courseCode, String term){
        try {
            Connection conn = DBConnection.getConnection();
            JasperDesign jd = GetDesign("/JasperReports/GetStudentProofOfRegistration.jrxml");
            HashMap params = new HashMap();
            params.put("personId", personId);
            params.put("courseCode", courseCode);
            params.put("term", term);
            params.put("imgURL", GetImageLogo());
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, params, conn);
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setTitle("Proof of registration");
            jv.setVisible(true);
         } catch (JRException ex) {
             JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
         }
    }
}
