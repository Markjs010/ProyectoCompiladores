package vista;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class VentanaPrincipal extends VentanaAbstracta {

    private JTextField reglasGramaticales;
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

        this.reglasGramaticales = new JTextField();
        this.tabla = new JTable();

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelCampos.add(etqTxtArea, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        panelCampos.add(reglasGramaticales, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelCampos.add(etqTabla, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panelCampos.add(new JScrollPane(tabla), gbc);

        this.btnAnalizar = new JButton("Analizar");
        this.btnAnalizar.addActionListener(this);
        this.btnBorrar = new JButton("Borrar");
        this.btnBorrar.addActionListener(this);
        this.btnGnerarTabla = new JButton("Generar Tabla");
        this.btnGnerarTabla.addActionListener(this);

        panelBotones.add(btnAnalizar);
        panelBotones.add(btnBorrar);
        panelBotones.add(btnGnerarTabla);

        this.getContentPane().add(panelCampos, BorderLayout.CENTER);
        this.getContentPane().add(panelBotones, BorderLayout.SOUTH);

        this.setResizable(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

}
