package com.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;
    
    public static Connection getConnection(){
        if(connection == null){createConnection();}
        return connection;
    }

    private static void createConnection(){
        String url = "jdbc:mysql://localhost:3306/almacen";
        String user = "root";
        String pass = "";
        try {
            connection=DriverManager.getConnection(url, user, pass);            
        } catch (SQLException e) {
            System.out.println("Fallo de conexión base de datos.");
            System.out.println(e.getMessage());
        }
    }
    
}
