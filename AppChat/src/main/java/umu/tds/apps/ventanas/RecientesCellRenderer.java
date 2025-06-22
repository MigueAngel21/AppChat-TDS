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


/**
 * Clase que define cómo se renderizan los elementos de la lista de usuarios recientes.
 * Extiende JPanel e implementa ListCellRenderer para personalizar cada celda.
 */
public class RecientesCellRenderer extends JPanel implements ListCellRenderer<Usuario> {
    //Atributos de clase
    private static final long serialVersionUID = 1L;
    private JLabel userLabel;
    private JLabel imageLabel;
    private JTextField estadoLabel;
    private JPanel panel;
    private JPanel rightPanel;

	//Constructor de la clase, inicializa los componentes gráficos de la celda.
    public RecientesCellRenderer() {
        setLayout(new FlowLayout());

        userLabel = new JLabel();
        imageLabel = new JLabel();
        estadoLabel = new JTextField();
        estadoLabel.setEditable(false);
        estadoLabel.setBackground(null);	//fondo transparente

        panel = new JPanel();
        rightPanel = new JPanel();

		//Configuración del panel cetral
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(100, 50));
        panel.setBackground(Color.WHITE);

		//Configuración del panel derecho
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBackground(Color.WHITE);
		
		//Añade los componentes al renderer
        add(imageLabel, BorderLayout.WEST);
        panel.add(userLabel, BorderLayout.NORTH);
        panel.add(estadoLabel, BorderLayout.SOUTH);
        add(panel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
    }

    //Método que configura cómo se ve cada celda de la lista.
    @Override
    public Component getListCellRendererComponent(JList<? extends Usuario> list,
            Usuario usuario, int index, boolean isSelected, boolean cellHasFocus) {

    	 Usuario usuarioActual = Controlador.INSTANCE.getUsuarioActual();
		
		//Obtiene el nombre del contacto desde la agenda del usuario
         String nombre = usuarioActual.existeContacto(usuario.getTelefono());
         userLabel.setText(nombre);
		
		//Establece la imagen de perfil por defecto
         ImageIcon imageIcon = new ImageIcon(RecientesCellRenderer.class.getResource(usuario.getImagen()));//Usuario.IMG
         imageLabel.setIcon(imageIcon);
        //Muestra el texto del último mensaje con ese contacto
         Mensaje m = usuarioActual.getUltimoMensaje(usuario);
         estadoLabel.setText(m != null ? m.getTexto() : "");
		
		//Colorea la celda si está seleccionada
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
