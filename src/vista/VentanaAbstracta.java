package vista;

import javax.swing.JFrame;
import java.awt.event.ActionListener;
import controlador.Control;

public abstract class VentanaAbstracta extends JFrame implements ActionListener {
    Control control;


    public VentanaAbstracta(String string) {
        super(string);
    }

    public VentanaAbstracta(Control control) {
        this.control = control;
    }

    public VentanaAbstracta() {
        super();
    }

    public void setControl(Control control) {
        this.control = control;
    }

}