package controlador;

public abstract class ControlAbstracto implements Control {
    protected Control padre;

    public void setPadre(Control c) {
        this.padre = c;
    }

}
