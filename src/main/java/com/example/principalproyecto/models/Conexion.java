package com.example.principalproyecto.models;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    private static final String bd = "tiendabd";
    private static final String user = "topicos2021";
    private static final String pas = "12345678";
    private static final String server = "127.0.0.1";


    public static Connection conexion;

    public static void getConexion() throws Exception {
        Class.forName("org.mariadb.jdbc.Driver");
        conexion = DriverManager.getConnection("jdbc:mariadb://" + server + ":3306/" + bd, user, pas);
    }
}






