package Modelo;

//Importamos la libreria JDBC

import java.sql.*;

//Creación de la clase de conexión (la cual tiene el mismo nombre del archivo de la clase)


public class ConexionSQL { // <- Editar por el nombre del archivo de la clase

    static String host = "localhost";
    static String port = "1433";
    static String database = "RestauranteSuvi"; // <- Editar por su base de datos
    static String user = "sa"; // <- Editar por su base de datos
    static String password = "Rical2023"; // <- Editar por su base de datos

    //Creación del metodo de conexión que retorna la conexión
    public static Connection getConexion() {

        //Cadena de conexión
        String conexionUrl = String.format("jdbc:sqlserver://%s:%s;", host, port) // <- Editar por su puerto de SQL
                + String.format("databaseName=%s;", database) // <- Editar por su base de datos
                + String.format("user=%s;", user)
                + String.format("password=%s;", password)
                + "encrypt=true;trustServerCertificate=true";

        //Retornamos la conexion
        try {
            //Creamos una variable de tipo Connection, al que le pasamos nuestra cadena de conexion
            Connection conn = DriverManager.getConnection(conexionUrl);
            //SI funciona, retornamos la conexion
            return conn;
        } catch (SQLException ex) {
            //Si no funciona, imprimimos en consola el error y retornamos un valor nulo
            System.out.println(ex.toString());
            return null;
        }
    }
}