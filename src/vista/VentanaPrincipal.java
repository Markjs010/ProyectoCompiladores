package vista;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.Comandos;
import controlador.ControladorVentana;
import modelo.Gramatica;

public class VentanaPrincipal extends VentanaAbstracta {
    Gramatica gramatica;

    private JTextArea reglasGramaticales;
    private JTextArea cadenaAnalizar;
    private JTable tabla; // tabla LL(1)
    private JButton btnAnalizar;
    private JButton btnBorrar;
    private JButton btnGnerarTabla;

    public VentanaPrincipal() {
        super("Gramaticas Libres de Contexto");
        JPanel panelCampos, panelBotones;
        JLabel etqTxtArea, etqTabla;

        panelCampos = new JPanel(new GridBagLayout());
        panelCampos.setBorder(new EmptyBorder(5, 5, 5, 5));

        panelBotones = new JPanel(new GridLayout(1, 3));
        panelBotones.setBorder(new EmptyBorder(5, 5, 5, 5));

        etqTxtArea = new JLabel("Ingresa las reglas:");
        etqTabla = new JLabel("Tabla LL(1):");

        this.reglasGramaticales = new JTextArea();
        this.cadenaAnalizar = new JTextArea();
        this.tabla = new JTable();

        GridBagConstraints gbc = new GridBagConstraints();

        // gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelCampos.add(etqTxtArea, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panelCampos.add(etqTabla, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weighty = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panelCampos.add(reglasGramaticales, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weighty = 1;
        gbc.weightx = 0.6;
        gbc.fill = GridBagConstraints.BOTH;
        panelCampos.add(new JScrollPane(tabla), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        gbc.insets = new Insets(5, 5, 5, 5);
        panelCampos.add(new JLabel("Cadena a analizar:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.insets = new Insets(5, 5, 5, 150);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelCampos.add(cadenaAnalizar, gbc);

        this.btnAnalizar = new JButton("Comprobar Gramatica");
        this.btnAnalizar.setActionCommand(Comandos.COMPROBAR);
        this.btnAnalizar.addActionListener(this);
        this.btnBorrar = new JButton("Borrar");
        this.btnBorrar.setActionCommand(Comandos.BORRAR);
        this.btnBorrar.addActionListener(this);
        this.btnGnerarTabla = new JButton("Generar Tabla");
        this.btnGnerarTabla.setActionCommand(Comandos.GENERAR_TABLA);
        this.btnGnerarTabla.addActionListener(this);

        panelBotones.add(btnAnalizar);
        panelBotones.add(btnBorrar);
        panelBotones.add(btnGnerarTabla);

        this.getContentPane().add(panelCampos, BorderLayout.CENTER);
        this.getContentPane().add(panelBotones, BorderLayout.SOUTH);

        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String reglas;
        switch (e.getActionCommand()) {
            case Comandos.COMPROBAR:
                reglas = this.reglasGramaticales.getText();
                gramatica = new Gramatica(reglas);
                this.control.ejecutaComando(Comandos.COMPROBAR, gramatica);
                JOptionPane.showMessageDialog(this,
                        "Producciones sin recursividad a la izquierda " + gramatica.getProduccionesFinales()
                                + "\n Primeros " + gramatica.getPrimeros());
                break;
            case Comandos.BORRAR:
                break;
            case Comandos.GENERAR_TABLA:
                break;
            default:
                break;
        }
    }

}
