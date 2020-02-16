/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.model;


/**
 *
 * @author Mothusi Molorane
 */
public class Title{
    
    private int titleId;
    private String title;
    
    public void setTitle(int titleId,String title) {
        this.titleId = titleId;
        this.title = title;
    }

    /**
     * @return the titleId
     */
    public int getTitleId() {
        return titleId;
    }

    /**
     * @param titleId the titleId to set
     */
    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    @Override
    public String toString(){
        return title;
    }
}
