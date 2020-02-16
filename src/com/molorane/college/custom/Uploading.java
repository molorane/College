/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.custom;

import static java.lang.Thread.sleep;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Mothusi Molorane
 */
public class Uploading extends Thread{
    
    private int millis;
    private String message;
    private JPanel jpn;
    private JLabel lbl;
    private StringBuilder primes;
    private boolean state;

    public void setProgress(JPanel jpn,JLabel lbl) {
        this.jpn = jpn;
        this.millis = 800;
        this.lbl = lbl;
        primes = new StringBuilder(message);
        this.state = true;
    }

    @Override
    public void run () {
        clear();
        while ( state ) {
            try {
                sleep( millis );
            } catch ( InterruptedException err ) {}
        }
    }
    
    private void clear(){
        lbl.setText(null);
    }
    
    public void Stop(){
       state = false;
       lbl.setText(null);
    }
    
}
