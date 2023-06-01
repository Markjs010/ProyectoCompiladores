package modelo;

import java.util.ArrayList;
import java.util.List;

public class Gramatica implements Data {
    // TODO: generar nuevas variables que definan el lado izquierdo del derecho
    // (podria cambiar noterminal y terminal por estos)
    private String cadena;
    private List<String> terminales;
    private List<String> noTerminales;
    private String[] producciones;
    private List<String> produccionesFinales = new ArrayList<String>();
    private List<String> ladoIzquierdo;
    private List<String> ladoDerecho;

    private String inicial;

    public Gramatica(String cadena) {
        this.cadena = cadena;
    }

    public Gramatica() {
        this.cadena = "";
        this.terminales = new ArrayList<String>();
        this.noTerminales = new ArrayList<String>();
        this.producciones = new String[0];
        this.inicial = "";
    }

    public String getCadena() {
        return cadena;
    }

    public List<String> getTerminales() {
        return terminales;
    }

    public List<String> getNoTerminales() {
        return noTerminales;
    }

    public String[] getProducciones() {
        return producciones;
    }

    public String getInicial() {
        return inicial;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public void setTerminales(List<String> terminales) {
        this.terminales = terminales;
    }

    public void setNoTerminales(List<String> noTerminales) {
        this.noTerminales = noTerminales;
    }

    public void setProducciones(String[] producciones) {
        this.producciones = producciones;
    }

    public void setInicial(String inicial) {
        this.inicial = inicial;
    }

    public List<String> getProduccionesFinales() {
        return produccionesFinales;
    }

    public void setProduccionesFinales(List<String> produccionesFinales) {
        this.produccionesFinales.addAll(produccionesFinales);
    }

    public List<String> getLadoIzquierdo() {
        return ladoIzquierdo;
    }

    public void setLadoIzquierdo(List<String> ladoIzquierdo) {
        this.ladoIzquierdo = ladoIzquierdo;
    }

    public List<String> getLadoDerecho() {
        return ladoDerecho;
    }

    public void setLadoDerecho(List<String> ladoDerecho) {
        this.ladoDerecho = ladoDerecho;
    }

    @Override
    public String toString() {
        return "Gramatica{" + "nombre=" + cadena + ", terminales=" + terminales + ", noTerminales=" + noTerminales
                + ", producciones=" + producciones + ", inicial=" + inicial + '}';
    }

}
