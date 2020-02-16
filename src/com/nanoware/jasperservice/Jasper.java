/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.jasperservice;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class Jasper {
    
    protected JasperDesign GetDesign(String path){
        try {
            return JRXmlLoader.load(getClass().getResourceAsStream(path));
        } catch (JRException ex) {
            Logger.getLogger(Jasper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    protected BufferedImage GetImage(String path){
       try {
           InputStream inputStream = getClass().getResourceAsStream(path);
           return ImageIO.read(new ByteArrayInputStream(JRLoader.loadBytes(inputStream)));
        } catch (JRException | IOException ex) {
            Logger.getLogger(Jasper.class.getName()).log(Level.SEVERE, null, ex);
        }
       return null;
    }
    
    protected BufferedImage GetImageLogo(){
        try {
           InputStream inputStream = getClass().getResourceAsStream("/JasperReports/logo.png");
           return ImageIO.read(new ByteArrayInputStream(JRLoader.loadBytes(inputStream)));
        } catch (JRException | IOException ex) {
            Logger.getLogger(Jasper.class.getName()).log(Level.SEVERE, null, ex);
        }
       return null;
    }
}