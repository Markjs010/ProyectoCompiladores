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
        List<String> noTerminal = new ArrayList<String>();
        List<String> terminal = new ArrayList<String>();

        for (int i = 0; i < reglasSeparadas.length; i++) {
            if (i % 2 == 0) {
                noTerminal.add(reglasSeparadas[i]);
                if (!ladoIzquierdo.contains(reglasSeparadas[i])) {
                    ladoIzquierdo.add(reglasSeparadas[i]);
                }
            } else {
                ladoDerecho.add(reglasSeparadas[i]);
                terminal.add(reglasSeparadas[i]);
            }
        }
        System.out.println(ladoIzquierdo);
        System.out.println(ladoDerecho);
        System.out.println(noTerminal);
        System.out.println("terminal " + terminal);

        g.setLadoIzquierdo(ladoIzquierdo);
        g.setLadoDerecho(ladoDerecho);
        g.setNoTerminales(noTerminal);
        g.setTerminales(terminal);
    }

    // TODO Quitar recursividad a la izquierda

    public void quitarRecursividadIzquierda(Gramatica g) {
        List<String> nuevasReglas = new ArrayList<String>();
        List<String> ladoDerecho = g.getLadoDerecho();
        List<String> ladoIzquierdo = g.getLadoIzquierdo();
        List<String> borrar = new ArrayList<String>();
        g.setProduccionesFinales(nuevasReglas);

        for (int i = 0; i < ladoIzquierdo.size(); i++) {
            for (int j = 0; j < ladoDerecho.size(); j++) {
                if (ladoIzquierdo.get(i).charAt(0) == ladoDerecho.get(j).charAt(0)) {
                    String reglaNoRecursiva = encontrarReglaNoRecursiva(ladoIzquierdo.get(i).charAt(0), g);
                    if (reglaNoRecursiva != null) {
                        String nuevaRegla;
                        String izqNoRecu = (ladoIzquierdo.get(i).charAt(0) + "'");
                        if (!borrar.contains(reglaNoRecursiva)) {
                            nuevaRegla = ladoIzquierdo.get(i).charAt(0) + "->" + reglaNoRecursiva + izqNoRecu;
                            nuevasReglas.add(nuevaRegla);
                            borrar.add(reglaNoRecursiva);
                        }
                        nuevaRegla = (izqNoRecu + "->") + ladoDerecho.get(j).substring(1) + izqNoRecu;
                        nuevasReglas.add(nuevaRegla);
                        // NO REGLA NO RECURSIVA ARREGLAR ERROR
                        String epsilon = incluirEpsilon(izqNoRecu, nuevasReglas);
                        if (epsilon != null) {
                            nuevasReglas.add(epsilon);
                        }

                        borrar.add(ladoDerecho.get(j));

                    } else {
                        System.out.println("No se puede quitar la recursividad a la izquierda");
                    }

                }
            }

            System.out.println("Estas son las nuevas reglas" + nuevasReglas);
        }
        g.setProduccionesFinales(nuevasReglas);

        actualizarReglasTerminales(borrar, g);
    }

    public String incluirEpsilon(String ladoIzqRec, List<String> nuevasReglas) {
        if (!nuevasReglas.contains(ladoIzqRec + "->e")) {
            return ladoIzqRec + "->e";
        }
        return null;
    }

    public void actualizarReglasTerminales(List<String> borrar, Gramatica g) {
        List<String> nuevasReglas = new ArrayList<String>();
        List<String> terminales = g.getTerminales();
        List<String> noTerminales = g.getNoTerminales();
        if (borrar.isEmpty()) {
            return;
        }
        for (int i = 0; i < borrar.size(); i++) {
            noTerminales.remove(terminales.indexOf(borrar.get(i)));
            terminales.remove(borrar.get(i));
        }
        for (int i = 0; i < noTerminales.size(); i++) {
            nuevasReglas.add(noTerminales.get(i) + "->" + terminales.get(i));
        }
        g.setProduccionesFinales(nuevasReglas);
        System.out.println("nuevas reglas " + g.getProduccionesFinales());
        System.out.println("no Terminal " + noTerminales);
        System.out.println("Terminal " + terminales);

    }

    // TODO arreglar condicion para no repetir lado derecho
    public String encontrarReglaNoRecursiva(char noTerminal, Gramatica g) {
        List<String> ladoDerecho = g.getLadoDerecho();
        List<String> noTerminales = g.getNoTerminales();

        for (int i = 0; i < noTerminales.size(); i++) { // Se busca en los no terminales la regla que no sea recursiva
            if (noTerminales.get(i).charAt(0) == noTerminal && ladoDerecho.get(i).charAt(0) != noTerminal
                    && !noTerminales.contains(Character.toString(ladoDerecho.get(i).charAt(0)))) {
                return ladoDerecho.get(i);
            }

        }
        return null; // Si no se encuentra una regla no recursiva, se regresa null
    }

    public void quitarRecuIndirecta(Gramatica g) {
        // TODO Quitar recursividad indirecta
        List<String> terminales = g.getTerminales();
        List<String> ladoIzquierdo = g.getLadoIzquierdo();
        List<String> noTerminales = g.getNoTerminales();
        List<String> aux1 = new ArrayList<String>();
        List<String> aux2 = new ArrayList<String>();
        List<String> aux3 = new ArrayList<String>();

        for (int i = 0; i < ladoIzquierdo.size(); i++) {
            for (int j = 0; j < terminales.size(); j++) {
                if (ladoIzquierdo.contains(Character.toString(terminales.get(j).charAt(
                        0))) && ladoIzquierdo.get(i).charAt(0) != terminales.get(j).charAt(0)) {

                    if (!aux1.contains(noTerminales.get(j)) || !aux2.contains(terminales.get(j))) {
                        aux1.add(noTerminales.get(j));
                        aux2.add(terminales.get(j));
                        aux3.add(terminales.get(j).substring(0, 1));
                    }

                    String noRecu = encontrarReglaNoRecursiva(terminales.get(j).charAt(0), g);
                    if (noRecu != null) {
                        String noTerRecursivo = Character.toString(terminales.get(j).charAt(0));
                        int inx = aux3.indexOf(noTerRecursivo);
                        cambiarReglaIndirecta(noTerRecursivo, aux1.get(inx), aux2.get(inx), g);
                        // System.out.println(aux1.get(inx) + " " + aux2.get(inx));
                        // System.out.println(noTerRecursivo);

                    }
                }
            }
        }

    }

    public void cambiarReglaIndirecta(String noTerRecu, String aux1, String aux2, Gramatica g) {
        // TODO Cambiar regla indirecta

        List<String> terminales = g.getTerminales();
        List<String> ladoIzquierdo = g.getLadoIzquierdo();
        List<String> noTerminales = g.getNoTerminales();

        for (int i = 0; i < terminales.size(); i++) {
            if (Character.toString(terminales.get(i).charAt(0)).equals(aux1)
                    && noTerminales.get(i).equals(noTerRecu)) {
                System.out.println(noTerminales.get(i) + " " + terminales.get(i));
                terminales.add(i, (aux2 + terminales.get(i).substring(1)));
                terminales.remove(i + 1);
            }
        }
        System.out.println("terminal act " + terminales);
        System.out.println("No terminal act " + noTerminales);

    }
    // TODO Obtener primeros y siguientes

}
