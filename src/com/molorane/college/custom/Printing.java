/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.custom;

import com.molorane.college.bll.impl.MatricBoImpl;
import com.molorane.college.jasperservice.JasperMatric;
import com.molorane.college.view.Controls.Alert;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;

/**
 *
 * @author Mothusi Molorane
 */
public class Printing extends Thread{
    
    private int millis;
    private JPanel jpn;
    private JLabel lbl;
    private boolean state;
    private JButton btnPrint;
    private List<HashMap<String, Object>> uniqueIDs;
    private int year;
    private JFrame frame;
    private boolean aborted;
    private Alert alert;
    
    private final JasperMatric jasperMatric = new JasperMatric();
    private final MatricBoImpl matricBoImpl = new MatricBoImpl();
    
    public void setPrinting(JPanel jpn,JLabel lbl,Alert alert, JButton btnPrint,int year,JFrame frame) {
        this.jpn = jpn;
        this.millis = 400;
        this.lbl = lbl;
        this.state = true;
        this.btnPrint = btnPrint;
        this.year = year;
        this.frame = frame;
        this.alert = alert;
    }
    
    @Override
    public void run () {
        try{
            begin();
            aborted = false;
            uniqueIDs = matricBoImpl.GetUniqueIDsMatric(year);
            ArrayList<JasperPrint> reportList = new ArrayList<>();

            uniqueIDs.forEach((row) -> {
                if(state && !aborted){
                    lbl.setText("Generating for :"+row.get("idno").toString()+", "+row.get("lastName").toString());
                    JasperPrint jp = jasperMatric.GetStudentReport(row.get("idno").toString(), year);
                    if(jp != null){
                        reportList.add(jp);
                    }else{
                        aborted = true;
                    }
                }
            });
            
            if(state && !aborted){
                JRExporter expoter = new JRPdfExporter();
                expoter.setParameter(JRPdfExporterParameter.JASPER_PRINT_LIST, reportList);
                lbl.setText("Binding reports..");
                try (OutputStream output = new FileOutputStream(new File(System.getProperty("user.home"),"Desktop\\MatricResults-"+year+".pdf"))) {
                    lbl.setText("Saving file to desktop...");
                    expoter.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, output);
                    expoter.exportReport();
                }
                complete();
            }
            
            if(aborted){
                notComplete();
            }
        }catch(IOException | JRException ex){
            notComplete();
            Functions.errorMessage(ex.getLocalizedMessage());
        }
    }
    
    private void begin(){
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jpn.setVisible(true);
        lbl.setText("Exporting please wait..");
        btnPrint.setEnabled(false);
    }
    
    public void Stop(){
       state = false;
       jpn.setVisible(state);
       btnPrint.setEnabled(true);
       lbl.setText(null);
    }
    
    public void Cancel(){
       state = false;
       alert.notify("You canceled the operation..",0);
       lbl.setText(null);
       jpn.setVisible(state);
       btnPrint.setEnabled(true);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void notComplete(){
       state = false;
       alert.notify("An error occured..",0);
       jpn.setVisible(false);
       btnPrint.setEnabled(true);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void complete(){
        state = false;
        alert.notify("PDF file exported to desktop.", 1);
        jpn.setVisible(false);
        btnPrint.setEnabled(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
