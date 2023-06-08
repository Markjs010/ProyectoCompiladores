package modelo;

import java.util.List;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ComprobarGramatica implements ComprobarG {
    // TODO no repetir, obtener reglas y separarlas en un metodo aparte.
    // TODO: cambiar el uso de noterminales y terminales ya que en realidad son lado
    // izquierdo y derecho

    public void obtenerProducciones(Gramatica g) {
        String reglas = g.getCadena();
        String[] reglasSeparadas = reglas.split("->|\n");
        g.setProducciones(reglasSeparadas);

    }

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
        List<String> noTerminales = g.getNoTerminales();
        List<String> borrar = new ArrayList<String>();
        List<String> borrarAux = new ArrayList<String>();

        g.setProduccionesFinales(nuevasReglas);
        for (int j = 0; j < ladoDerecho.size(); j++) {
            if (noTerminales.get(j).charAt(0) == ladoDerecho.get(j).charAt(0)) {
                String reglaNoRecursiva = encontrarReglaNoRecursiva(noTerminales.get(j).charAt(0), g);
                if (reglaNoRecursiva != null) {
                    String nuevaRegla;
                    String izqNoRecu = (noTerminales.get(j).charAt(0) + "'");
                    if (!borrarAux.contains(reglaNoRecursiva)) {
                        nuevaRegla = noTerminales.get(j).charAt(0) + "->" + reglaNoRecursiva + izqNoRecu;
                        nuevasReglas.add(nuevaRegla);
                        borrar.add(noTerminales.get(j) + reglaNoRecursiva);
                        borrarAux.add(reglaNoRecursiva);
                    }
                    nuevaRegla = (izqNoRecu + "->") + ladoDerecho.get(j).substring(1) + izqNoRecu;
                    nuevasReglas.add(nuevaRegla);
                    // NO REGLA NO RECURSIVA ARREGLAR ERROR
                    String epsilon = incluirEpsilon(izqNoRecu, nuevasReglas);
                    if (epsilon != null) {
                        nuevasReglas.add(epsilon);
                    }

                    borrar.add(noTerminales.get(j) + ladoDerecho.get(j));

                } else {
                    System.out.println("No se puede quitar la recursividad a la izquierda");
                }

            }
        }

        System.out.println("Estas son las nuevas reglas" + nuevasReglas);

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
        int indice = 0;

        for (int i = 0; i < borrar.size(); i++) {
            indice = noTerminales.indexOf(Character.toString(borrar.get(i).charAt(0)));
            if (indice != -1) {
                noTerminales.remove(indice);
                terminales.remove(indice);
            }
        }

        for (int i = 0; i < noTerminales.size(); i++) {
            nuevasReglas.add(noTerminales.get(i) + "->" + terminales.get(i));
        }
        // TODO arreglar duplicados
        g.setProduccionesFinales(nuevasReglas);

        g.setCadena(String.join("\n", g.getProduccionesFinales()));
        System.out.println("Reglas finales" + g.getProduccionesFinales());
        obtenerProducciones(g);
        separarTerminales(g);
        // System.out.println("no Terminal " + g.getNoTerminales());
        // System.out.println("Terminal " + g.getTerminales());
        // System.out.println("Inicial " + g.getInicial());
        List<String> noTerAux = new ArrayList<String>();
        noTerAux.addAll(g.getNoTerminales());
        List<String> terAux = new ArrayList<String>();
        terAux.addAll(g.getTerminales());
        obtenerPrimeros(g.getInicial(), noTerAux, terAux, g);
        noTerAux.addAll(g.getNoTerminales());
        terAux.addAll(g.getTerminales());

        obtenerSiguientes(g.getInicial(), terAux, noTerAux, g, g.getInicial());

    }

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
        List<String> terminales = g.getTerminales();
        List<String> ladoIzquierdo = g.getLadoIzquierdo();
        List<String> noTerminales = g.getNoTerminales();
        List<String> aux1 = new ArrayList<String>();
        List<String> aux2 = new ArrayList<String>();
        List<String> aux3 = new ArrayList<String>();

        for (int j = 0; j < terminales.size(); j++) {
            if (ladoIzquierdo.contains(Character.toString(terminales.get(j).charAt(
                    0))) && noTerminales.get(j).charAt(0) != terminales.get(j).charAt(0)) {

                if (!aux1.contains(noTerminales.get(j)) || !aux2.contains(terminales.get(j))) {
                    aux1.add(noTerminales.get(j));
                    aux2.add(terminales.get(j));
                    aux3.add(terminales.get(j).substring(0, 1));
                }

                String noRecu = encontrarReglaNoRecursiva(terminales.get(j).charAt(0), g);
                if (noRecu != null) {
                    String noTerRecursivo = Character.toString(terminales.get(j).charAt(0));
                    int inx = aux3.indexOf(noTerRecursivo);
                    System.out.println(aux1.get(inx) + " " + aux2.get(inx) + " " + noTerRecursivo);
                    cambiarReglaIndirecta(noTerRecursivo, aux1.get(inx), aux2.get(inx), g);
                }

            }
        }

        // System.out.println("Funciona? ");

        obtenerProducciones(g);
        separarTerminales(g);
        quitarRecursividadIzquierda(g);

    }

    public void cambiarReglaIndirecta(String noTerRecu, String aux1, String aux2, Gramatica g) {
        // TODO Cambiar regla indirecta

        List<String> terminales = g.getTerminales();
        List<String> noTerminales = g.getNoTerminales();
        String[] nuevasReglas = new String[noTerminales.size()];

        for (int i = 0; i < terminales.size(); i++) {
            if (Character.toString(terminales.get(i).charAt(0)).equals(aux1)
                    && noTerminales.get(i).equals(noTerRecu)) {
                terminales.add(i, (aux2 + terminales.get(i).substring(1)));
                terminales.remove(i + 1);
            }
            nuevasReglas[i] = (noTerminales.get(i) + "->" + terminales.get(i) + "\n");
        }

        g.setCadena(String.join("", nuevasReglas));

    }

    // TODO Arreglar primeros para que detecte terminales como id
    public void obtenerPrimeros(String Ini, List<String> noTerminales, List<String> terminales, Gramatica g) {
        List<String> noTerminalesAux = new ArrayList<String>();
        noTerminalesAux.addAll(g.getNoTerminales());

        LinkedHashMap<String, List<String>> primeros = g.getPrimeros();
        String inicial = Ini;
        System.out.println("Inicial " + inicial);
        int indx = noTerminales.indexOf(inicial);
        System.out.println("noTerminales " + noTerminales);
        List<String> aux = primeros.get(inicial);

        if (aux == null) {
            aux = new ArrayList<String>();
            primeros.put(inicial, aux);
        }

        String terminal = terminales.get(indx);
        if (!noTerminalesAux.contains(terminal.substring(0, 1))) {
            if (!aux.contains(terminal.substring(0, 1))) {
                aux.add(terminal.substring(0, 1));

            }
            terminales.remove(indx);
            noTerminales.remove(indx);
            System.out.println("terminal " + primeros);

            if (!terminales.isEmpty()) {
                obtenerPrimeros(noTerminales.get(0), noTerminales, terminales, g);
            } else {
                g.setPrimeros(primeros);
                System.out.println(g.getPrimeros());

            }
        } else {
            System.out.println("Holaaaaa");
            if (primeros.containsKey(terminal.substring(0, 1))) {
                for (String i : primeros.get(terminal.substring(0, 1))) {
                    if (!aux.contains(i)) {
                        aux.add(i);
                    }
                }
                terminales.remove(indx);
                noTerminales.remove(indx);
                if (!terminales.isEmpty()) {
                    obtenerPrimeros(noTerminales.get(0), noTerminales, terminales, g);
                } else {
                    g.setPrimeros(primeros);
                    System.out.println(g.getPrimeros());

                }
            } else {
                obtenerPrimeros(terminal.substring(0, 1), noTerminales, terminales, g);
            }
        }
    }

    public void obtenerSiguientes(String ini, List<String> terminales, List<String> noTerminales, Gramatica g,
            String iniAnt) {
        LinkedHashMap<String, List<String>> siguientes = g.getSiguientes();
        LinkedHashMap<String, List<String>> primeros = g.getPrimeros();
        List<String> izquierda = g.getLadoIzquierdo();
        String inicial = ini;
        System.out.println("yo" + izquierda);
        int indx = noTerminales.indexOf(inicial);
        List<String> aux = siguientes.get(inicial);
        if (aux == null) {
            aux = new ArrayList<String>();
            siguientes.put(inicial, aux);
        }

        if (inicial.equals(g.getInicial())) {
            if (!aux.contains("$")) {
                aux.add("$");
            }
        }

        for (String i : terminales) {

            int indice = i.indexOf(inicial);
            System.out.println("inicial" + inicial + "terminal " + i + "indice " + indice + "IniAnt" + iniAnt);

            if (indice != -1) {
                if (indice + 1 <= i.length() - 1) {
                    if (i.charAt(indice + 1) != '\'') {
                        if (indice + 2 <= i.length() - 1) {
                            if (i.charAt(indice + 2) != '\'') {
                                if (!noTerminales.contains(i.substring(indice + 1, indice + 2))) {
                                    if (!aux.contains(i.substring(indice + 1, indice + 2))) {
                                        aux.add(i.substring(indice + 1, indice + 2));
                                    }
                                } else {
                                    if (primeros.containsKey(i.substring(indice + 1, indice + 2))
                                            && !primeros.get(i.substring(indice + 1, indice + 2)).contains("e")) {
                                        for (String j : primeros.get(i.substring(indice + 1, indice + 2))) {
                                            if (!aux.contains(j) && !j.equals("e")) {
                                                aux.add(j);
                                            }
                                        }
                                    } else if (primeros.containsKey(i.substring(indice + 1, indice + 2))
                                            && primeros.get(i.substring(indice + 1, indice + 2)).contains("e")
                                            && !iniAnt.equals(i.substring(indice + 1, indice + 2))) {
                                        for (String j : primeros.get(i.substring(indice + 1, indice + 2))) {
                                            if (!aux.contains(j)) {
                                                aux.add(j);
                                            }
                                        }
                                        if (siguientes.containsKey(noTerminales.get(terminales.indexOf(i)))) {
                                            for (String j : primeros.get(i.substring(indice + 1, indice + 2))) {
                                                if (!aux.contains(j)) {
                                                    aux.add(j);
                                                }
                                            }
                                        } else {
                                            obtenerSiguientes(noTerminales.get(terminales.indexOf(
                                                    i)), terminales,
                                                    noTerminales,
                                                    g,
                                                    inicial);
                                        }
                                    }
                                }
                            }
                        } else if (indice + 3 <= i.length() - 1) {
                            if (primeros.containsKey(i.substring(indice + 1, indice + 3))
                                    && !primeros.get(i.substring(indice + 1, indice + 3)).contains("e")) {
                                for (String j : primeros.get(i.substring(indice + 1, indice + 3))) {
                                    if (!aux.contains(j)) {
                                        aux.add(j);
                                    }
                                }
                            } else if (primeros.containsKey(i.substring(indice + 1, indice + 3))
                                    && primeros.get(i.substring(indice + 1, indice + 3)).contains("e")
                                    && !iniAnt.equals(i.substring(indice + 1, indice + 3))) {
                                for (String j : primeros.get(i.substring(indice + 1, indice + 3))) {
                                    if (!aux.contains(j) && !j.equals("e")) {
                                        aux.add(j);
                                    }
                                }
                                if (siguientes.containsKey(noTerminales.get(terminales.indexOf(i)))) {
                                    for (String j : siguientes.get(noTerminales.get(terminales.indexOf(i)))) {
                                        if (!aux.contains(j)) {
                                            aux.add(j);
                                        }
                                    }
                                } else {
                                    obtenerSiguientes(noTerminales.get(terminales.indexOf(
                                            i)), terminales, noTerminales, g,
                                            inicial);
                                }
                            }
                        }
                    } else {
                        if (!iniAnt.equals(i.substring(indice, indice + 2))
                                && !i.substring(indice, indice + 2).equals(noTerminales.get(terminales.indexOf(i)))) {
                            System.out.println("entro " + i.substring(indice, indice + 2) + " Sale "
                                    + noTerminales.get(terminales.indexOf(i)));
                            if (siguientes.containsKey(noTerminales.get(terminales.indexOf(i)))) {
                                System.out.println("entro mucho");
                                for (String j : siguientes.get(noTerminales.get(terminales.indexOf(i)))) {
                                    if (!aux.contains(j)) {
                                        aux.add(j);
                                    }
                                }
                            } else {
                                obtenerSiguientes(i.substring(indice, indice + 2), terminales, noTerminales, g,
                                        inicial);
                            }
                        }

                    }

                } else {
                    if (!inicial.equals(i.substring(indice))) {
                        System.out.println("si");
                        if (siguientes.containsKey(noTerminales.get(terminales.indexOf(i)))) {
                            for (String j : siguientes.get(noTerminales.get(terminales.indexOf(i)))) {
                                if (!aux.contains(j)) {
                                    aux.add(j);
                                }
                            }
                        }

                    }

                }
            }

        }
        for (String i : izquierda) {
            if (!siguientes.containsKey(i)) {
                obtenerSiguientes(i, terminales, noTerminales, g, inicial);
            }
        }
        System.out.println("siguientes " + siguientes);
    }

}
