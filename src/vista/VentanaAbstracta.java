package vista;

import javax.swing.JFrame;
import java.awt.event.ActionListener;
import controlador.Control;

public abstract class VentanaAbstracta extends JFrame implements ActionListener {
    protected Control control;

    public VentanaAbstracta() {
        super();
    }

    public VentanaAbstracta(String string) {
        super(string);
    }

    public void setControl(Control control) {
        this.control = control;
    }

}