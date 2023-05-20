package controlador;

import modelo.Data;

public interface Control {
    public Data ejecutaComando(String c, Data d);

    public void setPadre(Control c);
}
