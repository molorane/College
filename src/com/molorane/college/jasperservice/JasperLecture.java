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
public class JasperLecture extends Jasper{    
    
    public void GetStudentMarks(int campusCode,String moduleCode, int termId, int assId){
        try {
            Connection conn = DBConnection.getConnection();
            JasperDesign jd = GetDesign("GetStudentMarks.jrxml");
            HashMap params = new HashMap();
            params.put("campusCode", campusCode);
            params.put("moduleCode", moduleCode);
            params.put("termId", termId);
            params.put("assId", assId);
            params.put("logo", GetImageLogo());
            params.put("footer", GetImageFooter());
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, params, conn);
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setTitle("Get Student Marks");
            jv.setVisible(true);
         } catch (JRException ex) {
             JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
         }
    }
    
    public void LectureClassList(int lectureId,int campusCode, int termId,String moduleCode){
        try {
            Connection conn = DBConnection.getConnection();
            JasperDesign jd = GetDesign("LectureClassList.jrxml");
            HashMap params = new HashMap();
            params.put("lectureId", lectureId);
            params.put("campusCode", campusCode);
            params.put("termId", termId);
            params.put("moduleCode", moduleCode);
            params.put("logo", GetImageLogo());
            params.put("footer", GetImageFooter());
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, params, conn);
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setTitle("Lecture Class List");
            jv.setVisible(true);
         } catch (JRException ex) {
             JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
         }
    }
    
    public void GetStudentModuleAssessment(int campusCode,int termId,String moduleCode){
        try {
            Connection conn = DBConnection.getConnection();
            JasperDesign jd = GetDesign("GetStudentModuleAssessment.jrxml");
            HashMap params = new HashMap();
            params.put("campusCode", campusCode);
            params.put("termId", termId);
            params.put("moduleCode", moduleCode);
            params.put("logo", GetImageLogo());
            params.put("footer", GetImageFooter());
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, params, conn);
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setTitle("Campus Module Assessment");
            jv.setVisible(true);
         } catch (JRException ex) {
             JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
         }
    }
    
    public void GetCampusModuleTestTimeTable(int campusCode,String moduleCode,int termId){
        try {
            Connection conn = DBConnection.getConnection();
            JasperDesign jd = GetDesign("GetCampusModuleTestTimeTable.jrxml");
            HashMap params = new HashMap();
            params.put("campusCode", campusCode);
            params.put("moduleCode", moduleCode);
            params.put("termId", termId);
            params.put("logo", GetImageLogo());
            params.put("footer", GetImageFooter());
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, params, conn);
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setTitle("Campus Module Test TimeTable");
            jv.setVisible(true);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
    }
}
