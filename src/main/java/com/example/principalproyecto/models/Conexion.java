package com.example.principalproyecto.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String bd = "tiendabd";
    private static final String user = "topicos2021";
    private static final String pas = "12345678";
    private static final String url = "jdbc:mysql://localhost:3306/" + bd;
    public static Connection conexion = null;

    public static void getConexion() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        //JAR Mariadb
        //conexion = DriverManager.getConnection(server + bd, user, pas);

        //JAR MySQL
        conexion = (Connection) DriverManager.getConnection(url, user, pas);
    }


    public void Desconectar() {
        try {
            conexion.close();
            System.out.println("Desconexion exitosa");
        } catch (SQLException e) {
            System.err.println("Desconexion fallida");
        }
    }
}






