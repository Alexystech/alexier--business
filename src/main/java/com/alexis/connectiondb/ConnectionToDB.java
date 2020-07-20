/*
 * Copyright (c) Develop by Alexis Vazquez
 */

package com.alexis.connectiondb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionToDB {

    private Statement statement;
    private Connection conn;
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String user = "root";
    private final String password = "1414";
    private final String url = "jdbc:mysql://localhost:3306/negocio";

    public ConnectionToDB() {
        conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,password);
            if (conn != null) {
                System.out.println("conexion establecida");
            }
            statement = conn.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("error de conexion");
        }
    }

    public void desconectar() {
        conn = null;
        System.out.println("conexion finalizada");
    }

    public Connection getConn() {
        return conn;
    }

    public Statement getStatement() {
        return statement;
    }
}
