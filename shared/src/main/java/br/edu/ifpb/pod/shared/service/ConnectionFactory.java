/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod.shared.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author miolivc
 */
public class ConnectionFactory implements ConnectionService {

    private Connection connection;
    private final String user = "postgres";
    private final String password = "123456";
    private final String host;

   
    private ConnectionFactory(String host) {
        this.connection = connection;
        this.host = host;
    }
     public static ConnectionFactory  of(String host) { 
        return new ConnectionFactory(host);
      
        
    }
    

    @Override
    public Connection getConnection() {
        try {
             Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(host, user, password);
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println(ex);
        }
        return connection;
    }

}
