/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.custom;

import com.molorane.college.view.Controls.Alert;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mothusi Molorane
 */
public class Exporting extends Thread {
    
    private int millis;
    private JPanel jpn;
    private JLabel lbl;
    private boolean state;
    private DefaultTableModel model;
    private NotifyData result;
    private PanelNotify pnlNotifySlider;
    private JPanel pnlNotify;
    private String subject;
    private JPanel alert;

    public void setProgress(JPanel jpn,JLabel lbl,DefaultTableModel model, NotifyData result,Alert alert, String subject) {
        this.jpn = jpn;
        this.millis = 400;
        this.lbl = lbl;
        this.state = true;
        this.model = model;
        this.result = result;
        this.pnlNotifySlider = alert.getJPanelSlider();
        this.pnlNotify = alert.getpnlNotify();
        this.subject = subject;
        this.alert = alert;
    }

    @Override
    public void run () {
        jpn.setVisible(true);
        lbl.setText("Exporting please wait..");
        try{
            
            try {
                sleep( millis );
            } catch ( InterruptedException err ) {}
            
            File f = new File(System.getProperty("user.home"),"Desktop\\"+subject+".csv");
            FileWriter csv = new FileWriter(f);
            
            for(int i=0; i < model.getColumnCount();i++){
               csv.write(model.getColumnName(i)+",");
            }
            csv.write("\n");
            
            for(int j=0; j < model.getRowCount(); j++){
                if(state){
                    for(int i=0; i < model.getColumnCount();i++){
                        csv.write(model.getValueAt(j,i).toString()+",");
                    }
                    csv.write("\n");
                }else{
                    Stop();
                    break;
                }
            }
            csv.close();
            result.setSuccess(1);
            result.setMsg("Data exported to desktop successfully.");
            result.init();
            pnlNotifySlider.notify(pnlNotify,result);
            jpn.setVisible(false);
        }catch(IOException ex){
            result.setSuccess(0);
            result.setMsg(ex.getLocalizedMessage());
            result.init();
            pnlNotifySlider.notify(pnlNotify,result);
            jpn.setVisible(false);
            Functions.errorMessage(ex.getLocalizedMessage());
        }
    }
    
    private void clear(){
        lbl.setText(null);
    }
    
    public void Stop(){
       state = false;
       jpn.setVisible(state);
       lbl.setText(null);
    }
}
