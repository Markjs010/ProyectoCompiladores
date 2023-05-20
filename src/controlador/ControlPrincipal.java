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
                vPrin = new VentanaPrincipal();
                vPrin.setBounds(200, 200, 800, 600);
                vPrin.setVisible(true);

                break;
            case Comandos.SALIR:
                break;
            default:
                return null;
        }
        return null;
    }

}
