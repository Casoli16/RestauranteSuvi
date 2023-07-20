package Modelo;

import java.sql.SQLException;
import java.util.List;

public class Orden {
    public String id;
    public String nombre;
    public int cantidad;
    public double precio;
    public String cliente;
    public String observacion;

    public Orden(String id, String nombre, int cantidad, double precio, String cliente, String observacion) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.cliente = cliente;
        this.observacion = observacion;
    }

    public Orden(String nombre, int cantidad, double precio, String cliente, String observacion) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.cliente = cliente;
        this.observacion = observacion;
    }

//    public static void main(String[] args) {
//        var resultado = Orden.agregarOrden(new Orden("Coca Cola", 2, 1.5, "Susan", "Con salsa de mostaza y miel"));
//        System.out.println(resultado);
//    }

    public static boolean agregarOrden(Orden orden) {
        var conexion = ConexionSQL.getConexion();
        assert conexion != null;

        try {
            var sentencia = conexion.prepareStatement("INSERT INTO Orden (nombre, cantidad, precio, cliente, observacion) VALUES (?, ?, ?, ?, ?);");
            sentencia.setString(1, orden.nombre);
            sentencia.setInt(2, orden.cantidad);
            sentencia.setDouble(3, orden.precio);
            sentencia.setString(4, orden.cliente);
            sentencia.setString(5, orden.observacion);

            var resultado = sentencia.executeUpdate();

            conexion.close();
            return resultado == 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static List<Orden> leerOrdenes() {
            return null;
    }


    public static boolean updateOrdenes() {
        return false;

    }

    public static boolean eliminarOrden() {

        return false;

    }

    public static List<Orden> searchOrden() {
        return null;

    }
}
