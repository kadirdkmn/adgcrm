/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ABDULKADIR
 */
public abstract class DBConnection {
    
    private Connection connection;
    private final String url =  "jdbc:mariadb://localhost:3306/adgcrm";
    private final String user = "adgcrm";
    private final String password = "12355" ;
    public Connection connect() {
               
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            this.connection = DriverManager.getConnection(url,user,password);
        } catch(Exception e) {
            System.out.print(e.getMessage());
        }
        return this.connection;
    }
    
}

