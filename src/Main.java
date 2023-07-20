import Controller.OrdenController;
import Modelo.Orden;
import Vista.frmComida;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Orden modeloOrden = new Orden();
                frmComida formComida = new frmComida();

                OrdenController controller = new OrdenController(modeloOrden, formComida);

                formComida.setVisible(true);
                formComida.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });


    }
}
