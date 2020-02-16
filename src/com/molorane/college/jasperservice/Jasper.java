/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.jasperservice;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class Jasper {
    
    private final String path = "/com/molorane/college/jasperreports/";
    
    protected JasperDesign GetDesign(String file){
        try {
            return JRXmlLoader.load(getClass().getResourceAsStream(path+file));
        } catch (JRException ex) {
            Logger.getLogger(Jasper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    protected JasperReport GetReport(String file){
        try {
           InputStream jasperStream = getClass().getResourceAsStream(path+file); 
           JasperReport jr = (JasperReport)JRLoader.loadObject(jasperStream);
           return jr; 
        } catch (JRException ex) {
            Logger.getLogger(Jasper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    protected String GetIreport(String file){
        return getClass().getResource(path+file).toString();
    }
    
    protected BufferedImage GetImage(String img){
       try {
           InputStream inputStream = getClass().getResourceAsStream(path+img);
           return ImageIO.read(new ByteArrayInputStream(JRLoader.loadBytes(inputStream)));
        } catch (JRException | IOException ex) {
            Logger.getLogger(Jasper.class.getName()).log(Level.SEVERE, null, ex);
        }
       return null;
    }
    
    protected BufferedImage GetImageLogo(){
        try {
           InputStream inputStream = getClass().getResourceAsStream(path+"logo.jpg");
           return ImageIO.read(new ByteArrayInputStream(JRLoader.loadBytes(inputStream)));
        } catch (JRException | IOException ex) {
            Logger.getLogger(Jasper.class.getName()).log(Level.SEVERE, null, ex);
        }
       return null;
    }
    
    protected BufferedImage GetImageFooter(){
        try {
           InputStream inputStream = getClass().getResourceAsStream(path+"footer.jpg");
           return ImageIO.read(new ByteArrayInputStream(JRLoader.loadBytes(inputStream)));
        } catch (JRException | IOException ex) {
            Logger.getLogger(Jasper.class.getName()).log(Level.SEVERE, null, ex);
        }
       return null;
    }
    
    
}
