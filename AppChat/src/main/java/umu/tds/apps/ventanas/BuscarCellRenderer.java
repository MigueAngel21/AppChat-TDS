package umu.tds.apps.ventanas;

import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Color;
import java.awt.Component;
import java.awt.BorderLayout;

public class BuscarCellRenderer extends JPanel implements ListCellRenderer<Object> {
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
	public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index,
			boolean isSelected, boolean cellHasFocus) {

		// Simulación de renderizado sin persistencia
		// Normalmente sería:
		// Mensaje mensaje = (Mensaje) value;
		// lblEmisor.setText(mensaje.getEmisor().getNombre());
		// lblReceptor.setText(mensaje.getReceptor().getNombre());
		// texto.setText(mensaje.getTexto());

		// Versión temporal: renderiza un string plano como si fuera un mensaje simulado
		if (value instanceof String) {
			lblEmisor.setText("Emisor");
			lblReceptor.setText("Receptor");
			lblEmisor.setForeground(Color.BLACK);
			lblReceptor.setForeground(Color.BLACK);
			texto.setText((String) value);
		} else {
			lblEmisor.setText("?");
			lblReceptor.setText("?");
			texto.setText("Mensaje desconocido");
		}

		// Estilo si está seleccionado
		if (isSelected) {
			setBackground(new Color(220, 240, 255));
		} else {
			setBackground(Color.WHITE);
		}
		return this;
	}
}

