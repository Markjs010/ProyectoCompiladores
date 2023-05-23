package modelo;

public interface ComprobarG {
    public void obtenerProducciones(Gramatica g);

    public boolean esGramaticaRegular(Gramatica g);

    public void separarTerminales(Gramatica g);

    public void quitarRecursividadIzquierda(Gramatica g);

    public String encontrarReglaNoRecursiva(char noTerminal, Gramatica g);

}
