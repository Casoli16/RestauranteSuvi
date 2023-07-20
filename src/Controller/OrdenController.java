package Controller;

import Modelo.Orden;
import Vista.frmComida;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrdenController implements ActionListener {

    private Orden orden;
    private frmComida frmComida = new frmComida();

    public OrdenController(Orden orden, frmComida frmComida) {
        this.orden = orden;
        this.frmComida = frmComida;
        this.frmComida.btnAgregar.addActionListener(this);
        this.frmComida.btnActualizaar.addActionListener(this);
        this.frmComida.btnEliminar.addActionListener(this);

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
    }

    public void actualizarOrden() {

    }

    public void eliminarOrden() {

    }
}
