package Controller;

import Modelo.Orden;
import Vista.frmComida;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class OrdenController implements ActionListener {

    private Orden orden;
    
    private frmComida frmComida = new frmComida();
    int selectedId;
    

    public OrdenController(Orden orden, frmComida frmComida) {
        this.orden = orden;
        this.frmComida = frmComida;
        this.frmComida.btnAgregar.addActionListener(this);
        this.frmComida.btnActualizaar.addActionListener(this);
        this.frmComida.btnEliminar.addActionListener(this);
        this.frmComida.jTableOrden.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
              int filaSeleccionada = frmComida.jTableOrden.getSelectedRow();         
              
              selectedId = (int) frmComida.jTableOrden.getValueAt(filaSeleccionada, 0);
              String nombre = frmComida.jTableOrden.getValueAt(filaSeleccionada, 1).toString();
              String cantidad = frmComida.jTableOrden.getValueAt(filaSeleccionada, 2).toString();
              String precio = frmComida.jTableOrden.getValueAt(filaSeleccionada, 3).toString();
              String cliente = frmComida.jTableOrden.getValueAt(filaSeleccionada, 4).toString();
              String observacion = frmComida.jTableOrden.getValueAt(filaSeleccionada, 5).toString();
              
              frmComida.txtNombre.setText(nombre);
              frmComida.txtCantidad.setText(cantidad);
              frmComida.txtPrecio.setText(precio);
              frmComida.txtCliente.setText(cliente);
              frmComida.txtObservacion.setText(observacion);
            }

        });

        initData();
    }

    private boolean validateNumbers(String datos){
        return datos.matches("[0-9]*\\.?[0-9]+");
    }
 
    private boolean validate() {
        // Validate

        if (frmComida.txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre es requerido");
            return false;
        }

        if (frmComida.txtCantidad.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "La cantidad es requerida");
            return false;
            
        }

        if (frmComida.txtPrecio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El precio es requerido");
            return false;
        }

        if (frmComida.txtCliente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El cliente es requerido");
            return false;
            
        }

        if (frmComida.txtObservacion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "La observación es requerida");
            return false;
        }

        return true;
    }

    public void initData() {
        var ordenes = Orden.leerOrdenes();

        String[] columnHeaders = {"Id", "Nombre", "Cantidad", "Precio", "Cliente", "Observación"};
        DefaultTableModel model = new DefaultTableModel(columnHeaders, 0);

        ordenes.forEach(orden -> model.addRow(new Object[]{
                orden.id,
                orden.nombre,
                orden.cantidad,
                orden.precio,
                orden.cliente,
                orden.observacion
        }));

        frmComida.jTableOrden.setModel(model);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frmComida.btnAgregar) {
            agregarOrden();
        }

        if (e.getSource() == frmComida.btnActualizaar) {
            actualizarOrden();
        }

        if (e.getSource() == frmComida.btnEliminar) {
            eliminarOrden();
        }
    }

    private void agregarOrden() {
        //Validar que no queden campos vacios
        validate();

        //Validar que los campos donde se deben ingresar numeros no se ingresen letras
        if (!validateNumbers(frmComida.txtCantidad.getText().trim())) {
            JOptionPane.showMessageDialog(null, "Ingresa solo números");

        }

        if (!validateNumbers(frmComida.txtPrecio.getText().trim())) {
            JOptionPane.showMessageDialog(null, "Ingresa solo números");

        }

        orden.nombre = frmComida.txtNombre.getText();
        orden.cantidad = Integer.parseInt(frmComida.txtCantidad.getText());
        orden.precio = Double.parseDouble(frmComida.txtPrecio.getText());
        orden.cliente = frmComida.txtCliente.getText();
        orden.observacion = frmComida.txtObservacion.getText();

        var resultado = Orden.agregarOrden(orden);
        initData();
    }

    public void actualizarOrden() {

        //Validar que no queden campos vacios
        validate();

        //Validar que los campos donde se deben ingresar numeros no se ingresen letras
        if (!validateNumbers(frmComida.txtCantidad.getText().trim())) {
            JOptionPane.showMessageDialog(null, "Ingresa solo números");

        }

        if (!validateNumbers(frmComida.txtPrecio.getText().trim())) {
            JOptionPane.showMessageDialog(null, "Ingresa solo números");

        }

        if (!validateNumbers(frmComida.txtCantidad.getText().trim())) {
            JOptionPane.showMessageDialog(null, "Ingresa solo números");

        }

        if (!validateNumbers(frmComida.txtPrecio.getText().trim())) {
            JOptionPane.showMessageDialog(null, "Ingresa solo números");

        }

        Orden ordenNueva = new Orden();
        ordenNueva.id = selectedId;
        ordenNueva.nombre = frmComida.txtNombre.getText();
        ordenNueva.cantidad = Integer.parseInt(frmComida.txtCantidad.getText());
        ordenNueva.precio = Double.parseDouble(frmComida.txtPrecio.getText());
        ordenNueva.cliente = frmComida.txtCliente.getText();
        ordenNueva.observacion = frmComida.txtObservacion.getText();

        var resultado = Orden.updateOrdenes(ordenNueva);
        initData();
    }

    public void eliminarOrden() {

        validate();
        var resultado = Orden.eliminarOrden(selectedId);
        initData();
    }
    
}


