package controlador;

import modelo.Data;
import vista.VentanaPrincipal;

public class ControlPrincipal extends ControlAbstracto {

    private VentanaPrincipal vPrin;

    public ControlPrincipal(ControladorVentana cV, VentanaPrincipal vPrin) {
        this.vPrin = vPrin;
    }

    @Override
    public Data ejecutaComando(String c, Data d) {
        switch (c) {
            case Comandos.INICIA:
                vPrin.setBounds(250, 50, 800, 600);
                vPrin.setVisible(true);

                break;
            case Comandos.SALIR:
                break;
            default:
                return null;
        }
        return null;
    }

    // public void setvPrin(VentanaPrincipal vPrin) {
    // this.vPrin = vPrin;
    // }

}
