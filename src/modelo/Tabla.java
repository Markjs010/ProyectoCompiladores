package modelo;

public class Tabla implements Data {

    private String[][] tabla;
    private String[] terminales;
    private String[] noTerminales;
    private String[] producciones;
    private String inicial;

    public Tabla(String[][] tabla, String[] terminales, String[] noTerminales, String[] producciones, String inicial) {
        this.tabla = tabla;
        this.terminales = terminales;
        this.noTerminales = noTerminales;
        this.producciones = producciones;
        this.inicial = inicial;
    }

    public Tabla() {
        this.tabla = new String[0][0];
        this.terminales = new String[0];
        this.noTerminales = new String[0];
        this.producciones = new String[0];
        this.inicial = "";
    }

    public String[][] getTabla() {
        return tabla;
    }

    public String[] getTerminales() {
        return terminales;
    }

    public String[] getNoTerminales() {
        return noTerminales;
    }

    public String[] getProducciones() {
        return producciones;
    }

    public String getInicial() {
        return inicial;
    }

    public void setTabla(String[][] tabla) {
        this.tabla = tabla;
    }

    public void setTerminales(String[] terminales) {
        this.terminales = terminales;
    }

    public void setNoTerminales(String[] noTerminales) {
        this.noTerminales = noTerminales;
    }

    public void setProducciones(String[] producciones) {
        this.producciones = producciones;
    }

    public void setInicial(String inicial) {
        this.inicial = inicial;
    }

}
