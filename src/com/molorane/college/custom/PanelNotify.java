/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.custom;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Mothusi Molorane
 */
public class PanelNotify extends JPanel {
    
   public static final boolean left = false;
   public static final boolean right = true;
  
    private GUIThread guiThread;
    private GUIThread guiThreadPrevious = null;
    private boolean noDisplay = false;
    private NotifyData notifyData;

    public PanelNotify(){
        setLayout(new CardLayout());
        setBorder(BorderFactory.createEtchedBorder());
        Dimension dimension = new Dimension(getWidth(), getHeight());
    }

  public void nextPanel(Component panel) {
    Component currentComp = getCurrentComponent(this);
    Rectangle b = currentComp.getBounds();
    panel.setVisible(true);
    PanelNotifyListener sl = new PanelNotifyListener(10, currentComp, panel, true);
    Timer t = new Timer(40, sl);
    sl.timer = t;
    t.start();
  }

  public void notify(Component pnlNotify, NotifyData notifyData) {
    noDisplay = false;
    this.notifyData = notifyData;
    this.notifyData.init();
    Component comp = getCurrentComponent(this);
    comp.getBounds();
    pnlNotify.setVisible(true);
    PanelNotifyListener jsl = new PanelNotifyListener(30, comp, pnlNotify, left);
    Timer t = new Timer(40, jsl);
    jsl.timer = t;
    t.start();
  }
  
  public void noDisplay(Component pnlNoDisplay){
    noDisplay = true;
    Component comp = getCurrentComponent(this);
    comp.getBounds();
    pnlNoDisplay.setVisible(true);
    PanelNotifyListener jsl = new PanelNotifyListener(30, comp, pnlNoDisplay, right);
    Timer t = new Timer(40, jsl);
    jsl.timer = t; 
    t.start();
  }

  public void nextPanel(int panelSpeed, int timeSpeed, Component panel, boolean direction) {
    Component comp = getCurrentComponent(this);
    comp.getBounds();
    panel.setVisible(true);
    PanelNotifyListener jsl = new PanelNotifyListener(panelSpeed, comp, panel, direction);
    Timer t = new Timer(timeSpeed, jsl);
    jsl.timer = t;
    t.start();
  }

  public Component getCurrentComponent(Container parent){
    Component comp = null;
    int count = parent.getComponentCount();
    for (int i = 0; i < count; i++) {
      comp = parent.getComponent(i);
      if (comp.isVisible()) {
        return comp;
      }
    }
    return comp;
  }

  public String getCurrentComponentShow(Container parent) {
    String panelName = null;
    Component comp = null;
    int count = parent.getComponentCount();
    for (int i = 0; i < count; i++) {
      comp = parent.getComponent(i);
      if (comp.isVisible()) {
        panelName = comp.getName();
        return panelName;
      }
    }
    return panelName;
  }

  public void refresh(){
    revalidate();
    repaint();
  }

  public class PanelNotifyListener implements ActionListener {
    Component hidePanel;
    Component showPanel;
    int steps;
    int step = 0;
    Timer timer;
    boolean isNext;

    public PanelNotifyListener(int steps, Component hidePanel, Component panel, boolean isNext) {
      this.steps = steps;
      this.hidePanel = hidePanel;
      this.showPanel = panel;
      this.isNext = isNext;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      Rectangle bounds = this.hidePanel.getBounds();
      int shift = bounds.width / this.steps;
      if (!this.isNext) {
        this.hidePanel.setLocation(bounds.x - shift, bounds.y);
        this.showPanel.setLocation(bounds.x - shift + bounds.width, bounds.y);
      }
      else {
        this.hidePanel.setLocation(bounds.x + shift, bounds.y);
        this.showPanel.setLocation(bounds.x + shift - bounds.width, bounds.y);
      }

      PanelNotify.this.repaint();
      this.step += 1;

      if (this.step == this.steps) {
        this.timer.stop();
        this.hidePanel.setVisible(false);
        this.showPanel.setVisible(true);
        if(!noDisplay){
            guiThread = new GUIThread();
            if(guiThreadPrevious!=null){ guiThreadPrevious.Stop(); }
            guiThreadPrevious = guiThread;
            guiThread.setGUIThread(notifyData.getMsg(), notifyData.getLblStatus());
            guiThread.start();
        }
      }
    }
  }
}
