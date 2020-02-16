/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.db;

/**
 * @author Mothusi Molorane
 */


import com.molorane.college.custom.Functions;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
    public Connection connection=null;
    public  static Connection getConnection()
    {
        System.out.println("------Mysql Connection Testing-----");
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
            Functions.errorMessage("Where is mysql Driver: "+e.getLocalizedMessage());
        }
       
        System.out.println("mysql Driver registered");
        Connection connection = null;
        
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost/college_gui","root","Blessing**4");
        }catch(SQLException e){
            Functions.errorMessage(e.getLocalizedMessage());
        }
        
        if(connection!=null){
            System.out.println("Connected");
        }else{
            Functions.errorMessage("Failed to connect to data repository. This is normally a network issue!");
            System.exit(0);
        }
        return connection;
    }
}
