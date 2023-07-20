package Modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
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


    public static boolean agregarOrden(Orden orden) {
        var conexion = ConexionSQL.getConexion();
        assert conexion != null;
        
        try{
           var cadena = conexion.prepareStatement("INSERT INTO Orden(nombre, cantidad, precio, cliente, observacion) VALUES(?,?,?,?,?)");
           cadena.setString(1, orden.nombre);
           cadena.setDouble(2, orden.cantidad);
           cadena.setDouble(3, orden.precio);
           cadena.setString(4, orden.cliente);
           cadena.setString(5, orden.observacion);
           cadena.executeUpdate();
           
           return true;
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }
    }

    public static void main(String[] args) {
        var resultado = Orden.agregarOrden(new Orden("Coca Cola", 2, 1.5, "Susan", "Con salsa de mostaza y miel"));
        System.out.println(resultado);
    }


    public static List<Orden> leerOrdenes() {
        List<Orden> ordenes = new ArrayList<>();

        var conexion = ConexionSQL.getConexion();
        assert conexion != null;

        try {
            var sentencia = conexion.prepareStatement("SELECT * FROM Orden;");

            retrieveOrdenes((List<Orden>) ordenes, sentencia);
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return ordenes;
    }


    public static boolean updateOrdenes(Orden orden) {
         var conexion = ConexionSQL.getConexion();
        assert conexion != null;
        
        try{
           var cadena = conexion.prepareStatement("UPDATE Orden set nombre= ?, cantidad =?, precio=?, cliente=?, observcaion=? where id = ?");
           cadena.setString(1, orden.nombre);
           cadena.setDouble(2, orden.cantidad);
           cadena.setDouble(3, orden.precio);
           cadena.setString(4, orden.cliente);
           cadena.setString(5, orden.observacion);
           cadena.executeUpdate();
           
           return true;
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }
    }

    public static boolean eliminarOrden(int id) {
        var conexion = ConexionSQL.getConexion();
        assert conexion != null;

        try {
            var sentencia = conexion.prepareStatement("DELETE FROM Orden WHERE id LIKE %?%");

            sentencia.setInt(1, id);
            var resultado = sentencia.executeUpdate();
            return resultado == 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    public static List<Orden> searchOrden(String name) {
        List<Orden> ordenes = new ArrayList<>();

        var conexion = ConexionSQL.getConexion();
        assert conexion != null;

        try {
            var sentencia = conexion.prepareStatement("SELECT * FROM Orden WHERE nombre LIKE ?;");
            sentencia.setString(1, "%" + name + "%");

            retrieveOrdenes((List<Orden>) ordenes, sentencia);
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return ordenes;

    }

    private static void retrieveOrdenes(List<Orden> ordenes, PreparedStatement sentencia) throws SQLException {
        var resultado = sentencia.executeQuery();
        while (resultado.next()) {
            var id = resultado.getString("id");
            var nombre = resultado.getString("nombre");
            var cantidad = resultado.getInt("cantidad");
            var precio = resultado.getDouble("precio");
            var cliente = resultado.getString("cliente");
            var observacion = resultado.getString("observacion");

            var orden = new Orden(id, nombre, cantidad, precio, cliente, observacion);
            ordenes.add(orden);
        }
    }
}
