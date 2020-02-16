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
public class JasperEnrolments extends Jasper{
    
    public void StudentTermEnrolment(long personId, int termId){
        try {
            Connection conn = DBConnection.getConnection();
            JasperDesign jd = GetDesign("StudentTermEnrolment.jrxml");
            HashMap params = new HashMap();
            params.put("personId", personId);
            params.put("termId", termId);
            params.put("logo",GetImageLogo());
            params.put("footer", GetImageFooter());
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
            JasperDesign jd = GetDesign("GetStudentProofOfRegistration.jrxml");
            HashMap params = new HashMap();
            params.put("personId", personId);
            params.put("courseCode", courseCode);
            params.put("term", term);
            params.put("logo", GetImageLogo());
            params.put("footer", GetImageFooter());
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, params, conn);
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setTitle("Proof of registration");
            jv.setVisible(true);
         } catch (JRException ex) {
             JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
         }
    }
    
    public void ProofOfRegistration(int personId, int termId){
        try {
            Connection conn = DBConnection.getConnection();
            JasperDesign jd = GetDesign("ProofOfRegistration.jrxml");
            HashMap params = new HashMap();
            params.put("personId", personId);
            params.put("termId", termId);
            params.put("logo", GetImageLogo());
            params.put("footer", GetImageFooter());
            params.put("TermCourseModules", GetIreport("TermCourseModules.jasper"));
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, params, conn);
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setTitle("Proof Of Registration");
            jv.setVisible(true);
         } catch (JRException ex) {
             JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
         }
    }
}
