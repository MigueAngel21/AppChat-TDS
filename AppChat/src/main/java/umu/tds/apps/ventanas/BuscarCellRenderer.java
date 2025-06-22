package umu.tds.apps.ventanas;

import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.LineBorder;

import umu.tds.apps.AppChat.Mensaje;

import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Color;
import java.awt.Component;
import java.awt.BorderLayout;

public class BuscarCellRenderer extends JPanel implements ListCellRenderer<Mensaje> {
	private static final long serialVersionUID = 1L;

	private final JPanel panelEmisorReceptor;
	private final JLabel lblEmisor;
	private final JLabel lblReceptor;
	private final JPanel panelTexto;
	private final JLabel texto;

	public BuscarCellRenderer() {
		setLayout(new BorderLayout());
		setBorder(new LineBorder(Color.BLACK, 1));

		// Panel superior: emisor y receptor
		panelEmisorReceptor = new JPanel();
		panelEmisorReceptor.setLayout(new BorderLayout());
		panelEmisorReceptor.setBackground(Color.WHITE);

		lblEmisor = new JLabel();
		lblReceptor = new JLabel();
		panelEmisorReceptor.add(lblEmisor, BorderLayout.WEST);
		panelEmisorReceptor.add(lblReceptor, BorderLayout.EAST);

		// Panel central: texto del mensaje
		panelTexto = new JPanel();
		panelTexto.setBackground(Color.WHITE);
		texto = new JLabel();
		texto.setForeground(Color.BLACK);
		panelTexto.add(texto);

		add(panelEmisorReceptor, BorderLayout.NORTH);
		add(panelTexto, BorderLayout.CENTER);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Mensaje> list, Mensaje mensaje, int index,
			boolean isSelected, boolean cellHasFocus) {

		// Actualizar los valores de las etiquetas con los datos del mensaje
		lblEmisor.setText(mensaje.getEmisor().getUsuario());
		lblReceptor.setText(mensaje.getReceptor().getUsuario());
		texto.setText(mensaje.getTexto());
		return this; // Devolver el panel configurado
	}
}

