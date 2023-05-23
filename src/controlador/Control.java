package controlador;

import modelo.Data;

public interface Control {
    public Data ejecutaComando(String c, Data d);

    void setPadre(Control c);
}
