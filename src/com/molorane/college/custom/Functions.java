/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.custom;

import com.molorane.college.college.ICollege;
import com.jtattoo.plaf.graphite.GraphiteLookAndFeel;
import com.molorane.college.bll.impl.UserBoImpl;
import com.molorane.college.college.Tshwane;
import com.molorane.college.model.PFile;
import com.molorane.college.model.User;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Mothusi Molorane
 */
public class Functions {
    
    private static final ICollege college = new Tshwane();
        
    public static void setLoggedInUser(JLabel label, User user){
        String session = ""+user.getRoleName()+": "+user.getFirstName()+"  "+user.getLastName();
        label.setText(session);
    }
    
    public static String getCentreNo(){
        return college.getCentreNo();
    }
    
    public static String getCentreName(){
        return college.getCentreName();
    }
    
    public static String GetPassword(JPasswordField pass){
        String r = "";
        char[] password = pass.getPassword();
        
        for(int i=0;i<password.length;i++){
            r +=password[i];
        }
      return r;
    }
    
    public static double toTwoDecimal(double money){
        DecimalFormat df = new DecimalFormat(".##");
        return Double.parseDouble(df.format(money));
    }
    
    public String GetCurrency1(double amount){
        return String.format("%,.2f", amount);
    }
    
    public static String GetCurrencyZAR(double amount){
        Locale locale = new Locale("en", "ZA");      
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return currencyFormatter.format(amount);
    }
    
    public static String GetCurrency(double amount){
        Locale locale = new Locale("en", "ZA");      
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return currencyFormatter.format(amount).substring(2);
    }
    
    public static String GetDateToday(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date dt = new Date();
        return formatter.format(dt);
    }
    
    public static String GetDateTimeToday(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        Date dt = new Date();
        return formatter.format(dt); 
    }
    
    public static int GetCurrentYear(){
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR);
    }
    
    public static String GetCurrentDate(){
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR)+"-"+cal.get(Calendar.MONTH)+"-"+cal.get(Calendar.DAY_OF_MONTH);
    }
    
    public static void centerFrame(JFrame frm){
        Dimension windowSize = frm.getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();

        int dx = centerPoint.x - windowSize.width / 2;
        int dy = centerPoint.y - windowSize.height / 2;    
        frm.setLocation(dx, dy);
    }
    
    public static void setFormTheme(){
        try{
            Properties props = new Properties();
            props.put("logoString", "NanoWare");
            props.put("textShadow", "on");
            props.put("systemTextFont", "Segeo UI PLAIN 13");
            props.put("controlTextFont", "Segeo UI PLAIN 13");
            props.put("menuTextFont", "Segeo UI PLAIN 13");
            props.put("userTextFont", "Segeo UI PLAIN 13");
            props.put("subTextFont", "Segeo UI PLAIN 12");
            props.put("windowTitleFont", "Segeo UI BOLD 14");
            props.put("windowTitleForegroundColor", "255 255 255");
            props.put("windowTitleBackgroundColor", "103 99 100");
            props.put("windowTitleColorLight", "103 99 100");
            props.put("windowTitleColorDark", "103 99 100");
            props.put("windowBorderColor", "103 99 100");
            GraphiteLookAndFeel.setCurrentTheme(props);
            UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void errorMessage(String msg){
        JOptionPane.showMessageDialog(null,msg,"Error!",JOptionPane.ERROR_MESSAGE);
    }
    
    public static void warningMessage(String msg){
       JOptionPane.showMessageDialog(null,msg,"Warning!",JOptionPane.WARNING_MESSAGE); 
    }
    
    public static void successMessage(String msg){
       JOptionPane.showMessageDialog(null,msg,"Successful!",JOptionPane.INFORMATION_MESSAGE); 
    }
    
    public static void infoMessage(String msg){
       JOptionPane.showMessageDialog(null,msg,"Information!",JOptionPane.INFORMATION_MESSAGE); 
    }
        
    public static String appName(){
        return college.appName();
    }
    
    public static String getCollegeName(){
        return college.getCollegeName();
    }
    
    public static String designedDetails(){
        return college.designedDetails();
    }
    
    public static void initForm(JFrame frm){
        setAppIcon(frm);
        centerFrame(frm);
    }
    
    public static String dashboardTitle(){
        return college.dashboardTitle();
    }
 
    public static Color pnlBackgroundSideMenu(){
        return college.pnlBackgroundSideMenu();
    }
    
    public static Color pnlBackgroundTop(){
        return college.pnlBackgroundTop();
    }
    
    public static Color pnlBackgroundBottom(){
        return college.pnlBackgroundBottom();
    }
	
    public static Color activeMenu(){
        return college.activeMenu(); 
    }
    
    public static Color setFontColor(){
        return college.setFontColor();
    }
    
    public static void setColor(JPanel panel){
        college.setColor(panel);
    }
    
    public static void resetColor(JPanel panel){
       college.resetColor(panel);
    }
    
    public static void notify(PanelNotify jpnNotifySlider, JPanel pnlNotify, JLabel lblMSGNotify,JLabel lblIMGNotify,String msg,int state){
        NotifyData nt = new NotifyData();
        nt.setNotifyData(pnlNotify, lblMSGNotify, lblIMGNotify, msg, state);
        jpnNotifySlider.notify(pnlNotify, nt); 
    }    
    
    public static void SQLException(SQLException e){
        switch (e.getErrorCode()) {
            case 1062:
                errorMessage("Duplicate entry.");
                break;
            case 1452:
                errorMessage("A corresponding record is missing from a dependent entity");
                break;
            default:
                errorMessage(e.getLocalizedMessage());
                break;
        }
    }
    
    public static String getDesktopPath(){
        return System.getProperty("user.home")+"\\Desktop\\";
    }
    
    public static List<HashMap<String,Object>> convertResultsetToList(ResultSet rs) throws SQLException{
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        List<HashMap<String,Object>> list = new ArrayList<>();
        while(rs.next()){
            HashMap<String,Object> row = new HashMap<>(columns);
            for(int i=1; i<= columns; ++i){
                row.put(md.getColumnName(i), rs.getObject(i));
            }
            list.add(row);
        }
        rs.close();
        return list;
    }
    
    public static HashMap<String,Object> convertResultsetToRecord(ResultSet rs) throws SQLException{
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        HashMap<String,Object> data = new HashMap<>();
        for(int i=1; i<= columns; ++i){
            data.put(md.getColumnName(i), rs.getObject(i));
        }
        rs.close();
        return data;
    }
    
    public static List<HashMap<String,Object>> readCSVFile(String path){
        try{
            File f = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(f));
            List<HashMap<String,Object>> list = new ArrayList<>();
            String  line;
            while((line=br.readLine())!=null){
                HashMap<String,Object> row = new HashMap<>(5);
                String [] value = line.split(",");//seperator
                row.put("personId", value[0]);
                row.put("lastName", value[1]);
                row.put("firstName", value[2]);
                row.put("otherName", value[3]);
                row.put("mark", value[4]);
                list.add(row);
            }
            return list;
        }catch(IOException ex){
            Functions.errorMessage(ex.getLocalizedMessage());
        }
        return null;
    }
    
    public static List<HashMap<String,Object>> readUploadFile(String path){
        try{
            File f = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(f));
            List<HashMap<String,Object>> list = new ArrayList<>();
            String  line;
            br.readLine();
            while((line=br.readLine())!=null){
                HashMap<String,Object> row = new HashMap<>(5);
                String [] value = line.split(",");//seperator
                row.put("IDNo", value[0]);
                row.put("LastName", value[1]);
                row.put("Names", value[2]);
                row.put("Year", value[3]);
                row.put("Subject", value[4]);
                row.put("Term 1", value[5]);
                row.put("Term 2", value[6]);
                row.put("Term 3", value[7]);
                list.add(row);
            }
            return list;
        }catch(IOException ex){
            Functions.errorMessage(ex.getLocalizedMessage());
        }
        return null;
    }
    
    public static List<HashMap<String,Object>> readStudentsUploadFile(String path){
        try{
            File f = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(f));
            List<HashMap<String,Object>> list = new ArrayList<>();
            String  line;
            br.readLine();
            while((line=br.readLine())!=null){
                HashMap<String,Object> row = new HashMap<>(5);
                String [] value = line.split(",");//seperator
                
                if(value.length == 4){
                    row.put("idno", value[0]);
                    row.put("lastName", value[1]);
                    row.put("names", value[2]);
                    row.put("subject", value[3]);
                    list.add(row);
                }else{
                    errorMessage("The formart of the file you are uploading\n"
                            + "does not match the required format. The format of the\n"
                            + "file must be exactly similar to the one of the table below.");
                    
                    return null;
                }
            }
            return list;
        }catch(IOException ex){
            Functions.errorMessage(ex.getLocalizedMessage()+" A problem occured!");
        }
        return null;
    }
    
    public static List<HashMap<String,Object>> readCollegeStudentsCSVFile(String path){
        try{
            File f = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(f));
            List<HashMap<String,Object>> list = new ArrayList<>();
            String  line;
            br.readLine();
            while((line=br.readLine())!=null){
                HashMap<String,Object> row = new HashMap<>(5);
                String [] value = line.split(",");//seperator
                if(value.length == 8){
                    row.put("studentno", value[0]);
                    row.put("idno", value[1]);
                    row.put("lastName", value[2]);
                    row.put("firstName", value[3]);
                    row.put("otherName", value[4]);
                    row.put("gender", value[5]);
                    row.put("genderId", (value[5]).equalsIgnoreCase("M")? 1: 2);
                    row.put("race", value[6]);
                    row.put("raceId", (value[6]).equalsIgnoreCase("B")? 1: 2);
                    row.put("langauge", value[7]);
                    row.put("langaugeId", (value[7]).equalsIgnoreCase("E")? 1: 2);
                    list.add(row);
                }else{
                    errorMessage("The formart of the file you are uploading\n"
                            + "does not match the required format. The format of the\n"
                            + "file must be exactly similar to the one of the table below.");
                    return null;
                }
            }
            return list;
        }catch(IOException ex){
            Functions.errorMessage(ex.getLocalizedMessage()+" A problem occured!");
        }
        return null;
    }
    
    public static User GetUser(){
        UserBoImpl user = new UserBoImpl();
        try{
            return user.GetUser("blessy");
        }catch(SQLException ex){
            errorMessage(ex.getLocalizedMessage());
        }
        return null;
    }
    
    public static User GetUser(String userName){
        UserBoImpl user = new UserBoImpl();
        try{
            return user.GetUser(userName);
        }catch(SQLException ex){
            errorMessage(ex.getLocalizedMessage());
        }
        return null;
    }
    
    public static void setAppIcon(JFrame frame){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension appSize = new Dimension(frame.getWidth(),frame.getHeight());
        int appPosX = screenSize.width/2- appSize.width/2;
        int appPosY = screenSize.width/2- appSize.width/2;
        Rectangle appBounds = new Rectangle(appPosX,appPosY,appSize.width,appSize.height);
        frame.setIconImage(ImageHelper.loadImage("/com/molorane/college/images/studentportal.png").getImage());
        frame.setBounds(appBounds);
    }
    
    public static String getDeptFromDepartment(String department){
        String dept = "";
        if(department.equals("Engineering"))
            dept = "ENG";
        else if(department.equals("Management"))
            dept = "BUS";
        else if(department.equals("IT"))
            dept = "ITE";
        return dept;
    }
    
    public static ArrayList<String> getDepts(){
        ArrayList<String> depts = new ArrayList<>();
        depts.add("SELECT");
        depts.add("ENG");
        depts.add("BUS");
        depts.add("ITE");
        return depts;
    }
    
    public static void writeLine(String line, String fileName){
        try{
            String str = getDesktopPath()+fileName+".txt";
            BufferedWriter bw = new BufferedWriter(new FileWriter(str,true));
            bw.write(line);
            bw.newLine();
            bw.close();
        }catch(IOException e){
            Functions.errorMessage(e.getLocalizedMessage());
        }
    }
    
    public static void clearFile(String fileName){
        try{
            PrintWriter pw = new PrintWriter(getDesktopPath()+fileName+".txt");
            pw.close();
        }catch(IOException e){
            Functions.errorMessage(e.getLocalizedMessage());
        }
    }
    
    public static String BrowseFile(JFrame frm){
        try{
            FileDialog fd = new FileDialog(frm, "Open File",FileDialog.LOAD);
            fd.setFile("*.csv");
            fd.setResizable(true);
            fd.setVisible(true);
            if(fd.getFile() == null){
                return null;
            }else{
                return fd.getDirectory()+fd.getFile();
            }
        }catch(Exception ex){
           Functions.errorMessage(ex.getLocalizedMessage());
           return null;
        }
    }
    
    public static PFile BrowseUploadFile(JFrame frm){
        PFile file = null;
        try{
            FileDialog fd = new FileDialog(frm, "Open File",FileDialog.LOAD);
            fd.setFile("*");
            fd.setResizable(true);
            fd.setVisible(true);
            if(fd.getFile() == null){
                return file;
            }else{
                file = new PFile();
                InputStream inputStream = new FileInputStream(new File(fd.getDirectory()+fd.getFile()));
                file.setFile(inputStream);
                file.setFileName(fd.getFile());
                return file;
            }
        }catch(FileNotFoundException ex){
           Functions.errorMessage(ex.getLocalizedMessage());
           return null;
        }
    }
    
    public static void DownloadFile(PFile file){
        try{
            OutputStream output = new FileOutputStream(new File(System.getProperty("user.home"),"Desktop\\"+file.getFileName()));
            int readBytes = -1;
            InputStream inputStream = file.getFile();
            byte[] buffer = new byte[4096];
            while((readBytes = inputStream.read(buffer)) != -1){
                output.write(buffer, 0, readBytes);
            }
            output.close();
            inputStream.close();
            Functions.successMessage("File saved to desktop with the same name as the name on the table.");
        }catch(IOException ex){
            Functions.errorMessage(ex.getLocalizedMessage());
        }
    }
    
    public static PFile BrowseProfilePhoto(JFrame frm){
        PFile file = null;
        try{
            file = new PFile();
            InputStream inputStream = new FileInputStream(new File(System.getProperty("user.home"),"Desktop\\profile.png"));
            file.setFile(inputStream);
            file.setFileName("profile.png");
            return file;
        }catch(FileNotFoundException ex){
           Functions.errorMessage(ex.getLocalizedMessage());
           return null;
        }
    }
}
