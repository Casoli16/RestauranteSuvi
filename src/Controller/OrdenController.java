package Controller;

import Modelo.Orden;
import Vista.frmComida;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrdenController implements ActionListener {

    private Orden orden;
    frmComida frmComida = new frmComida();

    public OrdenController(Orden orden, frmComida frmComida) {
        this.orden = orden;
        this.frmComida = frmComida;
    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }
}
