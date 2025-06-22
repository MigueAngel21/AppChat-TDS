package umu.tds.apps.ventanas;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;
import umu.tds.apps.Controlador.Controlador;
import umu.tds.apps.AppChat.Usuario;
import umu.tds.apps.AppChat.Mensaje;


public class RecientesCellRenderer extends JPanel implements ListCellRenderer<Usuario> {
    private static final long serialVersionUID = 1L;

    private JLabel userLabel;
    private JLabel imageLabel;
    private JTextField estadoLabel;
    private JPanel panel;
    private JPanel rightPanel;

    public RecientesCellRenderer() {
        setLayout(new FlowLayout());

        userLabel = new JLabel();
        imageLabel = new JLabel();
        estadoLabel = new JTextField();
        estadoLabel.setEditable(false);
        estadoLabel.setBackground(null);

        panel = new JPanel();
        rightPanel = new JPanel();

        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(100, 50));
        panel.setBackground(Color.WHITE);

        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBackground(Color.WHITE);

        add(imageLabel, BorderLayout.WEST);
        panel.add(userLabel, BorderLayout.NORTH);
        panel.add(estadoLabel, BorderLayout.SOUTH);
        add(panel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
    }

    //cambiar esta funcion por la del github
    @Override
    public Component getListCellRendererComponent(JList<? extends Usuario> list,
            Usuario usuario, int index, boolean isSelected, boolean cellHasFocus) {

    	 Usuario usuarioActual = Controlador.INSTANCE.getUsuarioActual();

         String nombre = usuarioActual.existeContacto(usuario.getTelefono());
         userLabel.setText(nombre);

         ImageIcon imageIcon = new ImageIcon(RecientesCellRenderer.class.getResource(Usuario.IMG));//Usuario.IMG
         imageLabel.setIcon(imageIcon);
         Mensaje m = usuarioActual.getUltimoMensaje(usuario);
         estadoLabel.setText(m != null ? m.getTexto() : "");

         if (isSelected) {
             setBackground(list.getSelectionBackground());
             panel.setBackground(list.getSelectionBackground());
             rightPanel.setBackground(list.getSelectionBackground());
         } else {
             setBackground(list.getBackground());
             panel.setBackground(list.getBackground());
             rightPanel.setBackground(list.getBackground());
         }
         
         return this;
    }
}
