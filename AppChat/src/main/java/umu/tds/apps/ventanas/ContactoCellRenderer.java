package umu.tds.apps.ventanas;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import umu.tds.apps.AppChat.Contacto;
import umu.tds.apps.AppChat.Usuario;


/**
 * Clase que implementa un renderizador personalizado para elementos de tipo `Contacto` en un `JList`.
 * Muestra una imagen asociada al contacto junto con su nombre.
 */
public class ContactoCellRenderer extends JPanel implements ListCellRenderer<Contacto> {


	//Atributos de la clase
	private static final long serialVersionUID = 1L;
	private JLabel nameLabel;
	private JLabel imageLabel;


	// Constructor que inicializa los componentes del renderizador.
	public ContactoCellRenderer() {
		setLayout(new BorderLayout(5, 5));

		nameLabel = new JLabel();
		imageLabel = new JLabel();

		add(imageLabel, BorderLayout.WEST);
		add(nameLabel, BorderLayout.CENTER);
	}


	//Método que devuelve el componente que se va a usar para renderizar cada celda del JList.
	@Override
	public Component getListCellRendererComponent(JList<? extends Contacto> list, Contacto contacto, int index,
			boolean isSelected, boolean cellHasFocus) {
		
		// Set the text
		nameLabel.setText(contacto.getNombre());

		// Load the image from a random URL (for example, using "https://robohash.org")
		ImageIcon imageIcon = new ImageIcon(ContactoCellRenderer.class.getResource(Usuario.IMG));//Usuario.IMG
		imageLabel.setIcon(imageIcon);

		// Set background and foreground based on selection
		if (isSelected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		} else {
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}
		
		return this;
	}
}
