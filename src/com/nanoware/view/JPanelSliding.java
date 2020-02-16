/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.view;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Mothusi Molorane
 */

public class JPanelSliding extends JPanel{


    public JPanelSliding() {
        setLayout(new CardLayout());
        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Dimension size = new Dimension(this.getWidth(), this.getHeight());
    }

    public void NextSlidPanel(Component ShowPanel) {

        if (!ShowPanel.getName().equals(getCurrentComponentShow(this))) 
        {
            Component currentComp=getCurrentComponent(this);
            Rectangle b=currentComp.getBounds();
            ShowPanel.setVisible(true);
            JPanelSlidingListener sl=new JPanelSlidingListener(10, currentComp,ShowPanel, true);
            Timer t=new Timer(40,sl);
            sl.timer=t;
            t.start();
        }
    }

    public void NextSlidPanel(int SpeedPanel,Component ShowPanel) {

        if (!ShowPanel.getName().equals(getCurrentComponentShow(this)))
        {
            Component currentComp=getCurrentComponent(this);
            Rectangle b=currentComp.getBounds();
            ShowPanel.setVisible(true);
            JPanelSlidingListener sl=new JPanelSlidingListener(SpeedPanel, currentComp,ShowPanel, true);
            Timer t=new Timer(40,sl);
            sl.timer=t;
            t.start();
        }
    }

    public void NextSlidPanel(int SpeedPanel,Component ShowPanel,boolean DirectionMove) {

        if (!ShowPanel.getName().equals(getCurrentComponentShow(this))) 
        {
            Component currentComp=getCurrentComponent(this);
            Rectangle b=currentComp.getBounds();
            ShowPanel.setVisible(true);
            JPanelSlidingListener sl=new JPanelSlidingListener(SpeedPanel, currentComp,ShowPanel, DirectionMove);
            Timer t=new Timer(40,sl);
            sl.timer=t;
            t.start();
        }
    }

    public void NextSlidPanel(int SpeedPanel,int TimeSpeed, Component ShowPanel,boolean DirectionMove) {

        if (!ShowPanel.getName().equals(getCurrentComponentShow(this))) 
        {
            Component currentComp=getCurrentComponent(this);
            Rectangle b=currentComp.getBounds();
            ShowPanel.setVisible(true);
            JPanelSlidingListener sl=new JPanelSlidingListener(SpeedPanel, currentComp,ShowPanel, DirectionMove);
            Timer t=new Timer(TimeSpeed,sl);
            sl.timer=t;
            t.start();
        }

    }

    public Component getCurrentComponent(Container parent) {
        Component comp = null;
        int n = parent.getComponentCount();
        for (int i = 0 ; i < n ; i++) 
            {
            comp = parent.getComponent(i);
                if (comp.isVisible())
                    {
                        return comp;
                    }
            }
        return comp;
    }

    public String getCurrentComponentShow(Container parent) 
    {
        String PanelName = null;
        Component comp = null;
        int n = parent.getComponentCount();
        for (int i = 0 ; i < n ; i++) 
            {
            comp = parent.getComponent(i);
                if (comp.isVisible())
                    {
                        PanelName=comp.getName();
                        return PanelName;
                    }
            }
        System.out.println("Algo Aqui"+PanelName);
        return PanelName;
    }

    public class JPanelSlidingListener implements ActionListener {

        Component HidePanel;
        Component ShowPanel;
        int steps;
        int step=0;
        Timer timer;
        boolean isNext;

        public JPanelSlidingListener(int steps,Component HidePanel, Component ShowPanel, boolean isNext)
        {
            this.steps=steps;
            this.HidePanel=HidePanel;
            this.ShowPanel=ShowPanel;
            this.isNext=isNext;
        }

        public void actionPerformed(ActionEvent e)
        {
            Rectangle bounds=HidePanel.getBounds();
            int shift=bounds.width/steps;
            if (!isNext) 
                {
                    HidePanel.setLocation(bounds.x-shift, bounds.y);
                    ShowPanel.setLocation(bounds.x-shift+bounds.width, bounds.y);
                }
            else 
                {
                    HidePanel.setLocation(bounds.x+shift, bounds.y);
                    ShowPanel.setLocation(bounds.x+shift-bounds.width, bounds.y);
                }

            repaint();
            step++;
            if (step==steps)
                {
                    timer.stop();
                    HidePanel.setVisible(false);
                }
        }
    }

    public void refresh() 
    {
        revalidate();
        repaint();
    }
}
