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
    

    public OrdenController(Orden orden, frmComida frmComida) {
        this.orden = orden;
        this.frmComida = frmComida;
        this.frmComida.btnAgregar.addActionListener(this);
        this.frmComida.btnActualizaar.addActionListener(this);
        this.frmComida.btnEliminar.addActionListener(this);
        this.frmComida.jTableOrden.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
              int filaSeleccionada = frmComida.jTableOrden.getSelectedRow();
              
              String id = frmComida.jTableOrden.getValueAt(filaSeleccionada, 1).toString();
              String nombre = frmComida.jTableOrden.getValueAt(filaSeleccionada, 2).toString();
              String cantidad = frmComida.jTableOrden.getValueAt(filaSeleccionada, 3).toString();
              String precio = frmComida.jTableOrden.getValueAt(filaSeleccionada, 4).toString();
              String cliente = frmComida.jTableOrden.getValueAt(filaSeleccionada, 5).toString();
              String observacion = frmComida.jTableOrden.getValueAt(filaSeleccionada, 6).toString();
              
              frmComida.txtNombre.setText(nombre);
              frmComida.txtCantidad.setText(cantidad);
              frmComida.txtPrecio.setText(precio);
              frmComida.txtCliente.setText(cliente);
              frmComida.txtObservacion.setText(observacion);
            }

        });

        initData();
    }


    public void initData() {
        var ordenes = Orden.leerOrdenes();

        String[] columnHeaders = {"Id", "Nombre", "Cantidad", "Precio", "Cliente", "Observación"};
        DefaultTableModel model = new DefaultTableModel(columnHeaders, 0);

        ordenes.forEach(orden -> model.addRow(new Orden[]{orden}));

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
        orden.nombre = frmComida.txtNombre.getText();
        orden.cantidad = Integer.parseInt(frmComida.txtCantidad.getText());
        orden.precio = Double.parseDouble(frmComida.txtPrecio.getText());
        orden.cliente = frmComida.txtCliente.getText();
        orden.observacion = frmComida.txtObservacion.getText();

        var resultado = Orden.agregarOrden(orden);
        initData();
    }

    public void actualizarOrden() {

    }

    public void eliminarOrden() {

    }
    
}


