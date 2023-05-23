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

    // TODO Comprobar si es gramatica regular

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
                ladoIzquierdo.add(reglasSeparadas[i]);
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
        List<String> nuevasReglas = g.getProduccionesFinales();
        String[] noTerminales = g.getNoTerminales();
        String[] terminales = g.getTerminales();

        for (int i = 0; i < noTerminales.length; i++) {
            for (int j = 0; j < terminales.length; j++) {
                if (noTerminales[i].charAt(0) == terminales[j].charAt(0)) {
                    if (i == j) { // recursividad directa
                        String reglaNoRecursiva = encontrarReglaNoRecursiva(noTerminales[i].charAt(0), g);
                        if (reglaNoRecursiva != null) { // Falta agregar condicion para que no se repita la regla que
                                                        // quita recursividad con la noRecursiva
                            String nuevaRegla = noTerminales[i] + "->" + reglaNoRecursiva + ("X" + i);
                            nuevasReglas.add(nuevaRegla);
                        } else {
                            System.out.println("No se puede quitar la recursividad a la izquierda");
                        }
                    } else {

                    }
                }
            }
        }

    }

    public String encontrarReglaNoRecursiva(char noTerminal, Gramatica g) {
        String[] noTerminales = g.getNoTerminales();
        String[] terminales = g.getTerminales();

        for (int i = 0; i < noTerminales.length; i++) { // Se busca en los no terminales la regla que no sea recursiva
            if (noTerminales[i].charAt(0) == noTerminal) {
                if (terminales[i].charAt(0) != noTerminal) { // Si la regla es no recursiva, se regresa esa regla
                    return terminales[i];
                }

            }
        }
        return null; // Si no se encuentra una regla no recursiva, se regresa null
    }

    // TODO Obtener primeros y siguientes

}
