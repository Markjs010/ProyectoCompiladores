package main;

import controlador.Comandos;
import controlador.ControlPrincipal;
import controlador.ControladorVentana;
import vista.VentanaPrincipal;

// esta es la clase

public class Main {
    // metodo main
    public static void main(String[] args) {
        // TODO code application logic here

        VentanaPrincipal vp = new VentanaPrincipal(); // se crea la ventana principal
        ControlPrincipal cp; // se crea el control principal
        ControladorVentana cv = new ControladorVentana(); // se crea el controlador de la ventana
        cp = new ControlPrincipal(cv, vp); // se crea el control principal con el controlador de la ventana y la ventana
                                           // principal

        vp.setControl(cp); // se le asigna el control a la ventana
        cp.ejecutaComando(Comandos.INICIA, null); // se ejecuta el comando inicia

    }
}
