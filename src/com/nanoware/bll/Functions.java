/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll;

import com.jtattoo.plaf.graphite.GraphiteLookAndFeel;
import com.nanoware.bll.impl.UserBoImpl;
import com.nanoware.model.User;
import com.nanoware.view.ImageHelper;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        
    public static void setLoggedInUser(JLabel label, User user){
        String session = "USER: "+user.getRoleName()+", "+user.getFirstName()+"  "+user.getLastName();
        label.setText(session);
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
        return "The Student Portal 1.0";
    }
    
    public static String designedDetails(){
        return "Copyright 2019 Design by Molorane & Mokwena";
    }
    
    public static Color pnlBackgroundSideMenu(){
        return new Color(54, 33, 89);
    }
    
    public static Color pnlBackgroundTop(){
        return new Color(122, 72, 221);
    }
    
    public static Color pnlBackgroundBottom(){
        return new Color(122, 72, 221);
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
    
    public static User GetUser(){
        UserBoImpl user = new UserBoImpl();
        try{
            return user.GetUser("admin");
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
        frame.setIconImage(ImageHelper.loadImage("/images/studentportal.png").getImage());
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
        depts.add("ENG");
        depts.add("BUS");
        depts.add("ITE");
        return depts;
    }
}
