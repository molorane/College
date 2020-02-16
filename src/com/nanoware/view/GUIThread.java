/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.view;

import static java.lang.Thread.sleep;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Mothusi Molorane
 */
public class GUIThread extends Thread {
    
    private int millis;
    private int loops;
    private String message;
    private JLabel lbl;
    private StringBuilder primes;
    private int count;
    private JPanel jpn;
    private boolean state;

    public void setGUIThread(JPanel jpn,String message,JLabel lbl, int millis) {
        this.jpn = jpn;
        this.millis = millis;
        this.message = message;
        this.lbl = lbl;
        this.loops = message.length();
        primes = new StringBuilder(message);
        this.state = true;
        this.jpn.setVisible(state);
        count = 0;
    }
    
    public void setGUIThread(JPanel jpn,String message,JLabel lbl){
        this.jpn = jpn;
        this.message = message;
        this.lbl = lbl;
        this.millis = 100;
        this.loops = message.length();
        primes = new StringBuilder(message);
        this.state = true;
        this.jpn.setVisible(state);
        count = 0;
    }

    @Override
    public void run () {
        clear();
        while ( state ) {
            count++;
            SwingUtilities.invokeLater(() -> {
                lbl.setText(primes.substring(0,count));
            });
            try {
                sleep( millis );
            } catch ( InterruptedException err ) {}
            
            if(count == message.length()){
                state = false;
            }
        }
    }
    
    private void clear(){
        count = 0;
        lbl.setText(null);
    }
    
    public void Stop(){
        count = 0;
        state = false;
       lbl.setText(null);
       jpn.setVisible(state);
    }
}
