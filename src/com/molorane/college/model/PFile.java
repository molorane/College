/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.model;

import com.molorane.college.custom.Functions;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

/**
 *
 * @author Mothusi Molorane
 */
public class PFile {
    
    private int upId;
    private long personId;
    private String fileName;
    private InputStream file;
    
    public void setPFile(int upId,long personId, String fileName, InputStream file){
        this.setUpId(upId);
        this.setPersonId(personId);
        this.setFileName(fileName);
        this.setFile(file);
    }
    
    public void setPFile(int upId,long personId, String fileName, Blob file){
        try{
            this.setUpId(upId);
            this.setPersonId(personId);
            this.setFileName(fileName);
            this.setFile(file.getBinaryStream());
        }catch(SQLException ex){
            Functions.errorMessage(ex.getLocalizedMessage());
        }
    }
    
    public void setPFile(int upId,long personId, String fileName){
        this.setUpId(upId);
        this.setPersonId(personId);
        this.setFileName(fileName);
    }

    /**
     * @return the upId
     */
    public int getUpId() {
        return upId;
    }

    /**
     * @param upId the upId to set
     */
    public void setUpId(int upId) {
        this.upId = upId;
    }

    /**
     * @return the personId
     */
    public long getPersonId() {
        return personId;
    }

    /**
     * @param personId the personId to set
     */
    public void setPersonId(long personId) {
        this.personId = personId;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the file
     */
    public InputStream getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(InputStream file) {
        this.file = file;
    }

}
