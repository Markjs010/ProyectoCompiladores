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

        ControlPrincipal cp; // se crea el control principal
        ControladorVentana cv = new ControladorVentana(); // se crea el controlador de la ventana
        VentanaPrincipal vp = new VentanaPrincipal(); // se crea la ventana principal

        cp = new ControlPrincipal(cv, vp); // se crea el control principal
        vp.setControl(cv); // se le asigna el control principal a la ventana principal
        cp.ejecutaComando(Comandos.INICIA, null); // se ejecuta el comando inicia

    }
}
