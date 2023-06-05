package controlador;

import modelo.ComprobarG;
import modelo.ComprobarGramatica;
import modelo.Data;
import modelo.Gramatica;

public class ControladorVentana extends ControlAbstracto {
    ComprobarGramatica comprobarG;
    Gramatica gramatica;

    @Override
    public Data ejecutaComando(String c, Data d) {
        comprobarG = new ComprobarGramatica();

        switch (c) {
            case Comandos.COMPROBAR:
                Gramatica g = (Gramatica) d;
                comprobarG.obtenerProducciones(g);
                if (comprobarG.esGramaticaRegular(g)) {
                    comprobarG.separarTerminales(g);
                    comprobarG.quitarRecuIndirecta(g);
                    // comprobarG.quitarRecursividadIzquierda(g);
                }
                break;
            default:
                return null;
        }
        return null;
    }
}
