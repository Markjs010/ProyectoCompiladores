package modelo;

import java.util.List;
import java.util.ArrayList;

public class ComprobarGramatica implements ComprobarG {
    // TODO no repetir, obtener reglas y separarlas en un metodo aparte.
    // TODO: cambiar el uso de noterminales y terminales ya que en realidad son lado
    // izquierdo y derecho

    public void obtenerProducciones(Gramatica g) {
        String reglas = g.getCadena();
        String[] reglasSeparadas = reglas.split("->|\n");
        g.setProducciones(reglasSeparadas);

    }

    // TODO ObtenerProduccionesFinales

    public boolean esGramaticaRegular(Gramatica g) {
        String[] reglasSeparadas = g.getProducciones();
        for (int i = 0; i < reglasSeparadas.length; i++) {
            if (i % 2 == 0) {
                if (reglasSeparadas[i].length() > 2 || reglasSeparadas[i].length() < 1
                        || !Character.isUpperCase(reglasSeparadas[i].charAt(0))) {
                    return false;
                }
            }
        }
        return true;
    }

    // TODO Separar terminales y no terminales

    public void separarTerminales(Gramatica g) {
        String[] reglasSeparadas = g.getProducciones();
        List<String> ladoIzquierdo = new ArrayList<String>();
        List<String> ladoDerecho = new ArrayList<String>();

        for (int i = 0; i < reglasSeparadas.length; i++) {
            if (i % 2 == 0) {
                if (!ladoIzquierdo.contains(reglasSeparadas[i])) {
                    ladoIzquierdo.add(reglasSeparadas[i]);
                }
            } else {
                ladoDerecho.add(reglasSeparadas[i]);
            }
        }
        System.out.println(ladoIzquierdo);
        System.out.println(ladoDerecho);

        g.setLadoIzquierdo(ladoIzquierdo);
        g.setLadoDerecho(ladoDerecho);
    }

    // TODO Quitar recursividad a la izquierda

    public void quitarRecursividadIzquierda(Gramatica g) {
        List<String> nuevasReglas = new ArrayList<String>();
        List<String> ladoDerecho = g.getLadoDerecho();
        List<String> ladoIzquierdo = g.getLadoIzquierdo();
        g.setProduccionesFinales(nuevasReglas);

        for (int i = 0; i < ladoIzquierdo.size(); i++) {
            for (int j = 0; j < ladoDerecho.size(); j++) {
                if (ladoIzquierdo.get(i).charAt(0) == ladoDerecho.get(j).charAt(0)) {
                    String reglaNoRecursiva = encontrarReglaNoRecursiva(ladoIzquierdo.get(i).charAt(0), g);
                    if (reglaNoRecursiva != null) {
                        String izqNoRecu = (ladoIzquierdo.get(i).charAt(0) + "'");
                        String nuevaRegla = ladoIzquierdo.get(i).charAt(0) + "->" + reglaNoRecursiva + izqNoRecu;
                        nuevasReglas.add(nuevaRegla);
                        nuevaRegla = (izqNoRecu + "->") + ladoDerecho.get(j).substring(1) + izqNoRecu;
                        nuevasReglas.add(nuevaRegla);
                        nuevaRegla = (izqNoRecu + "->e");
                        nuevasReglas.add(nuevaRegla);
                        g.setProduccionesFinales(nuevasReglas);

                    } else {
                        System.out.println("No se puede quitar la recursividad a la izquierda");
                    }

                }
            }
        }
        System.out.println(nuevasReglas);

    }

    // TODO arreglar condicion para no repetir lado derecho
    public String encontrarReglaNoRecursiva(char noTerminal, Gramatica g) {
        List<String> ladoDerecho = g.getLadoDerecho();
        List<String> ladoIzquierdo = g.getLadoIzquierdo();
        List<String> produccionesFinales = g.getProduccionesFinales();

        for (int i = 0; i < ladoIzquierdo.size(); i++) { // Se busca en los no terminales la regla que no sea recursiva
            for (int j = 0; j < ladoDerecho.size(); j++) {
                if (ladoIzquierdo.get(i).charAt(0) == noTerminal && ladoDerecho.get(j).charAt(0) != noTerminal
                        && !produccionesFinales.contains(ladoDerecho.get(j))) {
                    return ladoDerecho.get(j);
                }
            }
        }
        return null; // Si no se encuentra una regla no recursiva, se regresa null
    }

    // TODO Obtener primeros y siguientes

}
